package dataaccess;

import model.GameData;

import java.util.Collection;
import java.util.Map;

public interface GameDAO {

    void clear() throws DataAccessException;

    void createGame(GameData game) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    Collection<GameData> listGames();

    void updateGame(GameData game) throws DataAccessException;

}
