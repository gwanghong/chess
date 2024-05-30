package dataaccess;

import model.UserData;

import java.util.Map;
import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {

    private static final Map<String, UserData> users = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        users.clear();
    }

    @Override
    public void insertUser(UserData u) throws DataAccessException {
        if (!users.containsKey(u.username())) {
            users.put(u.username(), u);
        } else {
            throw new DataAccessException("user exists");
        }
    }

    @Override
    public UserData getUser(String userName) {

        return users.get(userName);
    }
}
