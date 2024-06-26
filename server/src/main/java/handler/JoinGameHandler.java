package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.JoinGameData;
import service.GameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler extends MainHandler {

    private final GameService gameService;

    public JoinGameHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            JoinGameData req = getBody(request, JoinGameData.class);
            String color = String.valueOf(req.playerColor());
            gameService.joinGame(authToken, color, req.gameID());

            return new Gson().toJson(new Object());
        } catch (DataAccessException e) {
            unauthorized(response);
        } catch (IllegalArgumentException e) {
            alreadyTaken(response);
        } catch (Exception e) {
            badRequest(response);
        }

        return null;
    }
}
