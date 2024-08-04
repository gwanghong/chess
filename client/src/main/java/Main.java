import ui.Repl;

public class Main {
    public static void main(String[] args) {

        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        new Repl(serverUrl).run();

        /*PreLogin preLogin = new PreLogin();
        PostLogin postLogin = new PostLogin();
        boolean isLogged = false;

        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        System.out.println("♕ Welcome to 240 chess. Type Help to get started ♕");

        label:
        while (true) {

            if (!isLogged) {
                System.out.print("[LOGGED_OUT] >>> ");
            } else {
                System.out.print("[LOGGED_IN] >>> ");
            }

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] splitInput = userInput.split(" ");
            String lowerCaseUI = splitInput[0].toLowerCase();

            if (!isLogged) {

                switch (lowerCaseUI) {
                    case "help", "h" -> preLogin.help();
                    case "quit", "q" -> {
                        break label;
                    }
                    case "login", "l" -> {
                        if (splitInput.length != 3) {
                            System.out.println(SET_TEXT_ITALIC + "Wrong input length for Login");
                        } else {
                            isLogged = preLogin.login(splitInput);
                        }
                    }
                    case "register", "r" -> {
                        if (splitInput.length != 4) {
                            System.out.println(SET_TEXT_ITALIC + "Wrong input length for Register");
                        } else {
                            preLogin.register(splitInput);
                        }
                    }
                    default ->
                            System.out.println(SET_TEXT_ITALIC + "Wrong command input. Type (h)elp to see the commands");
                }
            } else {

                switch (lowerCaseUI) {
                    case "help", "h" -> postLogin.help();
                    case "logout", "lo" -> postLogin.logout();
                    case "create", "c" -> {
                        if (splitInput.length != 2) {
                            System.out.println(SET_TEXT_ITALIC + "Wrong input length for create");
                        } else {
                            postLogin.createGame(splitInput);
                        }
                    }
                    case "list", "li" -> postLogin.listGames();
                    case "join", "j" -> {
                        if (splitInput.length != 3) {
                            System.out.println(SET_TEXT_ITALIC + "Wrong input length for join");
                        } else {
                            postLogin.playGame(splitInput);
                        }
                    }
                    case "observe", "o" -> {
                        if (splitInput.length != 2) {
                            System.out.println(SET_TEXT_ITALIC + "Wrong input length for observe");
                        } else {
                            postLogin.observe(splitInput);
                        }
                    }
                    case "quit", "q" -> postLogin.quit();
                    default ->
                            System.out.println(SET_TEXT_ITALIC + "Wrong command input. Type (h)elp to see the commands");
                }
            }

        }*/
    }
}