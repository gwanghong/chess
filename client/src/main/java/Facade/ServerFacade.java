package Facade;

import com.google.gson.Gson;
import model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

    public AuthData login(String username, String password) throws URISyntaxException, IOException {

        UserData user = new UserData(username, password, null);

        return this.sendRequest("/session", "POST", user, AuthData.class);
    }

    public void logout() throws URISyntaxException, IOException {
        this.sendRequest("/session", "DELETE", null, null);
    }

    public AuthData register(String username, String password, String email) throws URISyntaxException, IOException {

        UserData user = new UserData(username, password, email);

        return this.sendRequest("/user", "POST", user, AuthData.class);
    }

    public GameData createGame() throws URISyntaxException, IOException {
        return this.sendRequest("/game", "POST", null, GameData.class);
    }

    public Collection<GameData> listGames() throws URISyntaxException, IOException {
        return this.sendRequest("/game", "GET", null, Collection.class);
    }

    public void joinGame() throws URISyntaxException, IOException {
        this.sendRequest("/game", "PUT", null, null);
    }

    private <T> T sendRequest(String path, String method, Object request, Class<T> clazz) throws URISyntaxException, IOException {
        Gson gson = new Gson();
        URI uri = new URI(url + path);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(method);

        if (request != null) {
            writeRequestBody(gson.toJson(request), http);
        }

        http.connect();

        String response = readResponseBody(http);
        if (clazz == null) {
            return null;
        } else {
            return gson.fromJson(response, clazz);
        }
    }

    private static void writeRequestBody(String body, HttpURLConnection http) throws IOException {
        if (body != null && !body.isEmpty()) {
            http.setDoOutput(true);
            try (OutputStream outputStream = http.getOutputStream()) {
                outputStream.write(body.getBytes());
                outputStream.flush();
            }
        }
    }

    private static String readResponseBody(HttpURLConnection http) throws IOException {
        StringBuilder responseBody = new StringBuilder();
        try (InputStream respBody = http.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(respBody))) {
            String line;
            while ((line = reader.readLine()) != null) {
                responseBody.append(line);
            }
        }
        return responseBody.toString();
    }
}
