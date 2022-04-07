package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Position;

public interface GameState {

    boolean isEnd();

    boolean isBlackTurn();

    Winner findWinner();

    GameState move(Position start, Position target);

    GameState terminate();

    double calculateBlackScore();

    double calculateWhiteScore();

    Board getBoard();

    boolean isBlackWin();

    boolean isTerminated();

    StateType getType();
}