package handler;

import com.google.gson.Gson;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import model.AuthData;
import service.UserService;
import spark.Request;
import spark.Response;

public class LogoutHandler extends MainHandler {

    private final UserService userService;

    public LogoutHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        try {
            String authToken = request.headers("authorization");

            if (authToken == null) {
                throw new DataAccessException("authToken is null");
            }

            userService.logout(authToken);
            response.status(200);
            return new Gson().toJson(new Object());

        } catch (DataAccessException e) {
            internalServerError(response, e.getMessage());
        }

        return null;
    }
}
