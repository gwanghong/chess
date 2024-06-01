package server;

import dataaccess.*;
import handler.*;
import org.eclipse.jetty.server.Authentication;
import service.ClearService;
import service.GameService;
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
        GameDAO gameDao = new MemoryGameDAO();

        ClearService clearService = new ClearService();
        UserService userService = new UserService(userDao, authDao);
        GameService gameService = new GameService(gameDao, authDao);

        Spark.delete("/db", new ClearHandler(clearService));
        Spark.post("/user", new RegisterHandler(userService));
        Spark.post("/session", new LoginHandler(userService));
        Spark.delete("/session", new LogoutHandler(userService));
        Spark.post("/game", new CreateGameHandler(gameService));
        Spark.get("/game", new ListGamesHandler(gameService));
        Spark.put("/game", new JoinGameHandler(gameService));

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
