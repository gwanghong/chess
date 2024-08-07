package ui;

import Facade.ServerFacade;
import chess.ChessGame;
import data.DataStorage;
import model.GameData;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import static ui.EscapeSequences.*;

public class PostLogin {

    //private ServerFacade facade;

    public Combo eval(String input) {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch (cmd) {
            case "h", "help" -> new Combo(help(), true);
            case "log", "logout" -> logout();
            case "c", "create" -> createGame(params);
            case "l", "list" -> listGames();
            case "quit" -> new Combo("quit", true);
            default -> new Combo(help(), true);
        };
    }

    public String help() {
        return """
                    create <NAME>"  - a game
                    list - games
                    join <ID> [WHITE|BLACK] - a game
                    observe <ID> - a game
                    logout - when you are done
                    quit - playing chess
                    help - with possible commands
                """;
    }

    public Combo logout() {

        try {
            DataStorage.getInstance().getFacade().logout();
        } catch (IOException | URISyntaxException e) {
            return new Combo("Logout Failure", false);
        }

        DataStorage.getInstance().setState(DataStorage.State.LOGGED_OUT);
        return new Combo("Logout Success", true);
    }

    public Combo createGame(String[] input) {

        GameData game = new GameData(0, null, null, input[0], new ChessGame());
        GameData gameRes;
        try {
            gameRes = DataStorage.getInstance().getFacade().createGame(game);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        return new Combo("Game " + input[0] + " created. GameID is " + gameRes.gameID(), true);
    }

    public Combo listGames() {

        Collection<GameData> gameList = new HashSet<>();

        try {
            gameList = DataStorage.getInstance().getFacade().listGames();
        } catch (IOException | URISyntaxException e) {
            return new Combo("Error occurred for listing Games", false);
        }

        int count = 0;
        StringBuilder out = new StringBuilder();
        out.append("Game List:\n");


        for (GameData game : gameList) {
            count++;
            out.append(count).append(". GameName: ");
            out.append(game.gameName()).append(" GameID: ");
            out.append(game.gameID());

            if (game.whiteUsername() != null) {
                out.append(" WhiteUser: ").append(game.whiteUsername());
            }
            if (game.whiteUsername() != null) {
                out.append(" BlackUser: ").append(game.blackUsername());
            }
            out.append("\n");
        }

        return new Combo(out.toString(), true);
    }

    public void playGame(String[] input) {
        DisplayBoard.callMain();
    }

    public void observe(String[] input) {
        DisplayBoard.callMain();
    }
}
