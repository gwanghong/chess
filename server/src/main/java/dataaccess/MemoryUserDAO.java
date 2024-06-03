package dataaccess;

import model.UserData;

import java.util.Map;
import java.util.HashMap;

public class MemoryUserDAO implements UserDAO {

    private static final Map<String, UserData> Users = new HashMap<>();

    @Override
    public void clear() throws DataAccessException {
        Users.clear();
    }

    @Override
    public void insertUser(UserData u) {

        try {
            if (!Users.containsKey(u.username())) {
                Users.put(u.username(), u);
            } else {
                throw new DataAccessException("user exists");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserData getUser(String userName) {

        return Users.get(userName);
    }
}
