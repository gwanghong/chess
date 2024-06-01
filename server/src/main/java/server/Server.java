package server;

import dataaccess.AuthDAO;
import dataaccess.MemoryAuthDAO;
import dataaccess.MemoryUserDAO;
import dataaccess.UserDAO;
import handler.*;
import org.eclipse.jetty.server.Authentication;
import service.ClearService;
import service.UserService;
import spark.*;

import java.util.List;

public class Server {

    public static void main(String[] args) {
        new Server().run(8080);
    }

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        UserDAO userDao = new MemoryUserDAO();
        AuthDAO authDao = new MemoryAuthDAO();

        ClearService clearService = new ClearService();
        UserService userService = new UserService(userDao, authDao);

        Spark.delete("/db", new ClearHandler(clearService));
        Spark.post("/user", new RegisterHandler(userService));
        //Spark.post("/session", new LoginHandler());
        //Spark.delete("/session", new LogoutHandler());
        //Spark.get("/game", new ListGamesHandler());
        //Spark.post("/game", new CreateGameHanlder());
        //Spark.put("/game", new JoinGameHandler());

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
