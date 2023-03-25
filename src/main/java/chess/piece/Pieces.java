package chess.piece;

import chess.board.Position;
import chess.piece.type.Piece;
import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(PiecesGenrator piecesGenrator) {
        this.pieces = new ArrayList<>(piecesGenrator.generate());
    }

    public Piece findPieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.isSamePosition(position))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 해당 위치에 존재하는 기물이 없습니다."));
    }

    public void synchronizeMovedPiece(final Piece pieceBeforeMove, final Piece movedPiece) {
        if (pieces.contains(pieceBeforeMove)) {
            final int pieceBeforeMoveIndex = pieces.indexOf(pieceBeforeMove);
            pieces.set(pieceBeforeMoveIndex, movedPiece);
        }
    }

    public boolean isPieceExistOnPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.isSamePosition(position));
    }

    public List<Piece> getPieces() {
        return List.copyOf(pieces);
    }

    public void remove(final Piece pieceToRemove) {
        if (!pieces.contains(pieceToRemove)) {
            throw new IllegalArgumentException("[ERROR] 지우려고 하는 기물이 존재하지 않습니다.");
        }
        pieces.remove(pieceToRemove);
    }
}
