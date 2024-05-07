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
                Collection<ChessMove> kingP = new HashSet<>();

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if ((i != row || j != col) && (0 < i && i < 9 && 0 < j && j < 9)) {
                            if (board.getPiece(new ChessPosition(i, j)) == null) {
                                System.out.printf("%d %d  ", i, j);
                                kingP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                            }
                            else {
                                if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(this.pieceColor)) {
                                    System.out.printf("%d %d  ", i, j);
                                    kingP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                                }
                            }
                        }
                    }
                }
                return kingP;

            case QUEEN:
                break;

            case BISHOP:
                Collection<ChessMove> bishP = new HashSet<>();

                for (int i = row + 1, j = col + 1; i < 9 && j < 9; i++, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        System.out.printf("%d %d  ", i, j);
                        bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                    } else {
                        if (board.getPiece(new ChessPosition(i, j)).pieceColor.equals(this.pieceColor)) {
                            i = 9;
                            j = 9;
                        }
                        else {
                            System.out.printf("%d %d  ", i, j);
                            bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                            i = 9;
                            j = 9;
                        }
                    }
                }
                System.out.println("\n");

                for (int i = row - 1, j = col + 1; i > 0 && j < 9; i--, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        System.out.printf("%d %d  ", i, j);
                        bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                    } else {
                        if (board.getPiece(new ChessPosition(i, j)).pieceColor.equals(this.pieceColor)) {
                            i = 0;
                            j = 9;
                        }
                        else {
                            System.out.printf("%d %d  ", i, j);
                            bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                            i = 0;
                            j = 9;
                        }
                    }
                }
                System.out.println("\n");

                for (int i = row - 1, j = col - 1; i > 0 && j > 0; i--, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        System.out.printf("%d %d  ", i, j);
                        bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                    } else {
                        if (board.getPiece(new ChessPosition(i, j)).pieceColor.equals(this.pieceColor)) {
                            i = 0;
                            j = 0;
                        }
                        else {
                            System.out.printf("%d %d  ", i, j);
                            bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                            i = 0;
                            j = 0;
                        }
                    }
                }
                System.out.println("\n");

                for (int i = row + 1, j = col - 1; i < 9 && j > 0; i++, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        System.out.printf("%d %d  ", i, j);
                        bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                    } else {
                        if (board.getPiece(new ChessPosition(i, j)).pieceColor.equals(this.pieceColor)) {
                            i = 9;
                            j = 0;
                        }
                        else {
                            System.out.printf("%d %d  ", i, j);
                            bishP.add(new ChessMove(myPosition, new ChessPosition(i, j), null));
                            i = 9;
                            j = 0;
                        }
                    }
                }
                System.out.println("\n");

                return bishP;

            case KNIGHT:
                Collection<ChessMove> knightP = new HashSet<>();

                for (int i = -1; i <= 1; i+= 2) {
                    for (int j = -1; j <= 1; j+= 2) {
                        if ((0 < row + 2 * i && row + 2 * i < 9) && (0 < col + 2 * j && col + 2 * j < 9)) {
                            if (board.getPiece(new ChessPosition(row + 2 * i, col + j)) == null) {
                                System.out.printf("%d %d  ", row + 2 * i, col + j);
                                knightP.add(new ChessMove(myPosition, new ChessPosition(row + 2 * i, col + j), null));
                            } else if (board.getPiece(new ChessPosition(row + 2 * i, col + j)) != null
                                    && !board.getPiece(new ChessPosition(row + 2 * i, col + j)).pieceColor.equals(this.pieceColor)) {
                                System.out.printf("%d %d  ", row + 2 * i, col + j);
                                knightP.add(new ChessMove(myPosition, new ChessPosition(row + 2 * i, col + j), null));
                            }
                            if (board.getPiece(new ChessPosition(row + i, col + 2 * j)) == null) {
                                System.out.printf("%d %d  ", row + i, col + 2 * j);
                                knightP.add(new ChessMove(myPosition, new ChessPosition(row + i, col + 2 * j), null));
                            } else if (board.getPiece(new ChessPosition(row + i, col + 2 * j)) != null
                                    && !board.getPiece(new ChessPosition(row + i, col + 2 * j)).pieceColor.equals(this.pieceColor)) {
                                System.out.printf("%d %d  ", row + i, col + 2 * j);
                                knightP.add(new ChessMove(myPosition, new ChessPosition(row + i, col + 2 * j), null));
                            }
                        }
                    }
                }
                return knightP;

            case ROOK:
                break;

            case PAWN:
                break;
            }

            return new HashSet<ChessMove>();
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
}
