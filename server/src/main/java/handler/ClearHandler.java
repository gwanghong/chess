package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import service.ClearService;
import spark.Request;
import spark.Response;

public class ClearHandler extends MainHandler {

    private final ClearService clearService;

    public ClearHandler(ClearService clearService) {
        this.clearService = clearService;
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            clearService.clear();
            response.status(200);
            return new Gson().toJson(new Object());
        } catch (DataAccessException e) {
            internalServerError(response, "Error: " + e.getMessage());
            return null;
        }
    }
}
