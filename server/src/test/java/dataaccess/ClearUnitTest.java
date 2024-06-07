package dataaccess;

import model.AuthData;
import model.GameData;
import model.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.ClearService;
import service.ClearTest;
import service.GameService;
import service.UserService;

public class ClearUnitTest extends ClearTest {
    private static UserService userService;
    private static GameService gameService;
    private static ClearService clearS;
    private static UserDAO userDao;
    private static AuthDAO authDao;
    private static GameDAO gameDao;

    @BeforeEach
    public void setUp() {
        clearS = new ClearService();
        userDao = new MySqlUserDAO();
        authDao = new MySqlAuthDAO();
        gameDao = new MySqlGameDAO();
        userService = new UserService(userDao, authDao);
        gameService = new GameService(gameDao, authDao);
    }

    @Test
    @DisplayName("Testing if clear return success")
    @Override
    public void positiveTestClear() throws Exception {

        userDao.insertUser(new UserData("NewUse", "newUserPassword", "nu@mail.co"));

        AuthData auth = userService.login(new UserData("NewUse", "newUserPassword", "nu@mail.co"));

        GameData game = gameService.createGame(auth.authToken(), "newGame");
        int gameID = game.gameID();

        clearS.clear();

        Assertions.assertNull(userDao.getUser("NewUse"));
        Assertions.assertNull(authDao.getAuth(auth.authToken()));
        Assertions.assertNull(gameDao.getGame(gameID));

    }
}
