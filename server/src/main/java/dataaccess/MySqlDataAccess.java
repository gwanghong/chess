package dataaccess;

import java.sql.SQLException;

public class MySqlDataAccess {

    boolean isDatabaseExist = false;

    public MySqlDataAccess() throws DataAccessException {
        configureDatabase();
    }

    private final String[] createStatements = {
            """
            CREATE TABLE IF NOT EXISTS 
"""
    };

    private void configureDatabase() throws DataAccessException {

        if (!isDatabaseExist) {
            DatabaseManager.createDatabase();
            isDatabaseExist = true;
        }

        try (var conn = DatabaseManager.getConnection()) {

        } catch (SQLException e) {

        }
    }
}
