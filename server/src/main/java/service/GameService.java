package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.AuthData;
import model.GameData;

import java.util.Collection;

import java.util.List;
import java.util.UUID;


public class GameService {

    private final GameDAO gameDao;
    private final AuthDAO authDao;

    public GameService(GameDAO gameDao, AuthDAO authDao) {
        this.gameDao = gameDao;
        this.authDao = authDao;
    }

    public GameData createGame(String authToken, String gameName) throws DataAccessException {

        isAuthTokenValid(authToken);

        int gameID = Math.abs(UUID.randomUUID().hashCode());
        GameData newGame = new GameData(gameID, null, null, gameName, new ChessGame());

        gameDao.createGame(newGame);

        return newGame;
    }

    public Collection<GameData> listGames(String authToken) throws DataAccessException {

        AuthData auth = authDao.getAuth(authToken);
        if (auth == null) {
            throw new DataAccessException("authToken doesn't match or is null");
        }
        isAuthTokenValid(authToken);

        return gameDao.listGames();
    }


    public void joinGame(String authToken, String playerColor, int gameID) throws Exception {
        isAuthTokenValid(authToken);

        GameData joinedGame = gameDao.getGame(gameID);
        String userName = authDao.getAuth(authToken).username();
        List<String> acceptableColor = List.of("WHITE", "BLACK", "", "empty");
        if (!acceptableColor.contains(playerColor)) {
            throw new Exception("Player Color input is wrong");
        }

        if (joinedGame == null) {
            throw new Exception("Wrong gameID or game doesn't exist");
        }

        if ((joinedGame.whiteUsername() != null && playerColor.equals("WHITE"))
                || (joinedGame.blackUsername() != null && playerColor.equals("BLACK"))) {
            throw new IllegalArgumentException("Player color already taken");
        }

        if (playerColor.equals("WHITE")) {
            GameData newGame = new GameData(gameID, userName, joinedGame.blackUsername(), joinedGame.gameName(), joinedGame.game());
            gameDao.updateGame(newGame);
        } else if (playerColor.equals("BLACK")) {
            GameData newGame = new GameData(gameID, joinedGame.whiteUsername(), userName, joinedGame.gameName(), joinedGame.game());
            gameDao.updateGame(newGame);
        } else {
            gameDao.updateGame(joinedGame);
        }
    }


    void isAuthTokenValid(String authToken) throws DataAccessException {
        if (authDao.getAuth(authToken) == null) {
            throw new DataAccessException("authToken not exists");
        }
    }

}
