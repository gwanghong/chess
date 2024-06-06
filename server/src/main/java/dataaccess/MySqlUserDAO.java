package dataaccess;

import com.google.gson.Gson;
import model.UserData;
import org.eclipse.jetty.server.Authentication;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class MySqlUserDAO extends MySqlDataAccess implements UserDAO {

    public MySqlUserDAO() {
        super();
    }

    @Override
    public void clear() throws DataAccessException {
        var statement = "TRUNCATE TABLE user";
        executeUpdate(statement);
    }

    @Override
    public void insertUser(UserData u) throws DataAccessException {

        //if (getUser(u.username()) != null) {
        var statement = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(u.password(), BCrypt.gensalt());

        executeUpdate(statement, u.username(), hashedPassword, u.email());
        //} else {
        //    throw new DataAccessException("User already exists");
        //}
    }

    @Override
    public UserData getUser(String userName) throws DataAccessException {

        String passUsername = null;
        String passwordPasser = null;
        String emailPasser = null;

        try (var conn = DatabaseManager.getConnection()) {
            var statement = "SELECT * FROM user WHERE username=?";
            try (var ps = conn.prepareStatement(statement)) {
                ps.setString(1, userName);
                try (var rs = ps.executeQuery()) {
                    if (rs.next()) {
                        var username = rs.getString("username");
                        var password = rs.getString("password");
                        var email = rs.getString("email");

                        passUsername = username;
                        passwordPasser = password;
                        emailPasser = email;
                    }
                }
            }
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }

        String hashedPassword = BCrypt.hashpw(passwordPasser, BCrypt.gensalt());

        if (passUsername == null) {
            return null;
        }

        return new UserData(userName, hashedPassword, emailPasser);
    }

    @Override
    //Case sensitive ci 지워야할 수도
    public String[] createStatements() {
        return new String[]{
                """
                        CREATE TABLE IF NOT EXISTS  user (
                        `username` varchar(256) NOT NULL,
                        `password` varchar(256) NOT NULL,
                        `email` varchar(256) NOT NULL,
                        PRIMARY KEY (`username`)
                        ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
                        """
        };
    }
}
