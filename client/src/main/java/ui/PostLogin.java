package ui;

import Facade.ServerFacade;

import java.util.Arrays;

import static ui.EscapeSequences.*;

public class PostLogin {

    private final ServerFacade facade;
    private final String serverUrl;

    public PostLogin(String serverUrl) {
        facade = new ServerFacade(serverUrl);
        this.serverUrl = serverUrl;
    }

    public Combo eval(String input) {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch (cmd) {
            case "h", "help" -> new Combo(help(), true);

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

    public void logout() {
    }

    public void createGame(String[] input) {
    }

    public void listGames() {
    }

    public void playGame(String[] input) {
        DisplayBoard.callMain();
    }

    public void observe(String[] input) {
        DisplayBoard.callMain();
    }

    public void quit() {

    }
}
