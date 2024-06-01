package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import service.UserService;
import spark.Request;
import spark.Response;
import model.UserData;

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

        } catch (IllegalArgumentException e) {
            badRequest(response);
        } catch (RuntimeException e) {
            alreadyTaken(response);
        } catch (DataAccessException e) {
            internalServerError(response, "Error: " + e.getMessage());
        }
        return null;
    }
}
