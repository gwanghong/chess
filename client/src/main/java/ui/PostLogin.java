package ui;

import Facade.ServerFacade;
import chess.ChessGame;
import data.DataStorage;
import model.GameData;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

import static ui.EscapeSequences.*;

public class PostLogin {

    //private ServerFacade facade;

    public Combo eval(String input) {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch (cmd) {
            case "h", "help" -> new Combo(help(), true);
            case "l", "logout" -> logout();
            case "c", "create" -> createGame(params);
            case "quit" -> new Combo("quit", true);
            default -> new Combo(help(), true);
        };
    }

    public String help() {
        return """
                    create <NAME>" + RESET_TEXT_COLOR + " - a game
                    list" + RESET_TEXT_COLOR + " - games
                    join <ID> [WHITE|BLACK]" + RESET_TEXT_COLOR + " - a game
                    observe <ID>" + RESET_TEXT_COLOR + " - a game
                    logout" + RESET_TEXT_COLOR + " - when you are done
                    quit" + RESET_TEXT_COLOR + " - playing chess
                    help" + RESET_TEXT_COLOR + " - with possible commands
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

        try {
            DataStorage.getInstance().getFacade().createGame(game);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        return new Combo("Game " + input[0] + " created", true);
    }

    public void listGames() {
    }

    public void playGame(String[] input) {
        DisplayBoard.callMain();
    }

    public void observe(String[] input) {
        DisplayBoard.callMain();
    }
}
