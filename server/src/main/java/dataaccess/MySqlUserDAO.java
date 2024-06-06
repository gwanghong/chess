package dataaccess;

import com.google.gson.Gson;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.mindrot.jbcrypt.BCrypt;

public class MySqlUserDAO extends MySqlDataAccess implements UserDAO {

    @Override
    public void clear() throws DataAccessException {

    }

    @Override
    public void insertUser(UserData u) throws DataAccessException {
        var statement = "INSERT INTO user (username, password, email), VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(u.password(), BCrypt.gensalt());
        executeUpdate(statement, u.username(), hashedPassword, u.email());
    }

    @Override
    public UserData getUser(String userName) throws DataAccessException {

        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM user WHERE username=?";
            try (var ps = conn.prepareStatement(statement)) {
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return readPet(rs);
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        String hashedPassword = BCrypt.hashpw( , BCrypt.gensalt());

        return new UserData(userName, hashedPassword, );
    }

    @Override
    //Case sensitive ci 지워야할 수도
    public String[] createStatements() {
        return new String[]{
                            """
                            CREATE TABLE IF NOT EXISTS  user (
                            'username' varchar(256) NOT NULL,
                            'password' varchar(256) NOT NULL,
                            'email' varchar(256) NOT NULL,
                            PRIMARY KEY (username)
                            ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                            """
        };
    }
}
