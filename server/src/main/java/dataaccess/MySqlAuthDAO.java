package dataaccess;

import model.AuthData;

public class MySqlAuthDAO extends MySqlDataAccess implements AuthDAO {
    public MySqlAuthDAO() throws DataAccessException {
    }

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
            
           """};
    }
}
