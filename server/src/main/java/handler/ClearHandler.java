package handler;

import dataaccess.DataAccessException;
import service.ClearService;
import spark.Request;
import spark.Response;
import spark.Route;

public class ClearHandler implements Route {

    @Override
    public Object handle(Request request, Response response) throws DataAccessException {
        ClearService clearService = new ClearService();
        try {
            clearService.clear();
            response.status(200);
            return new Gson().toJson(new Object()); // Empty JSON response
        } catch (DataAccessException e) {
            response.status(500);
            return new Gson().toJson(Map.of("message", "Error: " + e.getMessage()));
        }
    }
}
