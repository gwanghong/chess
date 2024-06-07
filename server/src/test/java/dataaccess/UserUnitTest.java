package dataaccess;

import model.AuthData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import service.ClearService;
import service.UserService;

public class UserUnitTest {
    private static UserService userService;
    private static UserDAO userDao;
    private static AuthDAO authDao;
    private static ClearService clearService;

    @BeforeAll
    static void setUp() throws Exception {
        userDao = new MySqlUserDAO();
        authDao = new MySqlAuthDAO();
        userService = new UserService(userDao, authDao);
        userDao.clear();
    }

    @Test
    @DisplayName("register")
    public void registerTest() throws Exception {
        userDao.clear();
        ;
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

        AuthData auth = userService.register(newUser);

        Assertions.assertEquals(newUser.username(), userDao.getUser(newUser.username()).username());
        Assertions.assertEquals(newUser.email(), userDao.getUser(newUser.username()).email());
        Assertions.assertTrue(BCrypt.checkpw(newUser.password(), userDao.getUser(newUser.username()).password()));
        Assertions.assertEquals(auth.authToken(), authDao.getAuth(auth.authToken()).authToken());
    }

    @Test
    @DisplayName("login")
    public void logingTest() throws Exception {
        UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
        AuthData auth = userService.register(newUser);
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
            userService.register(new UserData("NewUser", "newUserPassword", "nu@mail.com"));

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }

    }

    @Test
    @DisplayName("login negative test")
    public void negativeLogin() throws Exception {
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

    }

    @Test
    @DisplayName("negative logout test")
    public void negativeLogout() throws Exception {
        //wrong logout
        try {
            userService.logout("asdf");

            Assertions.assertTrue(false);
        } catch (RuntimeException e) {
            Assertions.assertTrue(true);
        }
    }
}
