package service;

import dataaccess.AuthDAO;
import dataaccess.DataAccessException;

public class AuthService {

    private final AuthDAO authDao;

    public AuthService(AuthDAO authDao) {
        this.authDao = authDao;
    }
}
