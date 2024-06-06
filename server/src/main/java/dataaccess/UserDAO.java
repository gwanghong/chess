package dataaccess;

import model.UserData;

public interface UserDAO {

    void clear() throws DataAccessException;

    void insertUser(UserData u) throws DataAccessException;

    boolean verifyUser(String username, String password) throws DataAccessException;

    UserData getUser(String userName) throws DataAccessException;
}
