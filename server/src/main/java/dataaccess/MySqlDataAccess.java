package dataaccess;

import java.sql.SQLException;
import java.sql.Statement;

import chess.ChessGame;

import com.google.gson.Gson;

import static java.sql.Statement.RETURN_GENERATED_KEYS;
import static java.sql.Types.NULL;

public class MySqlDataAccess {

    boolean isDatabaseExist = false;

    public MySqlDataAccess() {
        try {
            configureDatabase();
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] createStatements() {
        return null;
    }

    protected void executeUpdate(String statement, Object... params) throws DataAccessException {
        try (var conn = DatabaseManager.getConnection()) {
            try (var ps = conn.prepareStatement(statement, RETURN_GENERATED_KEYS)) {
                for (var i = 0; i < params.length; i++) {
                    var param = params[i];
                    //System.out.println("Setting parameter " + (i + 1) + ": " + param + ": " + param.getClass());
                    switch (param) {
                        case String p -> ps.setString(i + 1, p);
                        case Integer p -> ps.setInt(i + 1, p);
                        case ChessGame p -> ps.setString(i + 1, new Gson().toJson(p));
                        case null -> ps.setNull(i + 1, NULL);
                        default -> {
                            throw new SQLException("Unhandled parameter type: " + param.getClass().getName());
                        }
                    }
                }
                try {
                    ps.executeUpdate();
                } catch (SQLException e) {
                    throw new DataAccessException(e.getMessage());
                }

                var rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    rs.getInt(1);
                }

            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    private void configureDatabase() throws DataAccessException {

        if (!isDatabaseExist) {
            DatabaseManager.createDatabase();
            isDatabaseExist = true;
        }
        //DatabaseManager.createDatabase();

        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements()) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
