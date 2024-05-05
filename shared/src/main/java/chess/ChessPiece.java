package chess;

import java.util.Collection;

import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single chess piece
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessPiece {

    private final PieceType type;
    private final ChessGame.TeamColor pieceColor;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.type = type;
        this.pieceColor = pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return type == that.type && pieceColor == that.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, pieceColor);
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

        return pieceColor;
    }

    /**
     * @return which type of chess piece this piece is
     */
    public PieceType getPieceType() {

        return type;
    }

    /**
     * Calculates all the positions a chess piece can move to
     * Does not take into account moves that are illegal due to leaving the king in
     * danger
     *
     * @return Collection of valid moves
     */
    public Collection<ChessMove> pieceMoves(ChessBoard board, ChessPosition myPosition) {

        final int row = myPosition.getRow();
        final int col = myPosition.getColumn();

        switch (type) {
            case KING:
                break;

            case QUEEN:
                break;

            case BISHOP:
                Collection<ChessMove> pos = new HashSet<>();

                for (int i = row + 1, j = col + 1; i < 9 && j < 9; i++, j++) {
                    System.out.printf("%d %d  ", i, j);
                    pos.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                }
                System.out.println("\n");
                for (int i = row - 1, j = col + 1; i > 0 && j < 9; i--, j++) {
                    System.out.printf("%d %d  ", i, j);
                    pos.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                }
                System.out.println("\n");
                for (int i = row - 1, j = col - 1; i > 0 && j > 0; i--, j--) {
                    System.out.printf("%d %d  ", i, j);
                    pos.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                }
                System.out.println("\n");
                for (int i = row + 1, j = col - 1; i < 9 && j > 0; i++, j--) {
                    System.out.printf("%d %d  ", i, j);
                    pos.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                }
                System.out.println("\n");

                return pos;

            case KNIGHT:
                break;

            case ROOK:
                break;

            case PAWN:
                break;
            }

            return new HashSet<ChessMove>();
    }
}
