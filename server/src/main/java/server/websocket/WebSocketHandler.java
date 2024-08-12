package server.websocket;

import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import websocket.commands.UserGameCommand;
import dataaccess.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class WebSocketHandler {

    Map<Integer, Set<Session>> connections = new HashMap<>();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {

        UserGameCommand userGameCommand = new Gson().fromJson(message, UserGameCommand.class);

        String authToken;
        if (userGameCommand.getAuthToken() != null) {
            authToken = userGameCommand.getAuthToken();
        }

        Integer gameID = userGameCommand.getGameID();

        switch (userGameCommand.getCommandType()) {
            case CONNECT -> connect();
            case MAKE_MOVE -> makeMove();
            case LEAVE -> leave();
            case RESIGN -> resign();
        }
    }

    private void connect() {
        var message = String.format("", );
        var notification = new UserGameCommand(UserGameCommand.CommandType.CONNECT, );
    }

    private void makeMove() {
    }

    private void leave() {
    }

    private void resign() {
    }

    @OnWebSocketClose
    public void onClose(int status, String message) {

    }

    @OnWebSocketError
    public void onError(Throwable e) {

    }
}
