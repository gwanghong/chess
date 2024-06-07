package dataaccess;

import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.GameService;
import service.GameTest;

public class GameUnitTest extends GameTest {

    private static GameService gameService;
    private static GameDAO gameDao;
    private static AuthData auth;

    @BeforeAll
    static void setUp() throws DataAccessException {
        gameDao = new MySqlGameDAO();
        AuthDAO authDao = new MySqlAuthDAO();
        gameService = new GameService(gameDao, authDao);

        gameDao.clear();
        authDao.clear();

        auth = new AuthData("1234", "newU");
        authDao.createAuth(auth);
    }

    @Override
    public void isAuthTokenValidTest() {
    }

    @Test
    @DisplayName("create game positive test")
    @Override
    public void createGameTest() throws DataAccessException {

        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
    }

    @Test
    @DisplayName("list game positive test")
    @Override
    public void listGameTest() throws DataAccessException {

        gameDao.clear();

        Assertions.assertEquals(0, gameService.listGames(auth.authToken()).size());
        GameData createGame = gameService.createGame(auth.authToken(), "newGame");
        Assertions.assertNotNull(createGame);
        Assertions.assertEquals(1, gameService.listGames(auth.authToken()).size());
    }

    @Test
    @DisplayName("join game positive test")
    @Override
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
    @Override
    public void creatGameNegativeTest() {
        try {
            gameService.createGame("1365", "newGame");

            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("listGame negative")
    @Override
    public void listgameNegativeTest() {
        try {
            gameService.listGames("1365");

            Assertions.fail();
        } catch (DataAccessException e) {
            Assertions.assertTrue(true);
        }
    }

    @Test
    @DisplayName("joinGame negative")
    @Override
    public void joingameNegativeTest() throws DataAccessException {
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
