package chess.domain.piece.strategy;

import chess.domain.piece.info.Position;

public class KingMoveStrategy extends AllMoveStrategy {
    private static final String KING_ERROR = "[ERROR] 킹 이동 규칙에 어긋납니다.";

    @Override
    public boolean canMove(Position source, Position target) {
        validateKingMove(source, target);
        return true;
    }

    private void validateKingMove(Position source, Position target) {
        if (isNotCrossOrDiagonal(source, target) || isDistanceOverOne(source, target)) {
            throw new IllegalArgumentException(KING_ERROR);
        }
    }

    private boolean isDistanceOverOne(Position source, Position target) {
        return (Math.abs(source.subtractX(target)) > 1 || Math.abs(source.subtractY(target)) > 1);
    }
}