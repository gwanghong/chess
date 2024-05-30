package service;

import dataaccess.*;

public class ClearService {

    UserDAO userDao = new MemoryUserDAO();
    AuthDAO authDao = new MemoryAuthDAO();
    GameDAO gameDao = new MemoryGameDAO();

    public void clear() throws DataAccessException {
        userDao.clear();
        authDao.clear();
        gameDao.clear();
    }
}
