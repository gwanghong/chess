package Facade;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Map;

public class ServerFacade {

    private final int port;
    private static String url;

    public ServerFacade(int port) {
        this.port = port;
        url = "http://localhost:" + port;
    }

    public AuthData login(UserData user) {
        AuthData auth = ;
    }

    public void logout() {

    }

    public AuthData register(UserData user) {

    }

    public GameData createGame() {

    }

    public Collection<GameData> listGames() {

    }

    public void joinGame() {

    }

    private <T> T sendRequest(String path, String method, Object request, Class<T> clazz) throws URISyntaxException, IOException {

        Gson gson = new Gson();

        URI uri = new URI(url);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(method);
        writeRequestBody(gson.toJson(request), http);

        http.connect();

        String response = readResponseBody(http);
        if (clazz == null) {
            return null;
        }
        else {
            return gson.fromJson(response, clazz);
        }
    }

    private static void writeRequestBody(String body, HttpURLConnection http) throws IOException {
        if (!body.isEmpty()) {
            http.setDoOutput(true);
            try (var outputStream = http.getOutputStream()) {
                outputStream.write(body.getBytes());
            }
        }
    }

    private static String readResponseBody(HttpURLConnection http) throws IOException {
        String responseBody = "";
        try (InputStream respBody = http.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            responseBody = new Gson().fromJson(inputStreamReader, String.class);
        }
        return responseBody;
    }

}
