package Facade;

import com.google.gson.Gson;
import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

public class ServerFacade {

    private final int port;
    private static String url;

    public ServerFacade(int port) {
        this.port = port;
        url = "http://localhost:" + port;
    }

    public AuthData login(UserData user) throws URISyntaxException, IOException {
        return sendRequest("/session", "POST", user, AuthData.class);
    }

    public void logout() throws URISyntaxException, IOException {
        sendRequest("/session", "DELETE", null, null);
    }

    public AuthData register(String username, String password, String email) throws URISyntaxException, IOException {

        UserData user = new UserData(username, password, email);

        return sendRequest("/user", "POST", user, AuthData.class);
    }

    public GameData createGame() throws URISyntaxException, IOException {
        return sendRequest("/game", "POST", null, GameData.class);
    }

    public Collection<GameData> listGames() throws URISyntaxException, IOException {
        return sendRequest("/game", "GET", null, Collection.class);
    }

    public void joinGame() throws URISyntaxException, IOException {
        sendRequest("/game", "PUT", null, null);
    }

    private <T> T sendRequest(String path, String method, Object request, Class<T> clazz) throws URISyntaxException, IOException {

        Gson gson = new Gson();

        URI uri = new URI(url + path);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(method);
        writeRequestBody(gson.toJson(request), http);

        http.connect();

        String response = readResponseBody(http);
        if (clazz == null) {
            return null;
        } else {
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
