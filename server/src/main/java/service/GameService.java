package service;

import chess.ChessGame;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.GameDAO;
import model.GameData;

import java.util.UUID;

public class GameService {

    private final GameDAO gameDao;
    private final AuthDAO authDao;

    public GameService(GameDAO gameDao, AuthDAO authDao) {
        this.gameDao = gameDao;
        this.authDao = authDao;
    }

    public GameData createGame(String authToken, String gameName) throws DataAccessException {

        //String userName = authDao.getAuth(authToken).username();
        int gameID = UUID.randomUUID().hashCode();
        GameData newGame = new GameData(gameID, null, null, gameName, new ChessGame());

        gameDao.createGame(newGame);

        return newGame;
    }

    public

}
