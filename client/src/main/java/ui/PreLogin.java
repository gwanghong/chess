package ui;

import static ui.EscapeSequences.*;

public class PreLogin {

    public void help() {
        System.out.println(SET_TEXT_COLOR_BLUE + "    register <USERNAME> <PASSWORD> <EMAIL>" + RESET_TEXT_COLOR + " - to create an account");
        System.out.println(SET_TEXT_COLOR_BLUE + "    login <USERNAME> <PASSWORD>" + RESET_TEXT_COLOR + " - to play chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    quit" + RESET_TEXT_COLOR + " - playing chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    help" + RESET_TEXT_COLOR + " - with possible commands\n");
    }

    public boolean login(String[] input) {

        return false;
    }

    public void register(String[] input) {
    }
}
