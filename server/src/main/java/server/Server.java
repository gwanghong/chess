package server;

import handler.ClearHandler;
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
        Spark.delete("/db", (req, res) -> new ClearHandler() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return null;
            }
        });
    }

    public void stop() {
        Spark.stop();
        Spark.awaitStop();
    }
}
