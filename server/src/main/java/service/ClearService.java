package service;

import dataaccess.*;

public class ClearService {

    UserDAO userDao = new MySqlUserDAO();
    AuthDAO authDao = new MySqlAuthDAO();
    GameDAO gameDao = new MySqlGameDAO();

    public void clear() throws DataAccessException {
        userDao.clear();
        authDao.clear();
        gameDao.clear();
    }
}
