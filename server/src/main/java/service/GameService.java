package service;

import dataaccess.AuthDAO;
import dataaccess.GameDAO;

public class GameService {

    private final GameDAO gameDao;
    private final AuthDAO authDao;

    public GameService(GameDAO gameDao, AuthDAO authDao) {
        this.gameDao = gameDao;
        this.authDao = authDao;
    }

    
}
