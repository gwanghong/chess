package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
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

        isAuthTokenValid(authToken);

        return gameDao.listGames();
    }


    public void joinGame(String authToken, String playerColor, int gameID) throws Exception {
        isAuthTokenValid(authToken);

        GameData joinGame = gameDao.getGame(gameID);
        String userName = authDao.getAuth(authToken).username();
        List<String> acceptableColor = List.of("WHITE", "BLACK", "", "empty");
        if (!acceptableColor.contains(playerColor)) {
            throw new Exception("Player Color input is wrong");
        }

        if (joinGame == null) {
            throw new Exception("Wrong gameID or game doesn't exist");
        }

        if ((joinGame.whiteUsername() != null && playerColor.equals("WHITE"))
                || (joinGame.blackUsername() != null && playerColor.equals("BLACK"))) {
            throw new IllegalArgumentException("Player color already taken");
        }

        if (playerColor.equals("WHITE")) {
            GameData newGame = new GameData(gameID, userName, joinGame.blackUsername(), joinGame.gameName(), joinGame.game());
            gameDao.updateGame(newGame);
        } else if (playerColor.equals("BLACK")) {
            GameData newGame = new GameData(gameID, joinGame.whiteUsername(), userName, joinGame.gameName(), joinGame.game());
            gameDao.updateGame(newGame);
        } else {
            gameDao.updateGame(joinGame);
        }
    }


    void isAuthTokenValid(String authToken) throws DataAccessException {
        try {
            authDao.getAuth(authToken);
        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

}
