package client;

import com.google.gson.Gson;
import data.DataStorage;
import data.ListGameResponse;
import model.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;

public class ServerFacade {

    private static String url;

    public ServerFacade(String url) {
        ServerFacade.url = url;
    }

    public AuthData login(String username, String password) throws URISyntaxException, IOException {

        UserData user = new UserData(username, password, null);
        AuthData auth = this.makeRequest("/session", "POST", user, AuthData.class);
        DataStorage.getInstance().setAuthToken(auth.authToken());
        return auth;
    }

    public void logout() throws URISyntaxException, IOException {
        //AuthData auth = new AuthData(DataStorage.getInstance().getAuthToken(), null);
        AuthData auth = new AuthData(DataStorage.getInstance().getAuthToken(), null);
        this.makeRequest("/session", "DELETE", auth, null);
    }

    public AuthData register(String username, String password, String email) throws URISyntaxException, IOException {

        UserData user = new UserData(username, password, email);
        AuthData auth = this.makeRequest("/user", "POST", user, AuthData.class);
        DataStorage.getInstance().setAuthToken(auth.authToken());
        return auth;
    }

    public GameData createGame(GameData game) throws URISyntaxException, IOException {
        return this.makeRequest("/game", "POST", game, GameData.class);
    }

    public Collection<GameData> listGames() throws URISyntaxException, IOException {

        ListGameResponse response = this.makeRequest("/game", "GET", null, ListGameResponse.class);
        return response.getGames();
    }

    public void joinGame(JoinGameData joinCombined) throws URISyntaxException, IOException {
        this.makeRequest("/game", "PUT", joinCombined, null);
    }

    private <T> T makeRequest(String path, String method, Object request, Class<T> responseClass) {

        try {
            URL url = (new URI(ServerFacade.url + path)).toURL();
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod(method);
            http.setDoOutput(true);

            writeBody(request, http);
            http.connect();
            throwIfNotSuccessful(http);
            return readBody(http, responseClass);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }


    private static void writeBody(Object request, HttpURLConnection http) throws IOException {

        String authToken = DataStorage.getInstance().getAuthToken();

        if (authToken != null) {
            http.addRequestProperty("authorization", authToken);
            http.setRequestProperty("authorization", authToken);
        }

        if (request != null) {
            http.addRequestProperty("Content-Type", "application/json");

            String reqData = new Gson().toJson(request);
            try (OutputStream reqBody = http.getOutputStream()) {
                reqBody.write(reqData.getBytes());
            }
        }
    }

    private void throwIfNotSuccessful(HttpURLConnection http) throws Exception {
        var status = http.getResponseCode();
        if (!isSuccessful(status)) {
            throw new Exception("HTTP request failed with status code: " + status + ", message: " + http.getResponseMessage());
        }
    }

    private static <T> T readBody(HttpURLConnection http, Class<T> responseClass) throws IOException {
        T response = null;
        if (http.getContentLength() < 0) {
            try (InputStream respBody = http.getInputStream()) {
                InputStreamReader reader = new InputStreamReader(respBody);
                if (responseClass != null) {
                    response = new Gson().fromJson(reader, responseClass);
                }
            }
        }
        return response;
    }


    private boolean isSuccessful(int status) {
        return status / 100 == 2;
    }

}
