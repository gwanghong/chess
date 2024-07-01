package ui;

import static ui.EscapeSequences.*;

import model.UserData;
import Facade.ServerFacade;

public class PreLogin {

    ServerFacade facade;

    public void help() {
        System.out.println(SET_TEXT_COLOR_BLUE + "    register <USERNAME> <PASSWORD> <EMAIL>" + RESET_TEXT_COLOR + " - to create an account");
        System.out.println(SET_TEXT_COLOR_BLUE + "    login <USERNAME> <PASSWORD>" + RESET_TEXT_COLOR + " - to play chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    quit" + RESET_TEXT_COLOR + " - playing chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    help" + RESET_TEXT_COLOR + " - with possible commands\n");
    }

    public boolean login(String[] input) {

        try {
            UserData user = new UserData(input[1], input[2], input[3]);
            facade.login(user);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void register(String[] input) {

        UserData user = new UserData(input[1], input[2], input[3]);
        facade.register(user);
    }
}
