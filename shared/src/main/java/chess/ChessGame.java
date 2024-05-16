package chess;

import java.util.*;

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
            board = new ChessBoard();
            board.resetBoard();
            team = TeamColor.WHITE;
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
        
        setTeamTurn(teamColor);
        //getting all the possible moves of the king and see if isincheck
        boolean allIsInCheck = true;

        //checking if first given position of king is in check
        if (!isInCheck(team)) {
            allIsInCheck = false;
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i, j);
                if (board.getPiece(p) != null ) {
                    if (board.getPiece(p).getTeamColor().equals(team)) {
                        for (ChessMove ChessM : board.getPiece(p).pieceMoves(board, p)) {
                            ChessPiece currPiece = board.getPiece(p);
                            ChessGame copiedBoard = new ChessGame(this);
                            copiedBoard.getBoard().addPiece(p, null);
                            copiedBoard.getBoard().addPiece(ChessM.getEndPosition(), currPiece);
                            //System.out.printf("%s, %s  ", kingM.getEndPosition().getRow(), kingM.getEndPosition().getColumn());
                            if (!copiedBoard.isInCheck(team)) {
                                allIsInCheck = false;
                            } else {
                                //System.out.printf("%s, %s  ", kingM.getEndPosition().getRow(), kingM.getEndPosition().getColumn());
                            }
                        }
                    }
                }
            }
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

        if (isInCheckmate(teamColor)) {
            return true;
        }

        return false;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && team == chessGame.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, team);
    }
}