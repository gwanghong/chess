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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WebSocketHandler {

    Map<Integer, Set<Session>> connections = new HashMap<>();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) {

        UserGameCommand userGameCommand = new Gson().fromJson(message, UserGameCommand.class);

        String authToken = null;
        if (userGameCommand.getAuthToken() != null) {
            authToken = userGameCommand.getAuthToken();
        }

        Integer gameID = userGameCommand.getGameID();

        switch (userGameCommand.getCommandType()) {
            case CONNECT -> connect(gameID, authToken);
            case MAKE_MOVE -> makeMove();
            case LEAVE -> leave(gameID, authToken);
            case RESIGN -> resign(gameID, authToken);
        }
    }

    private void connect(Integer gameID, String authToken) {
        connections.put(gameID, new HashSet<>());
        var notification = new UserGameCommand(UserGameCommand.CommandType.CONNECT, authToken, gameID);
        broadcast(gameID, notification);
    }

    private void makeMove() {
    }

    private void leave(Integer gameID, String authToken) {
        connections.remove(gameID);
        var notification = new UserGameCommand(UserGameCommand.CommandType.CONNECT, authToken, gameID);
        broadcast(gameID, notification);
    }

    private void resign(Integer gameID, String authToken) {
        connections.remove(gameID);
        var notification = new UserGameCommand(UserGameCommand.CommandType.CONNECT, authToken, gameID);
        broadcast(gameID, notification);
    }

    public void broadcast(Integer gameID, UserGameCommand notification) {

    }

    @OnWebSocketClose
    public void onClose(int status, String message) {

    }

    @OnWebSocketError
    public void onError(Throwable e) {

    }
}
