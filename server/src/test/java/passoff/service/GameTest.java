package passoff.service;

import dataaccess.DataAccessException;
import dataaccess.*;
import org.junit.jupiter.api.BeforeEach;
import service.GameService;
import service.UserService;

public class GameTest {

    private static GameService gameService;
    private static GameDAO gameDao;
    private static AuthDAO authDao;

    @BeforeEach
    void setUp() throws DataAccessException {
        gameDao = new MemoryGameDAO();
        authDao = new MemoryAuthDAO();
        gameService = new GameService(gameDao, authDao);
    }
}
