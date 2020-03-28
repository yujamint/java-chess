package chess.domain;

import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.exception.InvalidTurnException;

public class Board {
	private final Map<Position, Piece> board;

	public Board(Map<Position, Piece> board) {
		this.board = board;
	}

	public Map<Position, String> getDto() {
		return board.entrySet().stream()
			.collect(Collectors.toMap(
				Map.Entry::getKey,
				entry -> entry.getValue().toString()
			));
	}

	public void move(Position from, Position to, Turn turn) {
		Piece source = hasPieceIn(from);
		source.move(from, to, turn);
		if (!from.isTurn(turn)) {
			throw new InvalidTurnException("해당 플레이어의 턴이 아닙니다.");
		}
		source.move();
		board.put(to, source);
	}
}
