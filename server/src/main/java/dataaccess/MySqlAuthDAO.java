package dataaccess;

import model.AuthData;

public class MySqlAuthDAO extends MySqlDataAccess implements AuthDAO {

    public MySqlAuthDAO() {
        super();
    }

    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE TABLE auth";
        executeUpdate(statement);
    }

    @Override
    public void createAuth(AuthData auth) throws DataAccessException {
        if (getAuth(auth.authToken()) != null) {
            var statement = "INSERT INTO auth (authToken, username) VALUES (?, ?)";
            executeUpdate(statement, auth.authToken(), auth.username());
        }
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {

        String passAuthToken = null;
        String passUsername = null;

        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM auth WHERE authToken=?";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    ps.setString(1, authToken);
                    if (rs.next()) {
                        var getAuthToken = rs.getString("authToken");
                        var getUsername = rs.getString("username");

                        passAuthToken = getAuthToken;
                        passUsername = getUsername;
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        return new AuthData(passAuthToken, passUsername);
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

        if (getAuth(authToken) != null) {
            var statement = "DELETE FROM auth WHERE authToken=?";
            executeUpdate(statement, authToken);
        }
    }

    @Override
    public String[] createStatements() {
        return new String[]{"""
                            CREATE TABLE IF NOT EXISTS  auth (
                            `authToken` varchar(256) NOT NULL,
                            `username` varchar(256) NOT NULL,
                            PRIMARY KEY (authToken)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
