package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.AuthData;
import service.UserService;
import spark.Request;
import spark.Response;
import model.UserData;

import javax.management.BadAttributeValueExpException;

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
            alreadyTaken(response);
        } catch (Exception e) {
            badRequest(response);
        }
        return null;
    }
}
