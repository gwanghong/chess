package service;

import dataaccess.DataAccessException;
import model.UserData;
import model.AuthData;
import dataaccess.UserDAO;
import dataaccess.AuthDAO;

import java.util.UUID;


public class UserService {

    private final UserDAO userDao;
    private final AuthDAO authDao;

    public UserService(UserDAO userDao, AuthDAO authDao) {
        this.userDao = userDao;
        this.authDao = authDao;
    }

    public AuthData register(UserData user) throws DataAccessException {

        if (user.username().isEmpty()) {
            throw new IllegalArgumentException("Should not be null");
        }

        userDao.insertUser(user);

        String authToken = UUID.randomUUID().toString();
        AuthData auth = new AuthData(authToken, user.username());
        authDao.createAuth(auth);

        return auth;
    }

    public AuthData login(UserData user) throws DataAccessException {


        try {
            UserData containUser = userDao.getUser(user.username());

            if (!containUser.password().equals(user.password())) {
                throw new IllegalArgumentException("password doesn't match");
            }

            String authToken = UUID.randomUUID().toString();
            AuthData auth = new AuthData(authToken, user.username());
            authDao.createAuth(auth);

            return auth;

        } catch (DataAccessException e) {
            throw new DataAccessException("login failed");
        }
    }

    public void logout(String authToken) {

        try {
            authDao.deleteAuth(authToken);
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
