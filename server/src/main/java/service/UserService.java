package service;

import dataaccess.DataAccessException;
import model.UserData;
import model.AuthData;
import dataaccess.UserDAO;

public class UserService {

    private final UserDAO userDao;

    public UserService(UserDAO userDao) {
        this.userDao = userDao;
    }

    public AuthData register(UserData user) throws DataAccessException {
        userDao.insertUser(user);

        return null;
    }

    public AuthData login(UserData user) {

        return null;
    }

    public void logout(UserData user) {
    }

}
