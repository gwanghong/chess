package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import model.UserData;
import service.UserService;
import spark.Request;
import spark.Response;

public class LoginHandler extends MainHandler {

    private final UserService userService;

    public LoginHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            UserData req = getBody(request, UserData.class);
            AuthData result = userService.login(req);
            response.status(200);

            return new Gson().toJson(result);

        } catch (IllegalArgumentException e) {
            unauthorized(response, "Error: unauthorized");
        } catch (DataAccessException e) {
            internalServerError(response, "Error: " + e.getMessage());
        }

        return null;
    }
}
