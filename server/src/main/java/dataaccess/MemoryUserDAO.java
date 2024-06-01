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

        try {
            if (!users.containsKey(u.username())) {
                users.put(u.username(), u);
            } else {
                throw new DataAccessException("user exists");
            }
        } catch (DataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserData getUser(String userName) throws DataAccessException {

        UserData user = users.get(userName);

        /*if (user != null) {
            return user;
        } else {
            throw new DataAccessException("can't find user");
        }*/

        return user;
    }
}
