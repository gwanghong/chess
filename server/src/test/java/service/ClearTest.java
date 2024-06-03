package service;

import chess.ChessGame;
import org.junit.jupiter.api.*;
import model.*;
import dataaccess.*;
import passoff.model.TestUser;
import service.ClearService;

public class ClearTest {

    private static UserService userService;
    private static GameService gameService;
    private static ClearService clearS;
    private static UserDAO userDao;
    private static AuthDAO authDao;
    private static GameDAO gameDao;
    private static UserData newUser;

    @BeforeEach
    public void setUp() {
        clearS = new ClearService();
        userDao = new MemoryUserDAO();
        authDao = new MemoryAuthDAO();
        gameDao = new MemoryGameDAO();
        userService = new UserService(userDao, authDao);
        gameService = new GameService(gameDao, authDao);
        newUser = new UserData("NewUser", "newUserPassword", "nu@mail.com");
    }

    @Test
    @DisplayName("Testing if clear return success")
    public void positiveTestClear() throws Exception {

        userService.register(newUser);
        AuthData auth = userService.login(newUser);
        GameData game = gameService.createGame(auth.authToken(), "newGame");
        int gameID = game.gameID();

        clearS.clear();

        Assertions.assertNull(userDao.getUser("NewUser"));
        try {
            authDao.getAuth(auth.authToken());

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
        Assertions.assertNull(gameDao.getGame(gameID));

    }
}
