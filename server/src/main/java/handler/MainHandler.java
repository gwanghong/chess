package handler;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import static spark.Spark.halt;

public abstract class MainHandler implements Route {

    protected Gson gson = new Gson();


    protected <T> T getBody(Request req, Class<T> clazz) {

        var body = new Gson().fromJson(req.body(), clazz);

        if (body == null) {
            throw new RuntimeException("missing required body");
        }

        return body;
    }

    protected void unauthorized(Response res, String message) {
        res.status(401);
        halt(401, gson.toJson(new ErrorResponse(message)));
    }

    protected void badRequest(Response res, String message) {
        res.status(400);
        halt(400, gson.toJson(new ErrorResponse(message)));
    }

    protected void alreadyTaken(Response res, String message) {
        res.status(403);
        halt(403, gson.toJson(new ErrorResponse(message)));
    }

    protected void internalServerError(Response res, String message) {
        res.status(500);
        halt(500, gson.toJson(new ErrorResponse(message)));
    }

    protected record ErrorResponse(String message) {
    }
}
