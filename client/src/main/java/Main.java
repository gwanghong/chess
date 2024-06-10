import chess.*;
import ui.*;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        PreLogin preLogin = new PreLogin();
        PostLogin postLogin = new PostLogin();
        boolean isLogged = false;

        var serverUrl = "http://localhost:8080";
        if (args.length == 1) {
            serverUrl = args[0];
        }

        System.out.println("♕ Welcome to 240 chess. Type Help to get started ♕");

        while (true) {

            Scanner scanner = new Scanner(System.in);
            String[] userInput = new String[]{scanner.nextLine()};
            String lowerCaseUI = userInput[0].toLowerCase();

            if (!isLogged) {

                System.out.print("[LOGGED_OUT] >>> ");

                if (lowerCaseUI.equals("help") || lowerCaseUI.equals("h")) {
                    preLogin.help();
                } else if (lowerCaseUI.equals("quit") || lowerCaseUI.equals("q")) {
                    break;
                } else if (lowerCaseUI.equals("login") || lowerCaseUI.equals("l")) {
                    isLogged = preLogin.login();
                } else if (lowerCaseUI.equals("register") || lowerCaseUI.equals("r")) {
                    preLogin.register();
                } else {
                    System.out.println(EscapeSequences.SET_TEXT_ITALIC + "Wrong input. Type (h)elp to see the commands");
                }
            } else {

                System.out.print("[LOGGED_IN] >>> ");

                if (lowerCaseUI.equals("help")) {
                    postLogin.help();
                }
            }

        }
        //var piece = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);
        //System.out.println("♕ 240 Chess Client: " + piece);
    }
}