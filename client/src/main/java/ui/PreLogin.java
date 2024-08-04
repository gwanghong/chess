package ui;

import static ui.EscapeSequences.*;

import Facade.ServerFacade;
import data.DataStorage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;

public class PreLogin {

    private final ServerFacade facade;

    public PreLogin(int port) {
        this.facade = new ServerFacade(port);
    }

    public void help() {
        System.out.println(SET_TEXT_COLOR_BLUE + "    register <USERNAME> <PASSWORD> <EMAIL>" + SET_TEXT_COLOR_WHITE + " - to create an account");
        System.out.println(SET_TEXT_COLOR_BLUE + "    login <USERNAME> <PASSWORD>" + SET_TEXT_COLOR_WHITE + " - to play chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    quit" + SET_TEXT_COLOR_WHITE + " - playing chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    help" + SET_TEXT_COLOR_WHITE + " - with possible commands\n");
    }

    public boolean login(String[] input) {

        try {
            facade.login(input[1], input[2]);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Wrong input, try again");
            return false;
        }

        return true;
    }

    public void register(String[] input) {

        System.out.println(Arrays.toString(input));

        try {
            facade.register(input[1], input[2], input[3]);
        } catch (IOException | URISyntaxException e) {
            System.out.println("Wrong input, try again");
        }

        System.out.println("Register Successful");
    }
}
