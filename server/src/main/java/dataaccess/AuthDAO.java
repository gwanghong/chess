package dataaccess;

import model.AuthData;

public interface AuthDAO {

    void clear() throws DataAccessException;

    void createAuth(AuthData auth) throws DataAccessException;

    void getAuth(AuthData auth) throws DataAccessException;

    void deleteAuth(AuthData auth) throws DataAccessException;
}
