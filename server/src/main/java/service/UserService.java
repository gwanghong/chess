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

    public AuthData register(UserData user) throws Exception {

        if (user.username().isEmpty() || user.password().isEmpty()) {
            throw new Exception("Should not be null");
        }

        if (userDao.getUser(user.username()) != null) {
            throw new DataAccessException("alreay exists");
        }

        userDao.insertUser(user);

        String authToken = UUID.randomUUID().toString();
        AuthData auth = new AuthData(authToken, user.username());
        authDao.createAuth(auth);

        return auth;
    }

    public AuthData login(UserData user) throws Exception {


        try {
            UserData containUser = userDao.getUser(user.username());

            if (containUser == null) {
                throw new Exception("Error: containUser is null");
            }

            if (!containUser.password().equals(user.password())) {
                throw new Exception("Error: password doesn't match");
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
