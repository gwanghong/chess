package dataaccess;

import model.UserData;

public interface UserDAO {

    void clear() throws DataAccessException;

    void insertUser(UserData u) throws DataAccessException;

    UserData getUser(String userName);
}
