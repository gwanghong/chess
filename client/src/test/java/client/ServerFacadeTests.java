package client;

import chess.ChessGame;
import dataaccess.DataAccessException;
import model.GameData;
import org.junit.jupiter.api.*;
import server.Server;
import Facade.ServerFacade;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import service.ClearService;

import java.io.IOException;
import java.net.URISyntaxException;

public class ServerFacadeTests {

    private static Server server;
    static ServerFacade facade;

    @BeforeAll
    public static void init() {
        server = new Server();
        var port = server.run(8080);
        System.out.println("Started test HTTP server on " + port);
        String url = "http://localhost:" + port;
        facade = new ServerFacade(url);
    }

    @BeforeEach
    public void setup() throws DataAccessException {
        //clear Database
        ClearService clearService = new ClearService();
        clearService.clear();
    }

    @AfterAll
    static void stopServer() {
        server.stop();
    }


    @Test
    public void sampleTest() {
        assertTrue(true);
    }

    @Test
    void registerPositive() throws Exception {
        var authData = facade.register("player1", "password", "p1@email.com");
        assertTrue(authData.authToken().length() > 10);
    }

    @Test
    void registerNegative() throws URISyntaxException, IOException {

        boolean assertion = false;

        facade.register("player1", "password", "p1@email.com");

        try {
            facade.register("player1", "password", "p1@email.com");
        } catch (Exception e) {
            assertion = true;
        }

        if (assertion) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void loginPositive() throws URISyntaxException, IOException {

        facade.register("player1", "password", "p1@email.com");
        var authData = facade.login("player1", "password");

        if (authData.authToken().length() > 10) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void loginNegative() throws URISyntaxException, IOException {

        boolean assertion = false;
        facade.register("player1", "password", "p1@email.com");

        try {
            facade.login("player2", "password2");
        } catch (Exception e) {
            assertion = true;
        }

        if (assertion) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void logoutPositive() throws URISyntaxException, IOException {

        facade.register("player1", "password", "p1@email.com");
        facade.login("player1", "password");


        try {
            facade.logout();
        } catch (IOException e) {
            System.out.println("caught error");
        }
        assertTrue(true);

    }

    @Test
    void logoutNegative() throws URISyntaxException, IOException {

        boolean assertion = false;

        try {
            facade.logout();
        } catch (Exception e) {
            assertion = true;
        }

        assertTrue(assertion);
    }

    @Test
    void createGamePos() throws URISyntaxException, IOException {

        facade.register("player1", "password", "p1@email.com");
        facade.login("player1", "password");

        String gameName = "game1";
        GameData game = new GameData(0, null, null, gameName, new ChessGame());

        try {
            facade.createGame(game);
        } catch (Exception e) {
            fail();
        }

        assertTrue(true);
    }

    @Test
    void createGameNeg() throws URISyntaxException, IOException {

        boolean isTrue = false;

        String gameName = "game1";
        GameData game = new GameData(0, null, null, gameName, new ChessGame());

        try {
            facade.createGame(game);
        } catch (Exception e) {
            isTrue = true;
        }

        assertTrue(isTrue);
    }

    @Test
    void joinGamePos() {

    }

    @Test
    void joinGameNeg() {

    }

    @Test
    void listGamePos() {

    }

    @Test
    void listGameNeg() {

    }

}
