package dataaccess;

import model.AuthData;

import java.util.Map;
import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {

    private static final Map<String, AuthData> authTokens = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        authTokens.clear();
    }

    @Override
    public void createAuth(AuthData auth) {

    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {
        return null;
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

    }
}
