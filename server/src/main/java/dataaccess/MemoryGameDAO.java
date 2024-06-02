package dataaccess;

import model.GameData;

import java.util.Collection;
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
        if (!games.containsKey(game.gameID())) {
            games.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game exists");
        }
    }

    @Override
    public GameData getGame(int gameID) {

        return games.get(gameID);
    }

    @Override
    public Collection<GameData> listGames() {

        return games.values();
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {

        if (game != null && games.containsKey(game.gameID())) {
            games.remove(game.gameID());
            games.put(game.gameID(), game);
        } else {
            throw new DataAccessException("game update invalid");
        }
    }
}
