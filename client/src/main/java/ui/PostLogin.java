package ui;

import chess.ChessGame;
import data.DataStorage;
import model.GameData;
import model.JoinGameData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.Integer.parseInt;

public class PostLogin {

    //private ServerFacade facade;

    public Combo eval(String input) throws URISyntaxException, IOException {
        var tokens = input.toLowerCase().split(" ");
        var cmd = (tokens.length > 0) ? tokens[0] : "help";
        var params = Arrays.copyOfRange(tokens, 1, tokens.length);
        return switch (cmd) {
            case "log", "logout" -> logout();
            case "c", "create" -> createGame(params);
            case "l", "list" -> listGames();
            case "j", "join", "play" -> playGame(params);
            case "o", "observe" -> observe(params);
            case "quit" -> new Combo("quit", true);
            default -> new Combo(help(), true);
        };
    }

    public String help() {
        return """
                    create <NAME>"  - a game
                    list - games
                    join <ID> [WHITE|BLACK] - a game
                    observe <ID> - a game
                    logout - when you are done
                    quit - playing chess
                    help - with possible commands
                """;
    }

    public Combo logout() {

        try {
            DataStorage.getInstance().getFacade().logout();
        } catch (IOException | URISyntaxException e) {
            return new Combo("Logout Failure", false);
        }

        DataStorage.getInstance().setState(DataStorage.State.LOGGED_OUT);
        return new Combo("Logout Success", true);
    }

    public Combo createGame(String[] input) {

        if (input.length != 1) {
            return new Combo("Wrong input length, try again", false);
        }

        GameData game = new GameData(0, null, null, input[0], new ChessGame());
        GameData gameRes;
        try {
            gameRes = DataStorage.getInstance().getFacade().createGame(game);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        return new Combo("Game " + input[0] + " created.", true);
    }

    public Combo listGames() {

        Collection<GameData> gameList;

        try {
            gameList = DataStorage.getInstance().getFacade().listGames();
        } catch (IOException | URISyntaxException e) {
            return new Combo("Error occurred for listing Games", false);
        }

        int count = 0;
        StringBuilder out = new StringBuilder();
        out.append("Game List:\n");

        Map<Integer, GameData> listGames = new HashMap<>();

        for (GameData game : gameList) {
            count++;

            listGames.put(count, game);

            out.append(count).append(". GameName: ");
            out.append(game.gameName());

            if (game.whiteUsername() != null) {
                out.append(" | WhitePlayer: ").append(game.whiteUsername());
            } else {
                out.append(" | WhitePlayer: ").append("empty");
            }
            if (game.whiteUsername() != null) {
                out.append(" | BlackPlayer: ").append(game.blackUsername());
            } else {
                out.append(" | BlackPlayer: ").append("empty");
            }
            out.append("\n");
        }

        DataStorage.getInstance().setListGames(listGames);

        return new Combo(out.toString(), true);
    }

    public Combo playGame(String[] input) throws URISyntaxException, IOException {

        if (input.length != 2) {
            return new Combo("Wrong input length, try again", false);
        }

        int listNum = parseInt(input[0]);
        Map<Integer, GameData> listGames = DataStorage.getInstance().getListGames();
        GameData game = listGames.get(listNum);
        int id = game.gameID();

        ChessGame.TeamColor teamColor;
        if (input[1].equalsIgnoreCase("white")) {
            teamColor = ChessGame.TeamColor.WHITE;
        } else if (input[1].equalsIgnoreCase("black")) {
            teamColor = ChessGame.TeamColor.BLACK;
        } else {
            return new Combo("Wrong input, try again", false);
        }

        JoinGameData joinCombined = new JoinGameData(teamColor, id);

        try {
            DataStorage.getInstance().getFacade().joinGame(joinCombined);
        } catch (IOException | URISyntaxException e) {
            return new Combo("Wrong input, try again", false);
        }

        forPhase6(id);
        DisplayBoard.callMain(input);

        return new Combo("Join Success", true);
    }

    public Combo observe(String[] input) {

        if (input.length != 1) {
            return new Combo("Wrong input length, try again", false);
        }

        String[] newInput = {input[0], "white"};
        DisplayBoard.callMain(newInput);
        return new Combo("", true);
    }

    private void forPhase6(int id) throws URISyntaxException, IOException {
        Collection<GameData> gameList;
        gameList = DataStorage.getInstance().getFacade().listGames();
        Predicate<GameData> streamPredicate = item -> item.gameID() == id;
        var data = gameList.stream().filter(streamPredicate).toList();
    }
}
