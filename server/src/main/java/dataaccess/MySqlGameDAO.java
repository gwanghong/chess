package dataaccess;

import model.GameData;

import java.util.Collection;
import java.util.List;

public class MySqlGameDAO extends MySqlDataAccess implements GameDAO {

    public MySqlGameDAO() {
        super();
    }

    @Override
    public void clear() throws DataAccessException {
        executeUpdate("TRUNCATE TABLE game");
    }

    @Override
    public void createGame(GameData game) throws DataAccessException {

    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {
        return null;
    }

    @Override
    public Collection<GameData> listGames() {
        return List.of();
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {

    }

    @Override
    public String[] createStatements() {
        return new String[]{"""
                            CREATE TABLE IF NOT EXISTS  game (
                            'gameID' int NOT NULL,
                            'whiteUsername' varchar(256) NOT NULL,
                            'blackUsername' varchar(256) NOT NULL,
                            'gameName' varchar(256) NOT NULL,
                            'chessGame' TEXT NOT NULL,
                            PRIMARY KEY (gameID)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
