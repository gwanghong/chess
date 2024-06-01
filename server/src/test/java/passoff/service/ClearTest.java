package passoff.service;

import chess.ChessGame;
import org.junit.jupiter.api.*;
import model.*;
import dataaccess.*;
import service.ClearService;

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
            AuthData newAuth = new AuthData("123", "AthUser");
            GameData newGame = new GameData(1234, "whiteUser", "blackUser", "newGame", new ChessGame());

            userDao.insertUser(newUser);
            authDao.createAuth(newAuth);
            gameDao.createGame(newGame);

            Assertions.assertEquals(newUser, userDao.getUser(newUser.username()));
            Assertions.assertEquals(newAuth, authDao.getAuth(newAuth.authToken()));
            Assertions.assertEquals(newGame, gameDao.getGame(newGame.gameID()));

            clearS.clear();

            Assertions.assertNull(userDao.getUser(newUser.username()));
            Assertions.assertNull(authDao.getAuth(newAuth.authToken()));
            Assertions.assertNull(gameDao.getGame(newGame.gameID()));

        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
