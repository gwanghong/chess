package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.*;
import spark.utils.Assert;

public class UserTest {

    private static UserService userService;
    private static UserDAO userDao;
    private static AuthDAO authDao;

    @BeforeAll
    static void setUp() throws Exception {
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        userService = new UserService(userDao, authDao);
    }

    @Test
    @DisplayName("Valid request / return success")
    public void positiveTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

        AuthData auth = userService.register(newUser);

        Assertions.assertEquals(newUser, userDao.getUser(newUser.username()));
        Assertions.assertEquals(auth.authToken(), authDao.getAuth(auth.authToken()).authToken());

        AuthData authLogin = userService.login(newUser);

        Assertions.assertNotNull(authLogin);

        userService.logout(authLogin.authToken());

        try {
            Assertions.assertNull(authDao.getAuth(authLogin.authToken()));

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("Invalid request / return failure")
    public void negativeTest() throws Exception {

        //userService.register(new UserData("NewUser", "newUserPassword", "nu@mail.com"));

        //re-register
        try {
            userService.register(new UserData("NewUser", "newUserPassword", "nu@mail.com"));

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }

        //not registered user
        try {
            userService.login(new UserData("notregisteredUser", "passwo", "asdf@asd"));

            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        //wrong password
        try {
            userService.login(new UserData("NewUser", "passwo", "nu@mail.com"));

            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        //wrong logout
        try {
            userService.logout("asdf");

            Assertions.assertTrue(false);
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

}
