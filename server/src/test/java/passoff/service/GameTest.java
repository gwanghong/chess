package passoff.service;

import dataaccess.DataAccessException;
import dataaccess.*;
import model.AuthData;
import model.GameData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.GameService;
import service.UserService;

import java.util.List;

public class GameTest {

    private static GameService gameService;
    private static GameDAO gameDao;
    private static AuthDAO authDao;
    private static AuthData authData;

    @BeforeEach
    void setUp() throws DataAccessException {
        gameDao = new MemoryGameDAO();
        authDao = new MemoryAuthDAO();
        gameService = new GameService(gameDao, authDao);

        AuthData authD = new AuthData("testAuthToken", "testUser");
        authDao.createAuth(authD);

    }

    @Test
    @DisplayName("Valid request / return success")
    public void positiveTest() throws Exception {
        //List<GameData> games = gameService.listGames(authDao.getAuth());
    }

    @Test
    @DisplayName("Invalid request / return failure")
    public void negativeTest() throws DataAccessException {

    }
}
