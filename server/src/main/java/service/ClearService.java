package service;

import dataaccess.*;

public class ClearService {

    private final UserDAO userDao;
    private final AuthDAO authDao;
    private final GameDAO gameDao;


    public ClearService(UserDAO userDao, AuthDAO authDao, GameDAO gameDao) {
        this.userDao = userDao;
        this.authDao = authDao;
        this.gameDao = gameDao;
    }

    void clear() throws DataAccessException {
        userDao.clear();
        authDao.clear();
        gameDao.clear();
    }
}
