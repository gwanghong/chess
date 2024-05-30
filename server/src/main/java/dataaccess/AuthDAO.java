package dataaccess;

import model.AuthData;

public interface AuthDAO {

    void clear() throws DataAccessException;

    void createAuth(AuthData auth);

    AuthData getAuth(String authToken) throws DataAccessException;

    void deleteAuth(String authToken) throws DataAccessException;
}
