package dataaccess;

import model.AuthData;

public class MySqlAuthDAO extends MySqlDataAccess implements AuthDAO {

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void createAuth(AuthData auth) throws DataAccessException {

    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

    }

    @Override
    public String[] createStatements() {
        return new String[]{"""
                            CREATE TABLE IF NOT EXISTS  auth (
                            'authToken' varchar(256) NOT NULL,
                            'username' varchar(256) NOT NULL,
                            PRIMARY KEY (authToken)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
