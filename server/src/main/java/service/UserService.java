package service;

import dataaccess.DataAccessException;
import model.UserData;
import model.AuthData;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;

public class UserService {

    private final UserDAO userDao;
    private final AuthDAO authDao;

    public UserService(UserDAO userDao, AuthDAO authDao) {
        this.userDao = userDao;
        this.authDao = authDao;
    }

    public void clear() throws DataAccessException {
        userDao.clear();
    }

    public AuthData register(UserData user) throws DataAccessException {
        userDao.insertUser(user);
        
    }

    public AuthData login(UserData user) {
    }

    public void logout(UserData user) {
    }

}
