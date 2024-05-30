package passoff.service;

import org.junit.jupiter.api.*;
import passoff.model.*;
import passoff.server.TestServerFacade;
import server.Server;
import model.*;
import dataaccess.*;
import service.ClearService;

import java.net.HttpURLConnection;

public class ClearTest {

    private static ClearService clearS;
    private static UserDAO userDao;
    private static AuthDAO authDao;
    private static GameDAO gameDao;

    @BeforeEach
    public void setUp() {
        clearS = new ClearService();
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        gameDao = new MemoryGameDAO();
    }

    @Test
    @DisplayName("Testing if clear return success")
    public void positiveTestClear() {
        try {
            UserData newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");

            userDao.insertUser(newUser);
            //authDao.createAuth(new AuthData());
            //gameDao.createGame(new GameData());

            clearS.clear();
            Assertions.assertNull(userDao.getUser(newUser.username()));

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
