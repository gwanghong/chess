package dataaccess;

import java.sql.SQLException;

public class MySqlDataAccess {

    boolean isDatabaseExist = false;

    public MySqlDataAccess() throws DataAccessException {
        configureDatabase();
    }

    public String[] createStatements() {
        return null;
    }

    private int executeUpdate(String statement, Object... params) throws DataAccessException {

        return 0;
    }

    private void configureDatabase() throws DataAccessException {

        if (!isDatabaseExist) {
            DatabaseManager.createDatabase();
            isDatabaseExist = true;
        }

        try (var conn = DatabaseManager.getConnection()) {
            for (var statement : createStatements()) {
                try (var preparedStatement = conn.prepareStatement(statement)) {
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new DataAccessException("sqlException...");
        }
    }
}
