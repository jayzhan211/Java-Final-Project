package game;

import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import javafx.geometry.Point2D;

public final class Board{

	public static final int BOARD_LENGTH = 8;
	public static final int BOARD_WIDTH = 8;
	private Map<Point2D, SquareState> board;

	public Board() {
		board = new HashMap<Point2D, SquareState>(BOARD_LENGTH * BOARD_WIDTH);
		init();
	}
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
	public Board clone() {
		return new Board(this.board);
	}
}
