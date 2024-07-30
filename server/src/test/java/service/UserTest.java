package service;

import dataaccess.*;
import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.*;

public class UserTest {

    private static UserService userService;
    private static UserDAO userDao;
    private static AuthDAO authDao;

    @BeforeAll
    static void setUp() {

        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        userService = new UserService(userDao, authDao);
    }

    @Test
    @DisplayName("register")
    public void registerTest() throws Exception {
        userDao.clear();
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

        AuthData auth = userService.register(newUser);

        Assertions.assertEquals(newUser, userDao.getUser(newUser.username()));
        Assertions.assertEquals(auth.authToken(), authDao.getAuth(auth.authToken()).authToken());
    }

    @Test
    @DisplayName("login")
    public void logIngTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
        AuthData authLogin = userService.login(newUser);


        Assertions.assertNotNull(authLogin);
    }

    @Test
    @DisplayName("logout")
    public void logoutTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
        AuthData authLogin = userService.login(newUser);

        userService.logout(authLogin.authToken());

        try {
            Assertions.assertNull(authDao.getAuth(authLogin.authToken()));

            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }

    }


    @Test
    @DisplayName("negative register")
    public void negativeRegister() throws Exception {

        try {
            userService.register(new UserData("NewUser", "newUserPassword", "nu@mail.com"));

            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @DisplayName("login negative test")
    public void negativeLogin() {
        //not registered user
        try {
            userService.login(new UserData("notRegisteredUser", "passwo", "asdf@asd"));

            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        //wrong password
        try {
            userService.login(new UserData("NewUser", "passwo", "nu@mail.com"));

            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @DisplayName("negative logout test")
    public void negativeLogout() {
        //wrong logout
        try {
            userService.logout("asdf");

            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }

}
