package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {

    private final ChessPiece[][] squares = new ChessPiece[8][8];

    public ChessBoard() {
        
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {

        squares[position.getRow() - 1][position.getColumn() - 1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        return squares[position.getRow() - 1][position.getColumn() - 1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j] = null;
            }
        }

        //pawn
        for (int j = 1; j < 9; j ++) {
            ChessPosition whitePawnP = new ChessPosition(2, j);
            ChessPiece whiteP = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.PAWN);

            addPiece(whitePawnP, whiteP);

            ChessPosition blackPawnP = new ChessPosition(7, j);
            ChessPiece blackP = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.PAWN);

            addPiece(blackPawnP, blackP);
        }

        //rook
        for (int j = 1; j < 9; j += 7) {
            ChessPosition whiteRookP = new ChessPosition(1, j);
            ChessPiece whiteR = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.ROOK);

            addPiece(whiteRookP, whiteR);

            ChessPosition blackRookP = new ChessPosition(8, j);
            ChessPiece blackR = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.ROOK);

            addPiece(blackRookP, blackR);
        }

        //knight
        for (int j = 2; j < 8; j += 5) {
            ChessPosition whiteKnightP = new ChessPosition(1, j);
            ChessPiece WhiteN = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KNIGHT);

            addPiece(whiteKnightP, WhiteN);

            ChessPosition blackKnightP = new ChessPosition(8, j);
            ChessPiece blackN = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KNIGHT);

            addPiece(blackKnightP, blackN);
        }

        //bishop
        for (int j = 3; j < 7; j += 3) {
            ChessPosition whiteBishopP = new ChessPosition(1, j);
            ChessPiece whiteB = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.BISHOP);

            addPiece(whiteBishopP, whiteB);

            ChessPosition blackBishopP = new ChessPosition(8, j);
            ChessPiece blackB = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.BISHOP);

            addPiece(blackBishopP, blackB);
        }

        //queen
        ChessPosition whiteQueenP = new ChessPosition(1, 4);
        ChessPiece whiteQ = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.QUEEN);

        addPiece(whiteQueenP, whiteQ);

        ChessPosition blackQueenP = new ChessPosition(8, 4);
        ChessPiece blackQ = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.QUEEN);

        addPiece(blackQueenP, blackQ);

        //king
        ChessPosition whiteKingP = new ChessPosition(1, 5);
        ChessPiece whiteK = new ChessPiece(ChessGame.TeamColor.WHITE, ChessPiece.PieceType.KING);

        addPiece(whiteKingP, whiteK);

        ChessPosition blackKingP = new ChessPosition(8, 5);
        ChessPiece blackK = new ChessPiece(ChessGame.TeamColor.BLACK, ChessPiece.PieceType.KING);

        addPiece(blackKingP, blackK);


        //print
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.println(squares[i][j]);
            }
            System.out.println("\n");
        }
    }


    @Override
    public String toString() {
        return "ChessBoard{" +
                "squares=" + Arrays.toString(squares) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(squares, that.squares);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(squares);
    }
}
