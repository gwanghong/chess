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
    public void createAuth(AuthData auth) throws DataAccessException {
        if (!authTokens.containsKey(auth.authToken())) {
            authTokens.put(auth.authToken(), auth);
        } else {
            throw new DataAccessException("authToken exists");
        }
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {

        AuthData auth = authTokens.get(authToken);
        if (auth != null) {
            return auth;
        } else {
            throw new DataAccessException("authToken not found");
        }
    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

        AuthData auth = authTokens.get(authToken);

        if (auth != null) {
            authTokens.remove(authToken);
        } else {
            throw new DataAccessException("authToken not found");
        }
    }
}
