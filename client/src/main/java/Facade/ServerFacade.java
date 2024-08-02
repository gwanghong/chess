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

    private int port;

    public ServerFacade(int port) {
        this.port = port;

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

    public static void main(String[] args) throws Exception {
        if (args.length >= 2) {
            var method = args[0];
            var url = args[1];
            var body = args.length == 3 ? args[2] : "";

            HttpURLConnection http = sendRequest(url, method, body);
            receiveResponse(http);
        } else {
            System.out.println("ClientCurlExample <method> <url> [<body>]");
        }
    }

    private static HttpURLConnection sendRequest(String url, String method, String body) throws URISyntaxException, IOException {
        URI uri = new URI(url);
        HttpURLConnection http = (HttpURLConnection) uri.toURL().openConnection();
        http.setRequestMethod(method);
        writeRequestBody(body, http);
        http.connect();
        System.out.printf("= Request =========\n[%s] %s\n\n%s\n\n", method, url, body);
        return http;
    }

    private static void writeRequestBody(String body, HttpURLConnection http) throws IOException {
        if (!body.isEmpty()) {
            http.setDoOutput(true);
            try (var outputStream = http.getOutputStream()) {
                outputStream.write(body.getBytes());
            }
        }
    }

    private static void receiveResponse(HttpURLConnection http) throws IOException {
        var statusCode = http.getResponseCode();
        var statusMessage = http.getResponseMessage();

        Object responseBody = readResponseBody(http);
        System.out.printf("= Response =========\n[%d] %s\n\n%s\n\n", statusCode, statusMessage, responseBody);
    }

    private static Object readResponseBody(HttpURLConnection http) throws IOException {
        Object responseBody = "";
        try (InputStream respBody = http.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(respBody);
            responseBody = new Gson().fromJson(inputStreamReader, Map.class);
        }
        return responseBody;
    }


}
