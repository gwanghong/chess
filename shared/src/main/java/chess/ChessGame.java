package chess;

import java.util.Collection;
import java.util.HashSet;

/**
 * For a class that can manage a chess game, making moves on a board
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessGame {

    private ChessBoard board;
    private TeamColor team;

    public ChessGame() {

    }

    /**
     * @return Which team's turn it is
     */
    public TeamColor getTeamTurn() {
        return team;
    }

    /**
     * Set's which teams turn it is
     *
     * @param team the team whose turn it is
     */
    public void setTeamTurn(TeamColor team) {
        this.team = team;
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE,
        BLACK
    }

    /**
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        Collection<ChessMove> valid = new HashSet<>();
        ChessPiece piece = board.getPiece(startPosition);
        setTeamTurn(piece.getTeamColor());
        /*
         *get all the valid moves from all pieces, and check for
         *ischeck and ischeckmate
         *for isincheck make a copy board and check for chess, after that throw that board away
         *isStalemate: not in check but there is no legal moves to make
         *when start working on extra credit - make sure to make a separate branch and work from there
         *if fail, you can come back to the main branch
         *chesspiece 에 있는 queen 부분, 중복되는 부분 고쳐보기 - bishop, rook 중복코드 있는거 고치는 방법이 있지 않을까
         *case QUEEN: 에서 이제 switch(BISHOP), switch(ROOK) 로 가능할까?

         */
        if (piece != null) {
            for (ChessMove move : piece.pieceMoves(board, startPosition)) {
                if (true) {
                    System.out.printf("%d %d  ", move.getEndPosition().getRow(), move.getEndPosition().getColumn());
                    valid.add(move);
                }
            }
        }

        return valid;
    }

    /**
     * Makes a move in a chess game
     *
     * @param move chess move to preform
     * @throws InvalidMoveException if move is invalid
     */
    public void makeMove(ChessMove move) throws InvalidMoveException {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Determines if the given team is in check
     *
     * @param teamColor which team to check for check
     * @return True if the specified team is in check
     */
    public boolean isInCheck(TeamColor teamColor) {

        ChessPosition kingP = null;
        setTeamTurn(teamColor);

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i, j);
                if (board.getPiece(p) != null && board.getPiece(p).getPieceType().equals(ChessPiece.PieceType.KING)) {
                    if (board.getPiece(p).getTeamColor().equals(team)) {
                        kingP = p;
                        //System.out.printf("%d %d ", i, j);
                    }
                }
            }
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i, j);
                ChessPiece pt = board.getPiece(p);

                if (pt != null && !pt.getTeamColor().equals(team)) {
                    for (ChessMove move : pt.pieceMoves(board, p)) {
                        if (move.getEndPosition().equals(kingP)) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }

    /**
     * Determines if the given team is in checkmate
     *
     * @param teamColor which team to check for checkmate
     * @return True if the specified team is in checkmate
     */
    public boolean isInCheckmate(TeamColor teamColor) {

        //finding position for the current teamturn King.
        ChessPosition kingP = null;
        setTeamTurn(teamColor);

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i, j);
                if (board.getPiece(p) != null && board.getPiece(p).getPieceType().equals(ChessPiece.PieceType.KING)) {
                    if (board.getPiece(p).getTeamColor().equals(team)) {
                        kingP = p;
                    }
                }
            }
        }

        //getting all the possible moves of the king and see if isincheck
        boolean allIsInCheck = true;
        ChessPiece currKing = new ChessPiece(team, ChessPiece.PieceType.KING);
        for (ChessMove kingM : board.getPiece(kingP).pieceMoves(board, kingP)) {

            ChessGame copiedBoard = new ChessGame(this);
            copiedBoard.getBoard().addPiece(kingP, null);
            copiedBoard.getBoard().addPiece(kingM.getEndPosition(), currKing);
            //kingP = kingM.getEndPosition();
            System.out.printf("%s, %s  ", kingM.getEndPosition().getRow(), kingM.getEndPosition().getColumn());
            if (!copiedBoard.isInCheck(team)) {
                allIsInCheck = false;
            } else {
                System.out.printf("%s, %s  ", kingM.getEndPosition().getRow(), kingM.getEndPosition().getColumn());
            }
/*
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    ChessPosition p = new ChessPosition(i,j);
                    if (board.getPiece(p) != null) {
                        System.out.printf("%s: %s \n", board.getPiece(p).getTeamColor(), board.getPiece(p).getPieceType());
                    } else {
                        System.out.println("null");
                    }
                }
                System.out.println("\n");
            }
            System.out.println("Entering copyboard\n");
            for (int i = 1; i < 9; i++) {
                for (int j = 1; j < 9; j++) {
                    ChessPosition p = new ChessPosition(i,j);
                    if (copiedBoard.getBoard().getPiece(p) != null) {
                        System.out.printf("%s: %s \n", copiedBoard.getBoard().getPiece(p).getTeamColor(), copiedBoard.getBoard().getPiece(p).getPieceType());
                    } else {
                        System.out.println("null");
                    }
                }
                System.out.println("\n");
            }*/
        }

        return allIsInCheck;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {
        throw new RuntimeException("Not implemented");
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }

    //Created constructor for deep copy
    public ChessGame(ChessGame other) {
/*
        this.board = other.getBoard();
        this.team = other.getTeamTurn();
*/
        board = new ChessBoard();
        setTeamTurn(other.getTeamTurn());

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i,j);
                board.addPiece(p, other.getBoard().getPiece(p));
            }
        }
    }
}