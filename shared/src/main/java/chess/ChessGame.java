package chess;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

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

    //Created constructor for deep copy
    public ChessGame(ChessGame other) {

        board = new ChessBoard();
        setTeamTurn(other.getTeamTurn());

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                ChessPosition p = new ChessPosition(i, j);
                board.addPiece(p, other.getBoard().getPiece(p));
            }
        }
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
     * Gets a valid moves for a piece at the given location
     *
     * @param startPosition the piece to get valid moves for
     * @return Set of valid moves for requested piece, or null if no piece at
     * startPosition
     */
    public Collection<ChessMove> validMoves(ChessPosition startPosition) {

        Collection<ChessMove> valid = new HashSet<>();

        if (board.getPiece(startPosition) != null) {

            setTeamTurn(board.getPiece(startPosition).getTeamColor());

            if (board.getPiece(startPosition).getTeamColor().equals(team)) {
                for (ChessMove chessMovement : board.getPiece(startPosition).pieceMoves(board, startPosition)) {
                    ChessPiece currPiece = board.getPiece(startPosition);
                    ChessGame copiedBoard2 = new ChessGame(this);
                    copiedBoard2.getBoard().addPiece(startPosition, null);
                    copiedBoard2.getBoard().addPiece(chessMovement.getEndPosition(), currPiece);

                    if (!copiedBoard2.isInCheck(team) && !copiedBoard2.isInCheckmate(team)) {
                        valid.add(chessMovement);
                    }
                }
            }
        } else {
            return null;
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

        boolean isMoveValid = false;

        ChessPosition startP = move.getStartPosition();
        ChessPosition endP = move.getEndPosition();
        ChessPiece.PieceType moveP = move.getPromotionPiece();

        if (board.getPiece(startP) == null) {
            throw new InvalidMoveException();
        }

        for (ChessMove chessMovementM : board.getPiece(startP).pieceMoves(board, startP)) {
            if (move.equals(chessMovementM)) {
                isMoveValid = true;
                break;
            }
        }

        if (isMoveValid && !isInCheck(team) && !isInCheckmate(team) && board.getPiece(startP).getTeamColor().equals(team)) {
            ChessPiece currP = board.getPiece(startP);

            if (currP.getPieceType() == ChessPiece.PieceType.PAWN && (endP.getRow() == 1) || (endP.getRow() == 8)) {
                board.addPiece(startP, null);
                board.addPiece(endP, new ChessPiece(team, moveP));

            } else {
                board.addPiece(startP, null);
                board.addPiece(endP, currP);
            }

            if (team.equals(TeamColor.WHITE)) {
                setTeamTurn(TeamColor.BLACK);
            } else {
                setTeamTurn(TeamColor.WHITE);
            }

        } else {
            throw new InvalidMoveException("second");
        }
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
                    }
                }
            }
        }

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {

                if (isKingInDanger(i, j, kingP)) {
                    return true;
                }
            }
        }

        return false;

    }

    private boolean isKingInDanger(int i, int j, ChessPosition kingP) {
        ChessPosition p = new ChessPosition(i, j);
        ChessPiece pt = board.getPiece(p);

        if (pt != null && !pt.getTeamColor().equals(team)) {
            for (ChessMove move : pt.pieceMoves(board, p)) {
                if (move.getEndPosition().equals(kingP)) {
                    return true;
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
        //getting all the possible moves of the king and see if isInCheck
        boolean allIsInCheck = isInCheck(team);

        //checking if first given position of king is in check

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (!isInCheckMateHelper(i, j)) {
                    allIsInCheck = false;
                }
            }
        }

        return allIsInCheck;
    }

    private boolean isInCheckMateHelper(int i, int j) {
        ChessPosition p = new ChessPosition(i, j);
        if (board.getPiece(p) != null) {
            if (board.getPiece(p).getTeamColor().equals(team)) {
                for (ChessMove chessMovement : board.getPiece(p).pieceMoves(board, p)) {
                    ChessPiece currPiece = board.getPiece(p);
                    ChessGame copiedBoard = new ChessGame(this);
                    copiedBoard.getBoard().addPiece(p, null);
                    copiedBoard.getBoard().addPiece(chessMovement.getEndPosition(), currPiece);

                    if (!copiedBoard.isInCheck(team)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    /**
     * Determines if the given team is in stalemate, which here is defined as having
     * no valid moves
     *
     * @param teamColor which team to check for stalemate
     * @return True if the specified team is in stalemate, otherwise false
     */
    public boolean isInStalemate(TeamColor teamColor) {

        boolean isStale = false;

        for (int i = 1; i < 9; i++) {
            for (int j = 1; j < 9; j++) {
                if (isInStalemateHelper(i, j, teamColor)) {
                    isStale = true;
                }
            }
        }

        if (isInCheckmate(teamColor)) {
            return false;
        }

        return isStale;
    }

    private boolean isInStalemateHelper(int i, int j, TeamColor teamColor) {
        ChessPosition p = new ChessPosition(i, j);
        if (board.getPiece(p) != null) {
            if (board.getPiece(p).getTeamColor().equals(teamColor)) {
                for (ChessMove chessMovement : board.getPiece(p).pieceMoves(board, p)) {
                    ChessPiece currPiece = board.getPiece(p);
                    ChessGame copiedBoard2 = new ChessGame(this);
                    copiedBoard2.getBoard().addPiece(p, null);
                    copiedBoard2.getBoard().addPiece(chessMovement.getEndPosition(), currPiece);

                    if (copiedBoard2.isInCheck(teamColor)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Gets the current chessboard
     *
     * @return the chessboard
     */
    public ChessBoard getBoard() {
        return board;
    }

    /**
     * Sets this game's chessboard with a given board
     *
     * @param board the new board to use
     */
    public void setBoard(ChessBoard board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessGame chessGame = (ChessGame) o;
        return Objects.equals(board, chessGame.board) && team == chessGame.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, team);
    }

    /**
     * Enum identifying the 2 possible teams in a chess game
     */
    public enum TeamColor {
        WHITE, BLACK
    }
}