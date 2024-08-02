package client;

import dataaccess.DataAccessException;
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
        var port = server.run(0);
        System.out.println("Started test HTTP server on " + port);
        facade = new ServerFacade(port);
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

        var authData1 = facade.register("player1", "password", "p1@email.com");
        try {
            var authData2 = facade.register("player1", "password", "p1@email.com");
        } catch (IOException e) {
            assertion = true;
        }

        if (assertion) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void loginPositive() {

    }

    @Test
    void loginNegative() {

    }

    @Test
    void logoutPositive() {

    }

    @Test
    void logoutNegative() {

    }

    @Test
    void createGamePos() {

    }

    @Test
    void createGameNeg() {

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
