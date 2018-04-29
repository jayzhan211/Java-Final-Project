package game;

import java.awt.Point;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javafx.geometry.Point2D;


/**
 * the game board is a mapping of states to points
 *
 * @author c00kiemon5ter
 */
public final class Board{

	public static final int BOARD_LENGTH = 8;
	public static final int BOARD_WIDTH = 8;
	private Map<Point2D, SquareState> board;

	public Board() {
		board = new HashMap<Point2D, SquareState>(BOARD_LENGTH * BOARD_WIDTH);
		init();
	}

	/**
	 * Deep copy constructor.
	 *
	 * @param board
	 */
	private Board(Map<Point2D, SquareState> board) {
		this.board = new HashMap<Point2D, SquareState>(board.size());
		for (Point2D point : board.keySet()) {
			this.board.put(new Point2D(point.getX(),point.getY()), board.get(point));
		}
	}

	public void init() {

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board.put(new Point2D(i, j), SquareState.EMPTY);
		
		board.put(new Point2D(3, 3), SquareState.WHITE);
		board.put(new Point2D(3, 4), SquareState.BLACK);
		board.put(new Point2D(4, 3), SquareState.BLACK);
		board.put(new Point2D(4, 4), SquareState.WHITE);
	}

	public SquareState getSquareState(Point2D point) {
		return board.get(point);
	}

	public Set<Point2D> getSquares(SquareState state) {
		Set<Point2D> points = new HashSet<Point2D>();
		for (Point2D point : board.keySet()) {
			if (board.get(point) == state) {
				points.add(point);
			}
		}
		return points;
	}

	public boolean isFull() {
		for (Point2D point : board.keySet()) {
			if (board.get(point) == SquareState.EMPTY) {
				return false;
			}
		}
		return true;
	}

	public int count(SquareState state) {
		int count = 0;
		for (Point2D point : board.keySet()) {
			if (board.get(point) == state) {
				count++;
			}
		}
		return count;
	}

	public Set<Point2D> getPossibleMoves(Player player) {
		return MoveExplorer.explore(this, player.color());
	}

	public void markPossibleMoves(Set<Point2D> moves) {
		for (Point2D point : moves) {
			board.put(point, SquareState.PSSBL);
		}
	}

	public void unmarkPossibleMoves() {
		for (Point2D point : board.keySet()) {
			if (board.get(point) == SquareState.PSSBL) {
				board.put(point, SquareState.EMPTY);
			}
		}
	}

	public void markState(Set<Point2D> points, SquareState state) {
		for (Point2D point : points) {
			board.put(point, state);
		}
	}

	public Set<Point2D> makeMove(Point2D move, SquareState state) {
		board.put(move, state);
		Set<Point2D> changedSquares = MoveExplorer.squaresToFill(this, move);
		markState(changedSquares, state);
		changedSquares.add(move);
		return changedSquares;
	}

	@Override
	public String toString() {
		Point point = new Point();
		StringBuilder sb = new StringBuilder();
		sb.append("  A B C D E F G H");
		for (point.x = 0; point.x < BOARD_LENGTH; point.x++) {
			sb.append('\n').append(point.x + 1);
			for (point.y = 0; point.y < BOARD_WIDTH; point.y++) {
				sb.append(' ').append(board.get(point).symbol());
			}
		}
		sb.append('\n');
		return sb.toString();
	}

	public String toStringWithStats() {
		StringBuilder sb = new StringBuilder();
		String[] rows = toString().split("\n");
		for (int row = 0; row < rows.length; row++) {
			sb.append('\n').append(rows[row]);
			switch (row) {
				case 2:
					sb.append('\t').append(SquareState.BLACK.symbol()).
						append(' ').append(Player.BLACK).
						append(": ").append(count(SquareState.BLACK));
					break;
				case 4:
					sb.append('\t').append(SquareState.WHITE.symbol()).
						append(' ').append(Player.WHITE).
						append(": ").append(count(SquareState.WHITE));
					break;
			}
		}
		sb.append('\n');
		return sb.toString();
	}

	public String toStringWithStatsTurn(Player player) {
		StringBuilder sb = new StringBuilder();
		String[] rows = toString().split("\n");
		for (int row = 0; row < rows.length; row++) {
			sb.append('\n').append(rows[row]);
			switch (row) {
				case 2:
					sb.append('\t').append(SquareState.BLACK.symbol()).
						append(' ').append(Player.BLACK).
						append(": ").append(count(SquareState.BLACK));
					break;
				case 4:
					sb.append('\t').append(SquareState.WHITE.symbol()).
						append(' ').append(Player.WHITE).
						append(": ").append(count(SquareState.WHITE));
					break;
				case 6:
					sb.append('\t').append(player).append("'s turn!");
					break;
			}
		}
		sb.append('\n');
		return sb.toString();
	}

	/**
	 * Deep copy of this board.
	 *
	 * @return
	 */
	@Override
	public Board clone() {
		return new Board(this.board);
	}
}
