package ui;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static ui.EscapeSequences.*;

public class DisplayBoard {

    private static final int BOARD_SIZE_IN_SQUARES = 10;
    private static final int SQUARE_SIZE_IN_CHARS = 1;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = "   ";

    public static void main(String[] args) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);

        out.print(ERASE_SCREEN);

        drawBoard(out);
    }

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_LIGHT_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_TEXT_BOLD);

        out.print(player);

        setGrey(out);
    }

    private static void drawBoard(PrintStream out) {

        int start = 9;
        boolean isFirstAndLast;

        for (int boardRow = 0; boardRow < BOARD_SIZE_IN_SQUARES; ++boardRow) {

            isFirstAndLast = boardRow == 0 || boardRow == 9;

            drawRowOfSquares(out, start, isFirstAndLast);
            start--;

            if (boardRow == 0) {
                //drawVerticalLine(out);
                setBlack(out);
            }
        }
    }

    private static void drawRowOfSquares(PrintStream out, int number, boolean bool) {

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
                            printHeaderText(out, String.valueOf(" " + header + " "));
                            header++;
                        } else {
                            printHeaderText(out, String.valueOf(" " + number + " "));
                        }
                    }
                } else {
                    printPlayer(out, " O ", number, boardCol);
                }

                out.print(EMPTY.repeat(suffixLength));

                setBlack(out);
            }

            out.println();
        }
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

    private static void printPlayer(PrintStream out, String player, int number, int col) {

        if ((number + col) % 2 == 0) {
            out.print(SET_BG_COLOR_BLACK);
        } else {
            out.print(SET_BG_COLOR_WHITE);
        }
        out.print(SET_TEXT_COLOR_BLUE);

        out.print(player);

        setWhite(out);
    }
}
