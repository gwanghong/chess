package dataaccess;

import com.google.gson.Gson;
import model.UserData;

public class MySqlUserDAO extends MySqlDataAccess implements UserDAO {

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void insertUser(UserData u) throws DataAccessException {
        var statement = "INSERT INTO user (name, password, email), VALUES (?, ?, ?)";
        var json = new Gson().toJson(u);

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
