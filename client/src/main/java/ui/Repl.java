package ui;

import data.DataStorage;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class Repl {

    private final PreLogin prelogin;
    private final PostLogin postLogin;


    public Repl(String serverUrl) {
        prelogin = new PreLogin(serverUrl);
        postLogin = new PostLogin(serverUrl);
    }

    public void run() {

        DataStorage.getInstance().setState(DataStorage.State.LOGGED_OUT);

        System.out.println("♕ Welcome to Chess. Sign in to start.\n");
        System.out.print(SET_TEXT_COLOR_BLUE + prelogin.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                if (DataStorage.getInstance().getState().equals(DataStorage.State.LOGGED_OUT)) {
                    result = prelogin.eval(line).str();
                } else if (DataStorage.getInstance().getState().equals(DataStorage.State.LOGGED_IN)) {
                    result = postLogin.eval(line).str();
                }
                System.out.print(SET_TEXT_COLOR_BLUE + result);
            } catch (Throwable e) {
                //var msg = e.toString();
                //System.out.print(msg);
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    private void printPrompt() {
        System.out.print("\n" + SET_TEXT_COLOR_WHITE + "[" + DataStorage.getInstance().getState() + "]" + ">>> " + SET_TEXT_COLOR_GREEN);
    }
}
