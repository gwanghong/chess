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

    private final ChessGame.TeamColor pieceColor;
    private final ChessPiece.PieceType type;

    public ChessPiece(ChessGame.TeamColor pieceColor, ChessPiece.PieceType type) {
        this.pieceColor = pieceColor;
        this.type = type;
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
        final ChessPosition start = new ChessPosition(row, col);

        switch (type) {
            case KING:
                Collection<ChessMove> kingM = new HashSet<>();

                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (i > 0 && i < 9 && j > 0 && j < 9) {
                            if (board.getPiece(new ChessPosition(i, j)) == null) {
                                //System.out.printf("%d %d  ", i, j);
                                kingM.add(new ChessMove(start, new ChessPosition(i, j), null));
                            } else {
                                if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                                    //System.out.printf("%d %d  ", i, j);
                                    kingM.add(new ChessMove(start, new ChessPosition(i, j), null));
                                }
                            }
                        }
                    }
                }

                return kingM;
            case QUEEN:
                Collection<ChessMove> queenM = new HashSet<>();

                for (int i = row + 1, j = col + 1; i < 9 && j < 9; i++, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        queenM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            queenM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 8;
                        j = 8;
                    }
                }
                for (int i = row - 1, j = col - 1; i > 0 && j > 0; i--, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        queenM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            queenM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 1;
                        j = 1;
                    }
                }
                for (int i = row + 1, j = col - 1; i < 9 && j > 0; i++, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        queenM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            queenM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 8;
                        j = 1;
                    }
                }
                for (int i = row - 1, j = col + 1; i > 0 && j < 9; i--, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        queenM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            queenM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 1;
                        j = 8;
                    }
                }

                for (int i = row + 1; i < 9; i++) {
                    if (board.getPiece(new ChessPosition(i, col)) == null) {
                        //tem.out.printf("%d %d  ", i, col);
                        queenM.add(new ChessMove(start, new ChessPosition(i, col), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, col)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, col);
                            queenM.add(new ChessMove(start, new ChessPosition(i, col), null));

                        }
                        i = 8;
                    }
                }
                for (int i = row - 1; i > 0; i--) {
                    if (board.getPiece(new ChessPosition(i, col)) == null) {
                        //System.out.printf("%d %d  ", i, col);
                        queenM.add(new ChessMove(start, new ChessPosition(i, col), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, col)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, col);
                            queenM.add(new ChessMove(start, new ChessPosition(i, col), null));

                        }
                        i = 1;
                    }
                }
                for (int j = col + 1; j < 9; j++) {
                    if (board.getPiece(new ChessPosition(row, j)) == null) {
                        //System.out.printf("%d %d  ", row, j);
                        queenM.add(new ChessMove(start, new ChessPosition(row, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(row, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", row, j);
                            queenM.add(new ChessMove(start, new ChessPosition(row, j), null));

                        }
                        j = 8;
                    }
                }
                for (int j = col - 1; j > 0; j--) {
                    if (board.getPiece(new ChessPosition(row, j)) == null) {
                        //System.out.printf("%d %d  ", row, j);
                        queenM.add(new ChessMove(start, new ChessPosition(row, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(row, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", row, j);
                            queenM.add(new ChessMove(start, new ChessPosition(row, j), null));

                        }
                        j = 1;
                    }
                }

                return queenM;
            case BISHOP:
                Collection<ChessMove> bishopM = new HashSet<>();

                for (int i = row + 1, j = col + 1; i < 9 && j < 9; i++, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 8;
                        j = 8;
                    }
                }
                for (int i = row - 1, j = col - 1; i > 0 && j > 0; i--, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 1;
                        j = 1;
                    }
                }
                for (int i = row + 1, j = col - 1; i < 9 && j > 0; i++, j--) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 8;
                        j = 1;
                    }
                }
                for (int i = row - 1, j = col + 1; i > 0 && j < 9; i--, j++) {
                    if (board.getPiece(new ChessPosition(i, j)) == null) {
                        //System.out.printf("%d %d  ", i, j);
                        bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, j);
                            bishopM.add(new ChessMove(start, new ChessPosition(i, j), null));

                        }
                        i = 1;
                        j = 8;
                    }
                }

                return bishopM;
            case KNIGHT:
                Collection<ChessMove> knightM = new HashSet<>();

                for (int i = -1; i <= 1; i += 2) {
                    for (int j = -1; j <= 1; j += 2) {
                        if (row + i > 0 && row + i < 9 && col + 2 * j > 0 && col + 2 * j < 9) {
                            if (board.getPiece(new ChessPosition(row + i, col + 2 * j)) == null) {
                                //System.out.printf("%d %d  ", row + i, col + 2 * j);
                                knightM.add(new ChessMove(start, new ChessPosition(row + i, col + 2 * j), null));
                            } else {
                                if (!board.getPiece(new ChessPosition(row + i, col + 2 * j)).pieceColor.equals(pieceColor)) {
                                    //System.out.printf("%d %d  ", row + i, col + 2 * j);
                                    knightM.add(new ChessMove(start, new ChessPosition(row + i, col + 2 * j), null));
                                }
                            }
                        }

                        if ((row + 2 * i > 0) && (row + 2 * i < 9) && (col + j > 0) && (col + j < 9)) {
                            if (board.getPiece(new ChessPosition(row + 2 * i, col + j)) == null) {
                                //System.out.printf("%d %d  ", row + 2 * i, col + j);
                                knightM.add(new ChessMove(start, new ChessPosition(row + 2 * i, col + j), null));
                            } else {
                                if (!board.getPiece(new ChessPosition(row + 2 * i, col + j)).pieceColor.equals(pieceColor)) {
                                    //System.out.printf("%d %d  ", row + 2 * i, col + j);
                                    knightM.add(new ChessMove(start, new ChessPosition(row + 2 * i, col + j), null));
                                }
                            }
                        }
                    }
                }
                return knightM;
            case ROOK:
                Collection<ChessMove> rookM = new HashSet<>();

                for (int i = row + 1; i < 9; i++) {
                    if (board.getPiece(new ChessPosition(i, col)) == null) {
                        //System.out.printf("%d %d  ", i, col);
                        rookM.add(new ChessMove(start, new ChessPosition(i, col), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, col)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, col);
                            rookM.add(new ChessMove(start, new ChessPosition(i, col), null));

                        }
                        i = 8;
                    }
                }
                for (int i = row - 1; i > 0; i--) {
                    if (board.getPiece(new ChessPosition(i, col)) == null) {
                        //System.out.printf("%d %d  ", i, col);
                        rookM.add(new ChessMove(start, new ChessPosition(i, col), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(i, col)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", i, col);
                            rookM.add(new ChessMove(start, new ChessPosition(i, col), null));

                        }
                        i = 1;
                    }
                }
                for (int j = col + 1; j < 9; j++) {
                    if (board.getPiece(new ChessPosition(row, j)) == null) {
                        //System.out.printf("%d %d  ", row, j);
                        rookM.add(new ChessMove(start, new ChessPosition(row, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(row, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", row, j);
                            rookM.add(new ChessMove(start, new ChessPosition(row, j), null));

                        }
                        j = 8;
                    }
                }
                for (int j = col - 1; j > 0; j--) {
                    if (board.getPiece(new ChessPosition(row, j)) == null) {
                        //System.out.printf("%d %d  ", row, j);
                        rookM.add(new ChessMove(start, new ChessPosition(row, j), null));
                    } else {
                        if (!board.getPiece(new ChessPosition(row, j)).pieceColor.equals(pieceColor)) {
                            //System.out.printf("%d %d  ", row, j);
                            rookM.add(new ChessMove(start, new ChessPosition(row, j), null));

                        }
                        j = 1;
                    }
                }

                return rookM;

            case PAWN:
                Collection<ChessMove> pawnM = new HashSet<>();
                String color = String.valueOf(board.getPiece(myPosition).pieceColor);

                if (color.equals("WHITE")) {

                    for (int j = -1; j <= 1; j += 2) {
                        if (col + j > 0 && col + j < 9 && row + 1 < 9) {
                            if (board.getPiece(new ChessPosition(row + 1, col + j)) != null) {
                                if (!board.getPiece(new ChessPosition(row + 1, col + j)).pieceColor.equals(pieceColor)) {
                                    //System.out.printf("%d %d  ", row + 1, col + j);

                                    if (row + 1 == 8) {
                                        pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col + j), PieceType.ROOK));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col + j), PieceType.QUEEN));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col + j), PieceType.KNIGHT));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col + j), PieceType.BISHOP));
                                    } else {
                                        pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col + j), null));
                                    }
                                }
                            }
                        }
                    }

                    if (row + 1 < 9) {
                        if (board.getPiece(new ChessPosition(row + 1, col)) == null) {
                            //System.out.printf("%d %d  ", row + 1, col);

                            if (row + 1 == 8) {
                                pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col), PieceType.ROOK));
                                pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col), PieceType.QUEEN));
                                pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col), PieceType.KNIGHT));
                                pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col), PieceType.BISHOP));
                            } else {
                                pawnM.add(new ChessMove(start, new ChessPosition(row + 1, col), null));
                            }
                        }
                    }

                    if (row == 2) {
                        if (board.getPiece(new ChessPosition(row + 1, col)) == null && board.getPiece(new ChessPosition(row + 2, col)) == null) {
                            pawnM.add(new ChessMove(start, new ChessPosition(row + 2, col), null));
                        }
                    }
                }

                if (color.equals("BLACK")) {
                    //erase later
                    //System.out.println(board.getPiece(myPosition).pieceColor);

                    for (int j = -1; j <= 1; j += 2) {
                        if (col + j > 0 && col + j < 9 && row - 1 > 0) {
                            if (board.getPiece(new ChessPosition(row - 1, col + j)) != null) {
                                if (!board.getPiece(new ChessPosition(row - 1, col + j)).pieceColor.equals(pieceColor)) {
                                    //System.out.printf("%d %d  ", row - 1, col + j);

                                    if (row - 1 == 1) {
                                        pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col + j), PieceType.ROOK));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col + j), PieceType.QUEEN));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col + j), PieceType.KNIGHT));
                                        pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col + j), PieceType.BISHOP));
                                    } else {
                                        pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col + j), null));
                                    }
                                }
                            }
                        }
                    }

                    if (row - 1 > 0) {
                        if (board.getPiece(new ChessPosition(row - 1, col)) == null) {
                            //System.out.printf("%d %d  ", row - 1, col);

                            if (row - 1 == 1) {
                                pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col), PieceType.ROOK));
                                pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col), PieceType.QUEEN));
                                pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col), PieceType.KNIGHT));
                                pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col), PieceType.BISHOP));
                            } else {
                                pawnM.add(new ChessMove(start, new ChessPosition(row - 1, col), null));
                            }
                        }
                    }

                    if (row == 7) {
                        if (board.getPiece(new ChessPosition(row - 1, col)) == null && board.getPiece(new ChessPosition(row - 2, col)) == null) {
                            pawnM.add(new ChessMove(start, new ChessPosition(row - 2, col), null));
                        }
                    }


                }

                return pawnM;
        }

        return new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessPiece that = (ChessPiece) o;
        return pieceColor == that.pieceColor && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceColor, type);
    }

    /**
     * The various different chess piece options
     */
    public enum PieceType {
        KING, QUEEN, BISHOP, KNIGHT, ROOK, PAWN
    }
}
