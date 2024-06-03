package service;

import dataaccess.DataAccessException;
import dataaccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.*;

public class GameTest {

    private static GameService gameService;
    private static GameDAO gameDao;
    private static AuthDAO authDao;
    private static GameData game;

    private static AuthData auth;

    @BeforeAll
    static void setUp() throws DataAccessException {
        gameDao = new MemoryGameDAO();
        authDao = new MemoryAuthDAO();
        gameService = new GameService(gameDao, authDao);

        auth = new AuthData("1234", "newU");
        authDao.createAuth(auth);

    }

    @Test
    @DisplayName("authToken is valid")
    public void isAuthTokenValidTest() throws Exception {

        try {
            gameService.isAuthTokenValid("1234");
        } catch (DataAccessException e) {
            Assertions.assertTrue(false);
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
    @DisplayName("creategame negative")
    public void creatGameNegativeTest() throws DataAccessException {
        try {
            gameService.createGame("1365", "newGame");

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("listgame negative")
    public void listgameNegativeTest() throws DataAccessException {
        try {
            gameService.listGames("1365");

            Assertions.assertTrue(false);
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("joingame negative")
    public void joingameNegativeTest() throws DataAccessException {
        GameData game = gameService.createGame("1234", "new");
        try {
            gameService.joinGame("1365", "WHITE", game.gameID());

            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        try {
            gameService.joinGame("1234", "GREY", game.gameID());

            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }

        try {
            gameService.joinGame("1234", "BLACK", 568);

            Assertions.assertTrue(false);
        } catch (Exception e) {
            Assertions.assertTrue(true);
        }
    }
}
