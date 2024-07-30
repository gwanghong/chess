package dataaccess;

import service.BaseGameTest;

public class GameUnitTest extends BaseGameTest {

    @Override
    protected GameDAO createGameDAO() {
        return new MySqlGameDAO();
    }

    @Override
    protected AuthDAO createAuthDAO() {
        return new MySqlAuthDAO();
    }
}
