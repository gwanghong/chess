package handler;

import com.google.gson.Gson;
import dataaccess.DataAccessException;
import model.GameData;
import service.GameService;
import spark.Request;
import spark.Response;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ListGamesHandler extends MainHandler {

    private final GameService gameService;


    public ListGamesHandler(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public Object handle(Request request, Response response) {
        try {
            String authToken = request.headers("authorization");
            Collection<GameData> result = gameService.listGames(authToken);
            response.status(200);

            Map<String, Collection<GameData>> combineRes = new HashMap<>();
            combineRes.put("games", result);

            return new Gson().toJson(combineRes);

        } catch (DataAccessException e) {
            unauthorized(response);
        } catch (RuntimeException e) {
            internalServerError(response, e);
        }

        return null;
    }
}
