package dataaccess;

import model.GameData;

public interface GameDAO {

    void clear() throws DataAccessException;

    void createGame(GameData game) throws DataAccessException;

    void getGame(GameData game) throws DataAccessException;

    void listGame(GameData game) throws DataAccessException;

    void updateGame(GameData game) throws DataAccessException;

}
