package dataaccess;

import model.AuthData;

import java.util.Map;
import java.util.HashMap;

public class MemoryAuthDAO implements AuthDAO {

    private static final Map<String, AuthData> AUTH_TOKENS = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        AUTH_TOKENS.clear();
    }

    @Override
    public void createAuth(AuthData auth) throws DataAccessException {
        if (!AUTH_TOKENS.containsKey(auth.authToken())) {
            AUTH_TOKENS.put(auth.authToken(), auth);
        } else {
            throw new DataAccessException("authToken exists");
        }
    }

    @Override
    public AuthData getAuth(String authToken) throws DataAccessException {

        AuthData auth = AUTH_TOKENS.get(authToken);

        if (auth != null) {
            return auth;
        } else {
            throw new DataAccessException("authToken not found");
        }

    }

    @Override
    public void deleteAuth(String authToken) throws DataAccessException {

        AuthData auth = AUTH_TOKENS.get(authToken);

        if (auth != null) {
            AUTH_TOKENS.remove(authToken);
        } else {
            throw new DataAccessException("authToken not found");
        }
    }
}
