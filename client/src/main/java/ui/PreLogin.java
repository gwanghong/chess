package ui;

import static ui.EscapeSequences.*;

import Facade.ServerFacade;
import ui.Combo;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class PreLogin {

    private final ServerFacade facade;

    public PreLogin(String url) {
        this.facade = new ServerFacade(url);
    }

    public Combo eval(String input) {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch (cmd) {
            case "h", "help" -> new Combo(help(), true);
            case "l", "login" -> login(params);
            case "r", "register" -> register(params);
            case "quit" -> new Combo("quit", true);
            default -> new Combo(help(), true);
        };
    }

    public String help() {
        return """
                   register <USERNAME> <PASSWORD> <EMAIL> - to create an account"
                   login <USERNAME> <PASSWORD> - to play chess
                   quit - playing chess
                   help - with possible commands
                """;
    }

    public Combo login(String[] input) {

        try {
            facade.login(input[0], input[1]);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        return new Combo("Login Success", true);
    }

    public Combo register(String[] input) {

        System.out.println(Arrays.toString(input));

        try {
            facade.register(input[0], input[1], input[2]);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        return new Combo("Register Success", true);
    }
}
