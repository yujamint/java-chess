package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.MultiMove;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public final class Queen extends NoKingPieces implements MultiMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 9.0;
    private static final int INIT_COLUMN = 3;

    private Queen(final Team team, final Position position) {
        super(position, "Q", team, SCORE);
    }

    private Queen(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Queen of(final Team team, final Position position) {
        return new Queen(position, "Q", team, SCORE);
    }

    public static Queen of(final Team team, final int column) {
        if (column != INIT_COLUMN) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Queen(team, getInitPosition(team, column));
    }

    private static Position getInitPosition(final Team team, final int column) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), column);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), column);
    }

    public static List<Queen> getInitQueen(final Team team) {
        List<Queen> queens = new ArrayList<>();
        ColumnConverter.getQueenInitCols().forEach((column) -> queens.add(Queen.of(team, column)));
        return queens;
    }

    @Override
    public List<Position> getMovablePositions(final Board board) {
        int[] rowDirection = {0, 0, -1, 1, -1, 1, -1, 1};
        int[] colDirection = {-1, 1, 0, 0, -1, 1, 1, -1};
        return getMovablePositionsByDirection(board, rowDirection, colDirection);
    }

    @Override
    protected void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDirection, final int colDirection) {
        int currentRow = getPosition().getRow();
        int currentColumn = getPosition().getColumn();

        while (isMoveAble(movablePositions, board, currentRow + rowDirection, currentColumn + colDirection)) {
            movablePositions.add(new Position(currentRow += rowDirection, currentColumn += colDirection));
        }
    }

    @Override
    public boolean isMoveAble(final List<Position> movablePositions, final Board board, final int nextRow, final int nextColumn) {
        return isMoveAbleDirection(movablePositions, board, nextRow, nextColumn, getTeam());
    }
}