package ui;

import static ui.EscapeSequences.*;

public class PostLogin {

    public void help() {
        System.out.println(SET_TEXT_COLOR_BLUE + "    create <NAME>" + RESET_TEXT_COLOR + " - a game");
        System.out.println(SET_TEXT_COLOR_BLUE + "    list" + RESET_TEXT_COLOR + " - games");
        System.out.println(SET_TEXT_COLOR_BLUE + "    join <ID> [WHITE|BLACK]" + RESET_TEXT_COLOR + " - a game");
        System.out.println(SET_TEXT_COLOR_BLUE + "    observe <ID>" + RESET_TEXT_COLOR + " - a game");
        System.out.println(SET_TEXT_COLOR_BLUE + "    logout" + RESET_TEXT_COLOR + " - when you are done");
        System.out.println(SET_TEXT_COLOR_BLUE + "    quit" + RESET_TEXT_COLOR + " - playing chess");
        System.out.println(SET_TEXT_COLOR_BLUE + "    help" + RESET_TEXT_COLOR + " - with possible commands\n");
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
