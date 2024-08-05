package ui;

import java.util.Scanner;

import static ui.EscapeSequences.*;

public class Repl {

    private final PreLogin prelogin;

    public Repl(String serverUrl) {
        prelogin = new PreLogin(serverUrl);
    }

    public void run() {
        System.out.println("♕ Welcome to Chess. Sign in to start.\n");
        System.out.print(SET_TEXT_COLOR_BLUE + prelogin.help());

        Scanner scanner = new Scanner(System.in);
        var result = "";
        while (!result.equals("quit")) {
            printPrompt();
            String line = scanner.nextLine();

            try {
                result = prelogin.eval(line).str();
                System.out.print(SET_TEXT_COLOR_BLUE + result);
            } catch (Throwable e) {
                var msg = e.toString();
                System.out.print(msg);
            }
        }
        System.out.println();
    }

    private void printPrompt() {
        System.out.print("\n" + SET_TEXT_COLOR_WHITE + ">>> " + SET_TEXT_COLOR_GREEN);
    }
}
