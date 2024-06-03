package dataaccess;

import model.GameData;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {

    private static final Map<Integer, GameData> Games = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        Games.clear();
    }

    @Override
    public void createGame(GameData game) throws DataAccessException {
        if (!Games.containsKey(game.gameID())) {
            Games.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game exists");
        }
    }

    @Override
    public GameData getGame(int gameID) {

        return Games.get(gameID);
    }

    @Override
    public Collection<GameData> listGames() {

        return Games.values();
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {

        if (game != null && Games.containsKey(game.gameID())) {
            Games.remove(game.gameID());
            Games.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game update invalid");
        }
    }
}
