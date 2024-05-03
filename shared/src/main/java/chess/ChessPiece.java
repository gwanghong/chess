package chess;

import java.util.ArrayList;


/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    PieceType type;
    ChessGame.TeamColor pieceColor;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.pieceColor = pieceColor;
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING,
        QUEEN,
        BISHOP,
        KNIGHT,
        ROOK,
        PAWN
    }

    /**
     * @return Which team this chess piece belongs to
     */
    public ChessGame.TeamColor getTeamColor() {
        throw new RuntimeException("Not implemented");
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public ArrayList<int[][]> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        switch (type) {
            case KING:
                break;

            case QUEEN:
                break;

            case BISHOP:
                ArrayList<int[][]> p = new ArrayList<>();

                p.add(new int[][]{{6, 5}, {7, 6}, {8, 7},
                        {4, 5}, {3, 6}, {2, 7}, {1, 8},
                        {4, 3}, {3, 2}, {2, 1},
                        {6, 3}, {7, 2}, {8, 1}});

                return p;

            case KNIGHT:
                break;

            case ROOK:
                break;

            case PAWN:
                break;
            }

        return new ArrayList<>();
    }
}
