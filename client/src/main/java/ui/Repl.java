package ui;

import data.DataStorage;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class Repl {


    public void run() {

        PreLogin preLogin = DataStorage.getInstance().getPreLogin();
        PostLogin postLogin = DataStorage.getInstance().getPostLogin();

        DataStorage.getInstance().setState(DataStorage.State.LOGGED_OUT);

        System.out.println("\n♕ Welcome to Chess. Sign in to start.\n\n" + SET_TEXT_COLOR_BLUE + preLogin.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                if (DataStorage.getInstance().getState().equals(DataStorage.State.LOGGED_OUT)) {
                    result = preLogin.eval(line).str();
                } else if (DataStorage.getInstance().getState().equals(DataStorage.State.LOGGED_IN)) {
                    result = postLogin.eval(line).str();
                }
                System.out.print(SET_TEXT_COLOR_BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        printPrompt();
    }

    private void printPrompt() {
        System.out.print("\n" + SET_TEXT_COLOR_WHITE + "[" + DataStorage.getInstance().getState() + "] " + ">>> " + SET_TEXT_COLOR_GREEN);
    }
}
