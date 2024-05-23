package passoff.service;

import org.junit.jupiter.api.*;
import passoff.model.*;
import passoff.server.TestServerFacade;
import server.Server;

import java.net.HttpURLConnection;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceUnitTest {
    private static TestUser existingUser;

    private static TestUser newUser;

    private static TestCreateRequest createRequest;

    private static TestServerFacade serverFacade;
    private static Server server;

    private String existingAuth;

    @Test
    @Order(1)
    @DisplayName("Static Files")
    public void staticFiles() throws Exception {
        String htmlFromServer = serverFacade.file("/").replaceAll("\r", "");
        Assertions.assertEquals(HttpURLConnection.HTTP_OK, serverFacade.getStatusCode(),
                "Server response code was not 200 OK");
        Assertions.assertNotNull(htmlFromServer, "Server returned an empty file");
        Assertions.assertTrue(htmlFromServer.contains("CS 240 Chess Server Web API"));
    }
}
