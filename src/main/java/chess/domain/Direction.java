package chess.domain;

import java.util.Arrays;
import java.util.List;

public enum Direction {
	NORTH(0, 1),
	NORTHEAST(1, 1),
	EAST(1, 0),
	SOUTHEAST(1, -1),
	SOUTH(0, -1),
	SOUTHWEST(-1, -1),
	WEST(-1, 0),
	NORTHWEST(-1, 1),

	NNE(1, 2),
	NNW(-1, 2),
	SSE(1, -2),
	SSW(-1, -2),
	EEN(2, 1),
	EES(2, -1),
	WWN(-2, 1),
	WWS(-2, -1);

	private static final List<Direction> BLACK_PAWN_DIRECTION = Arrays.asList(SOUTH, SOUTHEAST, SOUTHWEST);
	private static final List<Direction> WHITE_PAWN_DIRECTION = Arrays.asList(NORTH, NORTHEAST, NORTHWEST);
	private static final List<Direction> KNIGHT_DIRECTION = Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
	private static final List<Direction> EVERY_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST,
		SOUTHWEST, NORTHWEST);
	private static final List<Direction> DIAGONAL_DIRECTION = Arrays.asList(NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
	private static final List<Direction> LINEAR_DIRECTION = Arrays.asList(NORTH, EAST, SOUTH, WEST);

	private int xDegree;
	private int yDegree;

	private Direction(int xDegree, int yDegree) {
		this.xDegree = xDegree;
		this.yDegree = yDegree;
	}

	public int getXDegree() {
		return xDegree;
	}

	public int getYDegree() {
		return yDegree;
	}

	public static List linearDirection() {
		return LINEAR_DIRECTION;
	}

	public static List diagonalDirection() {
		return DIAGONAL_DIRECTION;
	}

	public static List everyDirection() {
		return EVERY_DIRECTION;
	}

	public static List knightDirection() {
		return KNIGHT_DIRECTION;
	}

	public static List whitePawnDirection() {
		return WHITE_PAWN_DIRECTION;
	}

	public static List blackPawnDirection() {
		return BLACK_PAWN_DIRECTION;
	}
}