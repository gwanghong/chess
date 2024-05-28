package server;

import handler.*;
import spark.*;

public class Server {

    public int run(int desiredPort) {
        Spark.port(desiredPort);

        Spark.staticFiles.location("web");

        // Register your endpoints and handle exceptions here.
        Clear();

        Spark.awaitInitialization();
        return Spark.port();
    }

    public void Clear() {
        Spark.delete("/db", (req, res) -> new ClearHandler());
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
