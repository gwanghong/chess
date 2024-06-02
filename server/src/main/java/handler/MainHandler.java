package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.Map;

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

    protected void badRequest(Response res) {
        res.status(400);
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("message", "Error: badRequest");
        halt(400, gson.toJson(errorRes));
    }

    protected void unauthorized(Response res) {
        res.status(401);
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("message", "Error: unauthorized");
        halt(401, gson.toJson(errorRes));
    }

    protected void alreadyTaken(Response res) {
        res.status(403);
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("message", "Error: already taken");
        halt(403, gson.toJson(errorRes));
    }

    protected void internalServerError(Response res, DataAccessException e) {
        res.status(500);
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("message", "Error: " + e.getMessage());
        halt(500, gson.toJson(errorRes));
    }

    protected record ErrorResponse(String message) {
    }
}
