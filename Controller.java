package game;


import javafx.geometry.Point2D;
import java.util.Set;
public final class Controller {
	private Board board;
	private Player player;
	private int depth = 3;
	private int pass = 0;

	private Controller() {
		this.board = new Board();
		init();
	}

	public Set<Point2D> markPossibleMoves() {
		Set<Point2D> moves = board.getPossibleMoves(player);
		board.markPossibleMoves(moves);
		pass = moves.isEmpty() ? pass++ : 0;
		return moves;
	}

	public void unmarkPossibleMoves() {
		board.unmarkPossibleMoves();
	}

	public Set<Point2D> makeMove(Point2D move) {
		return board.makeMove(move, player.color());
	}

	public int getBlackScore() {
		return board.count(SquareState.BLACK);
	}

	public int getWhiteScore() {
		return board.count(SquareState.WHITE);
	}

	public Player getWinner() {
		return getBlackScore() < getWhiteScore() ? Player.WHITE : Player.BLACK;
	}

	public boolean isDraw() {
		return getBlackScore() == getWhiteScore();
	}
	public boolean endOfGame() {
		return board.isFull() || checkZeroScore() || pass == 2;
	}

	private boolean checkZeroScore() {
		return getBlackScore() == 0 || getWhiteScore() == 0;
	}

	public void changeTurn() {
		player = player.opponent();
	}

	public Player currentPlayer() {
		return player;
	}
	public void init() {
		board.init();
		player = Player.BLACK;
		pass = 0;
	}

	public void setDifficulty(DifficultyLevel type) {
		depth = type.level;
	}

	public Point2D evalMove() {
		NegaMax searcher = new NegaMax();
		ScoreEval evalfunction = new ScoreEval(64);
		return searcher.search(board, player, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, evalfunction).getPoint();
	}

	private static class ControllerHolder {

		private static final Controller INSTANCE = new Controller();
	}

	public static Controller getInstance() {
		return ControllerHolder.INSTANCE;
	}
}
