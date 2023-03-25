package chess.controller;

import chess.board.Board;
import chess.board.File;
import chess.board.Position;
import chess.board.Rank;
import chess.board.dto.BoardDto;
import chess.game.ChessGame;
import chess.view.GameCommand;
import chess.piece.AllPiecesGenerator;
import chess.piece.Pieces;
import chess.piece.Side;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private final InputView inputView;

    public ChessController(final InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        final ChessGame chessGame = setUp();

        while(!(chessGame.status() == GameStatus.END)) {
            repeatByRunnable(() -> handleCommand(chessGame));
        }
        repeatByRunnable(() -> handleCommandAfterGameEnd(chessGame));
    }

    private ChessGame setUp() {
        Board board = new Board(new Pieces(new AllPiecesGenerator()));
        OutputView.printGameStartMessage();
        OutputView.printGameCommandInputMessage();
        return new ChessGame(board, Side.WHITE, GameStatus.INIT);
    }

    private Runnable repeatByRunnable(Runnable runnable) {
        try {
            runnable.run();
            return runnable;
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatByRunnable(runnable);
        }
    }

    private void handleCommand(final ChessGame chessGame) {
        final List<String> splitGameCommand = inputGameCommand();
        final GameCommand gameCommand = GameCommand.of(splitGameCommand.get(0));
        if (GameCommand.START == gameCommand) {
            handleStartCommand(chessGame);
        }
        if (GameCommand.MOVE == gameCommand) {
            handleMoveCommand(chessGame, splitGameCommand);
        }
        if (GameCommand.END == gameCommand) {
            chessGame.exit();
        }
    }

    private List<String> inputGameCommand() {
        return repeatUntilReturnValue(inputView::inputGameCommand);
    }

    private <T> T repeatUntilReturnValue(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e);
            return repeatUntilReturnValue(supplier);
        }
    }

    private void handleStartCommand(final ChessGame chessGame) {
        chessGame.start();
        OutputView.printBoard(new BoardDto(chessGame.getBoard()));
    }

    private void handleMoveCommand(final ChessGame chessGame, final List<String> splitGameCommand) {
        final Position sourcePosition = generatePosition(splitGameCommand.get(SOURCE_POSITION_INDEX));
        final Position targetPosition = generatePosition(splitGameCommand.get(TARGET_POSITION_INDEX));

        chessGame.movePiece(sourcePosition, targetPosition);
        OutputView.printBoard(new BoardDto(chessGame.getBoard()));
    }

    private Position generatePosition(final String positionInput) {
        final List<String> splitSourcePosition = Arrays.asList(positionInput.split(""));
        final File sourceFile = File.of(splitSourcePosition.get(0));
        final Rank sourceRank = extractRank(splitSourcePosition);
        return new Position(sourceFile, sourceRank);
    }

    private Rank extractRank(final List<String> splitSourcePosition) {
        try {
            return Rank.of(Integer.parseInt(splitSourcePosition.get(SOURCE_POSITION_INDEX)));
        } catch (NumberFormatException e) {
            throw new NumberFormatException("[ERROR] 랭크 값은 숫자여야 합니다.");
        }
    }

    private void handleCommandAfterGameEnd(final ChessGame chessGame) {
        OutputView.printCommandAfterGameEndInputMessage();
        final String inputCommand = repeatUntilReturnValue(inputView::inputCommandAfterGameEnd);
        if (GameCommand.STATUS == GameCommand.of(inputCommand)) {
            handleStatusCommand(chessGame);
        }
    }

    private void handleStatusCommand(ChessGame chessGame) {
        final Side winner = chessGame.getWinner();
        final double whiteScore = chessGame.calculateScoreBySide(Side.WHITE);
        final double blackScore = chessGame.calculateScoreBySide(Side.BLACK);

        OutputView.printGameResult(winner, whiteScore, blackScore);
    }
}
