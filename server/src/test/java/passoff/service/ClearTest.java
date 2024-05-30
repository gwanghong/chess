package passoff.service;

import org.junit.jupiter.api.*;
import passoff.model.*;
import passoff.server.TestServerFacade;
import server.Server;
import model.*;
import dataaccess.*;

import java.net.HttpURLConnection;

public class ClearTest {

    private static AuthData authData;
    private static GameData gameData;
    private static UserData userData;

    @BeforeEach
    public void setUp() {
        authData = new AuthData(null, null);

    }

    @Test
    @DisplayName("Testing if clear return success")
    public void positiveTestClear() throws Exception {
        Server.main(new String[]{"8080"});
    }
}
