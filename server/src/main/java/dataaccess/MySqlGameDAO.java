package dataaccess;

import chess.ChessGame;
import com.google.gson.Gson;
import model.GameData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

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
        if (getGame(game.gameID()) == null) {
            var statement = "INSERT INTO game (gameID, whiteUsername, blackUsername, gameName, chessGame) VALUES (?, ?, ?, ?, ?)";
            executeUpdate(statement, game.gameID(), game.whiteUsername(), game.blackUsername(), game.gameName(), game.game());
        } else {
            throw new DataAccessException("game exists");
        }
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

        if (passGameName == null) {
            return null;
        }

        return new GameData(passGameID, passWhiteUser, passBlackUser, passGameName, passGame);
    }

    @Override
    public Collection<GameData> listGames() {
        var result = new ArrayList<GameData>();
        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM game";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(readGame(rs));
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Error: was caught in ListGames - %s", e.getMessage());
        }

        return result;
    }

    private GameData readGame(ResultSet rs) throws SQLException {
        var gameID = rs.getInt("gameID");
        var whiteUsername = rs.getString("whiteUsername");
        var blackUsername = rs.getString("blackUsername");
        var gameName = rs.getString("gameName");
        var json = rs.getString("chessGame");
        var chessGame = new Gson().fromJson(json, ChessGame.class);
        return new GameData(gameID, whiteUsername, blackUsername, gameName, chessGame);
    }

    @Override
    public void updateGame(GameData game) throws DataAccessException {
        if (game != null && getGame(game.gameID()) != null) {
            try (var conn = DatabaseManager.getConnection()) {
                var statement = "UPDATE game SET whiteUsername=?, blackUsername=?, gameName=?, chessGame=? WHERE gameID=?";
                try (var ps = conn.prepareStatement(statement)) {
                    ps.setString(1, game.whiteUsername());
                    ps.setString(2, game.blackUsername());
                    ps.setString(3, game.gameName());
                    var serializer = new Gson();
                    var json = serializer.toJson(game.game());
                    ps.setString(4, json);

                    ps.setInt(5, game.gameID());

                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new DataAccessException(e.getMessage());
            }
        } else {
            throw new DataAccessException("game update invalid");
        }
    }

    @Override
    public String[] createStatements() {
        return new String[]{"""
                            CREATE TABLE IF NOT EXISTS  game (
                            `gameID` int NOT NULL,
                            `whiteUsername` varchar(256),
                            `blackUsername` varchar(256),
                            `gameName` varchar(256) NOT NULL,
                            `chessGame` TEXT NOT NULL,
                            PRIMARY KEY (`gameID`)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
