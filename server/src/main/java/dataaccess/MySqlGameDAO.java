package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;
import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Collection;
import java.util.List;

import static java.sql.Types.NULL;

public class MySqlGameDAO extends MySqlDataAccess implements GameDAO {

    public MySqlGameDAO() {
        super();
    }

    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE TABLE game";
        executeUpdate(statement);
    }

    @Override
    public void createGame(GameData game) throws DataAccessException {

    }

    @Override
    public GameData getGame(int gameID) throws DataAccessException {

        int passGameID = NULL;
        String passWhiteUser = null;
        String passBlackUser = null;
        String passGameName = null;
        ChessGame passGame = null;

        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game WHERE gameID=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setInt(1, gameID);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        var getGameID = rs.getInt("gameID");
                        var getWhiteUser = rs.getString("whiteUsername");
                        var getBlackUser = rs.getString("blackUsername");
                        var getGameName = rs.getString("gameName");
                        var getChessGame = rs.getString("chessGame");

                        var deserialized_ChessGame = new Gson().fromJson(getChessGame, ChessGame.class);

                        passGameID = getGameID;
                        passWhiteUser = getWhiteUser;
                        passBlackUser = getBlackUser;
                        passGameName = getGameName;
                        passGame = deserialized_ChessGame;
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        return new GameData(passGameID, passWhiteUser, passBlackUser, passGameName, passGame);
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
                            `gameID` int NOT NULL,
                            `whiteUsername` varchar(256) NOT NULL,
                            `blackUsername` varchar(256) NOT NULL,
                            `gameName` varchar(256) NOT NULL,
                            `chessGame` TEXT NOT NULL,
                            PRIMARY KEY (gameID)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
