package dataaccess;

import model.UserData;
import org.mindrot.jbcrypt.BCrypt;

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
    public void insertUser(UserData u) {
        
        var statement = "INSERT INTO user (username, password, email) VALUES (?, ?, ?)";
        String hashedPassword = BCrypt.hashpw(u.password(), BCrypt.gensalt());

        try {
            executeUpdate(statement, u.username(), hashedPassword, u.email());
        } catch (DataAccessException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    @Override
    public boolean verifyUser(String username, String password) throws DataAccessException {

        var hashedPassword = getUser(username).password();

        return BCrypt.checkpw(password, hashedPassword);
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

        if (passUsername == null) {
            return null;
        }

        return new UserData(userName, passwordPasser, emailPasser);
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
