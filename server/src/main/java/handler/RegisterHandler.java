package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import service.UserService;
import spark.Request;
import spark.Response;
import model.UserData;

import java.util.Map;

public class RegisterHandler extends MainHandler {

    private final UserService userService;

    public RegisterHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            UserData req = getBody(request, UserData.class);
            AuthData result = userService.register(req);
            response.status(200);

            return new Gson().toJson(result);

        } catch (DataAccessException e) {
            internalServerError(response, "Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            badRequest(response, "Error: bad request");
        } catch (Exception e) {
            internalServerError(response, "Error: already taken");
        }
        return null;
    }
}
