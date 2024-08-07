package dataaccess;

import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import service.UserService;

public class UserUnitTest {
    private static UserService userService;
    private static UserDAO userDao;
    private static AuthDAO authDao;

    @BeforeAll
    static void setUp() throws Exception {
        userDao = new MySqlUserDAO();
        authDao = new MySqlAuthDAO();
        userService = new UserService(userDao, authDao);
        userDao.clear();
        authDao.clear();
    }

    @Test
    @DisplayName("register")
    public void registerTest() throws Exception {
        userDao.clear();
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

        AuthData auth = userService.register(newUser);

        Assertions.assertEquals(newUser.username(), userDao.getUser(newUser.username()).username());
        Assertions.assertEquals(newUser.email(), userDao.getUser(newUser.username()).email());
        Assertions.assertTrue(BCrypt.checkpw(newUser.password(), userDao.getUser(newUser.username()).password()));
        Assertions.assertEquals(auth.authToken(), authDao.getAuth(auth.authToken()).authToken());
    }

    @Test
    @DisplayName("login")
    public void loginTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
        userService.register(newUser);
        AuthData authLogin = userService.login(newUser);


        Assertions.assertNotNull(authLogin);
    }

    @Test
    @DisplayName("logout")
    public void logoutTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
        AuthData authLogin = userService.login(newUser);

        userService.logout(authLogin.authToken());

        Assertions.assertNull(authDao.getAuth(authLogin.authToken()));


    }

    @Test
    @DisplayName("negative register")
    public void negativeRegister() throws Exception {

        try {
            userService.register(new UserData("NewUsers", "newUserPasswords", "nu@mails.com"));
            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("login negative test")
    public void negativeLogin() {
        try {
            userService.login(new UserData("notRegisteredUser", "password", "asdf@asd"));

            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        try {
            userService.login(new UserData("NewUser", "password", "nu@mail.com"));

            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("negative logout test")
    public void negativeLogout() {
        try {
            userService.logout("asdf");

            Assertions.fail();
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }
}
