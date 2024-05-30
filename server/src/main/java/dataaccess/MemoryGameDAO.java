package dataaccess;

import model.GameData;

import java.util.Map;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {

    private static final Map<Integer, GameData> games = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        games.clear();
    }

    @Override
    public void createGame(GameData game) throws DataAccessException {

    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {

    }

    @Override
    public Map<String, GameData> listGame() throws DataAccessException {
        return new HashMap<>(games);
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {

    }
}
