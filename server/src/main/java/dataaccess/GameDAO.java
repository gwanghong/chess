package dataaccess;

import model.GameData;

import java.util.Map;

public interface GameDAO {

    void clear() throws DataAccessException;

    void createGame(GameData game) throws DataAccessException;

    GameData getGame(int gameID) throws DataAccessException;

    Map<String, GameData> listGame() throws DataAccessException;

    void updateGame(GameData game) throws DataAccessException;

}
