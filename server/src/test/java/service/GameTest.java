package service;

import dataaccess.*;

public class GameTest extends BaseGameTest {

    @Override
    protected GameDAO createGameDAO() {
        return new MemoryGameDAO();
    }

    @Override
    protected AuthDAO createAuthDAO() {
        return new MemoryAuthDAO();
    }
}
