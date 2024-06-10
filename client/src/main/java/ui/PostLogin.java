package ui;

public class PostLogin {

    String helpTextColor = EscapeSequences.SET_TEXT_COLOR_BLUE;
    String resetTextColor = EscapeSequences.RESET_TEXT_COLOR;

    public void help() {
        System.out.println(helpTextColor + "    create <NAME>" + resetTextColor + " - a game");
        System.out.println(helpTextColor + "    list" + resetTextColor + " - games");
        System.out.println(helpTextColor + "    join <ID> [WHITE|BLACK]" + resetTextColor + " - a game");
        System.out.println(helpTextColor + "    observe <ID>" + resetTextColor + " - a game");
        System.out.println(helpTextColor + "    logout" + resetTextColor + " - when you are done");
        System.out.println(helpTextColor + "    quit" + resetTextColor + " - playing chess");
        System.out.println(helpTextColor + "    help" + resetTextColor + " - with possible commands\n");
    }

    public void logout() {
    }

    public void createGame(String[] input) {
    }

    public void listGames() {
    }

    public void playGame(String[] input) {
    }

    public void observe(String[] input) {
    }

    public void quit() {

    }
}
