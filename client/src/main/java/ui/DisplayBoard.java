package ui;

import chess.ChessBoard;
import chess.ChessGame;
import chess.ChessPiece;
import chess.ChessPosition;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static ui.EscapeSequences.*;

public class DisplayBoard {

    private static final int BOARD_SIZE_IN_SQUARES = 10;
    private static final int SQUARE_SIZE_IN_CHARS = 1;
    private static final String EMPTY = "   ";

    final static Map<ChessPiece.PieceType, Character> CHAR_TO_TYPE_MAP = Map.of(
            ChessPiece.PieceType.PAWN, 'P',
            ChessPiece.PieceType.KNIGHT, 'N',
            ChessPiece.PieceType.ROOK, 'R',
            ChessPiece.PieceType.QUEEN, 'Q',
            ChessPiece.PieceType.KING, 'K',
            ChessPiece.PieceType.BISHOP, 'B');

    public static void main(String[] args) {

        boolean whiteTurn = true;
        //args[1].equalsIgnoreCase("white");

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawBoard(out, whiteTurn);

        resetColors(out);

    }

    public static void callMain(String[] input) {

        //int gameID = parseInt(input[0]);

        boolean whiteTurn = input[1].equalsIgnoreCase("white");

        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawBoard(out, whiteTurn);

        resetColors(out);
    }

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_TEXT_BOLD);

        out.print(player);

        setGrey(out);
    }

    private static void drawBoard(PrintStream out, boolean whiteTurn) {

        int start = 9;
        if (whiteTurn) {
            start = 0;
        }
        boolean isFirstAndLast;

        for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {

            isFirstAndLast = boardRow == 0 || boardRow == 9;

            if (whiteTurn) {
                drawRowOfSquaresWhite(out, start, isFirstAndLast, boardRow);
                start++;
            } else {
                drawRowOfSquaresBlack(out, start, isFirstAndLast, boardRow);
                start--;
            }

            if (boardRow == 0) {
                //drawVerticalLine(out);
                setBlack(out);
            }
        }

    }

    private static void drawRowOfSquaresBlack(PrintStream out, int number, boolean bool, int row) {

        char header = 'a';

        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_CHARS; ++squareRow) {
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {

                setWhite(out);

                int prefixLength = SQUARE_SIZE_IN_CHARS / 2;
                int suffixLength = 0;

                out.print(EMPTY.repeat(prefixLength));

                if ((boardCol == 0 || boardCol == 9) || bool) {
                    if (boardCol == 0 && bool || boardCol == 9 && bool) {
                        printHeaderText(out, EMPTY);
                    } else {
                        if (bool) {
                            printHeaderText(out, " " + header + " ");
                            header++;
                        } else {
                            printHeaderText(out, " " + number + " ");
                        }
                    }
                } else {

                    ChessPiece piece = returnPiece(row, boardCol);
                    drawRowOfSquareHelper(piece, out, number, boardCol);
                }

                out.print(EMPTY.repeat(suffixLength));

                setBlack(out);
            }

            out.println();
        }
    }

    private static void drawRowOfSquaresWhite(PrintStream out, int number, boolean bool, int row) {

        char header = 'h';

        for (int squareRow = 0; squareRow < SQUARE_SIZE_IN_CHARS; ++squareRow) {
            for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {

                setWhite(out);

                int prefixLength = SQUARE_SIZE_IN_CHARS / 2;
                int suffixLength = 0;

                out.print(EMPTY.repeat(prefixLength));

                if ((boardCol == 0 || boardCol == 9) || bool) {
                    if (boardCol == 0 && bool || boardCol == 9 && bool) {
                        printHeaderText(out, EMPTY);
                    } else {
                        if (bool) {
                            printHeaderText(out, " " + header + " ");
                            header--;
                        } else {
                            printHeaderText(out, " " + number + " ");
                        }
                    }
                } else {

                    int reverseRow = 9 - row;
                    int reverseCol = 9 - boardCol;
                    ChessPiece piece = returnPiece(reverseRow, reverseCol);

                    drawRowOfSquareHelper(piece, out, number, boardCol);

                }

                out.print(EMPTY.repeat(suffixLength));

                setBlack(out);
            }

            out.println();
        }
    }

    private static void drawRowOfSquareHelper(ChessPiece piece, PrintStream out, int number, int boardCol) {
        String player;
        boolean isWhite = false;

        if (piece != null) {

            var type = CHAR_TO_TYPE_MAP.get(piece.getPieceType());

            isWhite = piece.getTeamColor().equals(ChessGame.TeamColor.WHITE);
            player = " " + type + " ";
        } else {
            player = "   ";
        }

        printPlayer(out, player, number, boardCol, isWhite);
    }

    private static void setWhite(PrintStream out) {
        out.print(SET_BG_COLOR_WHITE);
        out.print(SET_TEXT_COLOR_WHITE);
    }

    private static void setGrey(PrintStream out) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_DARK_GREY);
    }

    private static void setBlack(PrintStream out) {
        out.print(SET_BG_COLOR_BLACK);
        out.print(SET_TEXT_COLOR_BLACK);
    }

    private static void resetColors(PrintStream out) {
        out.print(SET_BG_COLOR_CONSOLE_BACKGROUND);
        //out.print(RESET_TEXT_COLOR);
    }

    private static void printPlayer(PrintStream out, String player, int number, int col, boolean isWhite) {

        if ((number + col) % 2 == 0) {
            out.print(SET_BG_COLOR_BLACK);
        } else {
            out.print(SET_BG_COLOR_WHITE);
        }

        if (isWhite) {
            out.print(SET_TEXT_COLOR_BLUE);
        } else {
            out.print(SET_TEXT_COLOR_RED);
        }

        out.print(player);

        setWhite(out);
    }

    public static ChessPiece returnPiece(int i, int j) {

        ChessBoard board = new ChessBoard();
        board.resetBoard();

        ChessPosition position = new ChessPosition(i, j);


        return board.getPiece(position);
    }
}
