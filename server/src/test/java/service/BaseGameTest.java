package service;

import dataaccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.*;

public abstract class BaseGameTest {

    protected GameService gameService;
    protected GameDAO gameDao;
    protected AuthData auth;

    @BeforeAll
    static void setUpBeforeClass() {
        // No-op
    }

    @BeforeEach
    void setUp() throws DataAccessException {
        gameDao = createGameDAO();
        AuthDAO authDao = createAuthDAO();
        gameService = new GameService(gameDao, authDao);

        gameDao.clear();
        authDao.clear();

        auth = new AuthData("1234", "newU");
        authDao.createAuth(auth);
    }

    protected abstract GameDAO createGameDAO();

    protected abstract AuthDAO createAuthDAO();

    @Test
    @DisplayName("authToken is valid")
    public void isAuthTokenValidTest() throws Exception {

        try {
            gameService.isAuthTokenValid("1234");
        } catch (DataAccessException e) {
            Assertions.fail();
        }

        Assertions.assertEquals(0, gameService.listGames(auth.authToken()).size());
        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
        Assertions.assertEquals(1, gameService.listGames(auth.authToken()).size());

        gameService.joinGame("1234", "WHITE", createGame.gameID());
        Assertions.assertEquals("newU", gameDao.getGame(createGame.gameID()).whiteUsername());
    }

    @Test
    @DisplayName("create game positive test")
    public void createGameTest() throws DataAccessException {
        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
    }

    @Test
    @DisplayName("list game positive test")
    public void listGameTest() throws DataAccessException {
        gameDao.clear();

        Assertions.assertEquals(0, gameService.listGames(auth.authToken()).size());
        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
        Assertions.assertEquals(1, gameService.listGames(auth.authToken()).size());
    }

    @Test
    @DisplayName("join game positive test")
    public void joinGameTest() throws Exception {
        gameDao.clear();

        Assertions.assertEquals(0, gameService.listGames(auth.authToken()).size());
        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
        Assertions.assertEquals(1, gameService.listGames(auth.authToken()).size());

        gameService.joinGame("1234", "WHITE", createGame.gameID());
        Assertions.assertEquals("newU", gameDao.getGame(createGame.gameID()).whiteUsername());
    }

    @Test
    @DisplayName("createGame negative")
    public void createGameNegativeTest() {
        try {
            gameService.createGame("1365", "newGame");
            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("listGame negative")
    public void listGameNegativeTest() {
        try {
            gameService.listGames("1365");
            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("joinGame negative")
    public void joinGameNegativeTest() throws DataAccessException {
        GameData game = gameService.createGame("1234", "new");
        try {
            gameService.joinGame("1365", "WHITE", game.gameID());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        try {
            gameService.joinGame("1234", "GREY", game.gameID());
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        try {
            gameService.joinGame("1234", "BLACK", 568);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
