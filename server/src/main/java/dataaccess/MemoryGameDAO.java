package dataaccess;

import model.GameData;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class MemoryGameDAO implements GameDAO {

    private static final Map<Integer, GameData> GAMES = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        GAMES.clear();
    }

    @Override
    public void createGame(GameData game) throws DataAccessException {
        if (!GAMES.containsKey(game.gameID())) {
            GAMES.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game exists");
        }
    }

    @Override
    public GameData getGame(int gameID) {

        return GAMES.get(gameID);
    }

    @Override
    public Collection<GameData> listGames() {

        return GAMES.values();
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {

        if (game != null && GAMES.containsKey(game.gameID())) {
            GAMES.remove(game.gameID());
            GAMES.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game update invalid");
        }
    }
}
