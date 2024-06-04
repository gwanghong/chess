package dataaccess;

import model.UserData;

public class MySqlUserDAO extends MySqlDataAccess implements UserDAO {
    public MySqlUserDAO() throws DataAccessException {
    }

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void insertUser(UserData u) throws DataAccessException {

    }

    @Override
    public UserData getUser(String userName) throws DataAccessException {
        return null;
    }

    @Override
    public String[] createStatements() {
        return new String[]{"""
            
           """};
    }
}
