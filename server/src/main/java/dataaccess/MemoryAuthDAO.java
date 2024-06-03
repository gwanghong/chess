package dataaccess;

import model.AuthData;

import java.util.Map;
import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {

    private static final Map<String, AuthData> AuthTokens = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        AuthTokens.clear();
    }

    @Override
    public void createAuth(AuthData auth) throws DataAccessException {
        if (!AuthTokens.containsKey(auth.authToken())) {
            AuthTokens.put(auth.authToken(), auth);
        } else {
            throw new DataAccessException("authToken exists");
        }
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {

        AuthData auth = AuthTokens.get(authToken);

        if (auth != null) {
            return auth;
        } else {
            throw new DataAccessException("authToken not found");
        }

    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

        AuthData auth = AuthTokens.get(authToken);

        if (auth != null) {
            AuthTokens.remove(authToken);
        } else {
            throw new DataAccessException("authToken not found");
        }
    }
}
