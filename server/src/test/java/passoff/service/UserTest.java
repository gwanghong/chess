package passoff.service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import passoff.model.TestAuthResult;
import passoff.model.TestResult;
import passoff.model.TestUser;
import passoff.server.TestServerFacade;
import service.UserService;

import java.net.HttpURLConnection;
import java.util.Locale;

public class UserTest {

    private static UserService userService;
    private static UserDAO userDao;
    private static AuthDAO authDao;

    @BeforeEach
    void setUp() throws DataAccessException {
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        userService = new UserService(userDao, authDao);
    }

    @Test
    @DisplayName("Valid request / return success")
    public void positiveTest() throws DataAccessException {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

        AuthData auth = userService.register(newUser);

        Assertions.assertEquals(newUser, userDao.getUser(newUser.username()));
        Assertions.assertEquals(auth.authToken(), authDao.getAuth(auth.authToken()).authToken());

        AuthData authLogin = userService.login(newUser);

        Assertions.assertNotNull(authLogin);

        userService.logout(authLogin.authToken());

        Assertions.assertNull(authDao.getAuth(authLogin.authToken()));
    }

    @Test
    @DisplayName("Invalid request / return failure")
    public void negativeTest() throws DataAccessException {

    }
    
}
