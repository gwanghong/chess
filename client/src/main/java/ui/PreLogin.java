package ui;

public class PreLogin {

    String helpTextColor = EscapeSequences.SET_TEXT_COLOR_BLUE;
    String resetTextColor = EscapeSequences.RESET_TEXT_COLOR;

    public void help() {
        System.out.println(helpTextColor + "    register <USERNAME> <PASSWORD> <EMAIL>" + resetTextColor + " - to create an account");
        System.out.println(helpTextColor + "    login <USERNAME> <PASSWORD>" + resetTextColor + " - to play chess");
        System.out.println(helpTextColor + "    quit" + resetTextColor + " - playing chess");
        System.out.println(helpTextColor + "    help" + resetTextColor + " - with possible commands\n");
    }

    public boolean login(String[] input) {

        return false;
    }

    public void register(String[] input) {
    }
}
