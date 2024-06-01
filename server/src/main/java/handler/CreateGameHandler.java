package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.GameData;
import model.UserData;
import service.GameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler extends MainHandler {

    private final GameService gameService;

    public CreateGameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {

        try {
            String authToken = request.headers("authorization");
            GameData req = getBody(request, GameData.class);
            GameData result = gameService.createGame(authToken, req.gameName());

            response.status(200);
            return new Gson().toJson(result.gameID());

        } catch (DataAccessException e) {
            internalServerError(response, e.getMessage());
        }

        return null;
    }
}
