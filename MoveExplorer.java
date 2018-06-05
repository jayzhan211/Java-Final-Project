package game;



import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javafx.geometry.Point2D;
public class MoveExplorer {


	private static boolean shouldSearch(final Board board, final Point2D seed, final Direction direction) {
		Point2D nextPoint = direction.next(seed);
		return pointIsValid(nextPoint) ? board.getSquareState(nextPoint) == board.getSquareState(seed).opposite() : false;
	}
	private static boolean pointIsValid(Point2D point) {
		return point.getX() >= 0 && point.getX() < Board.BOARD_LENGTH
		       && point.getY() >= 0 && point.getY() < Board.BOARD_WIDTH;
	}

	public static Set<Point2D> explore(final Board board, final SquareState state) {
		Set<Point2D> possibleMoves = new HashSet<Point2D>();
		Set<Point2D> statePoints = board.getSquares(state);
		for (Point2D seed : statePoints) {
			for (Direction direction : Direction.values()) {
				if (shouldSearch(board, seed, direction)) {
					Point2D nextPoint = direction.next(seed);
					nextPoint = direction.next(nextPoint);
					while (pointIsValid(nextPoint)) {
						if (board.getSquareState(nextPoint) == state) {
							break;
						} else if (board.getSquareState(nextPoint) == SquareState.EMPTY) {
							possibleMoves.add(nextPoint);
							break;
						}
						nextPoint = direction.next(nextPoint);
					}
				}
			}
		}
		return possibleMoves;
	}
	public static Set<Point2D> squaresToFill(final Board board, final Point2D seed) {
		Set<Point2D> filledlist = new HashSet<Point2D>();
		SquareState seedState = board.getSquareState(seed);
		for (Direction direction : Direction.values()) {
			if (shouldSearch(board, seed, direction)) {
				Point2D nextPoint = direction.next(seed);
				LinkedList<Point2D> templist = new LinkedList<Point2D>();
				while (pointIsValid(nextPoint)) {
					SquareState nextState = board.getSquareState(nextPoint);
					if (nextState == seedState.opposite()) {
						templist.add(nextPoint);
					} else if (nextState == seedState) {
						filledlist.addAll(templist);
						break;
					} else if (nextState == SquareState.EMPTY) {
						break;
					}
					nextPoint = direction.next(nextPoint);
				}
			}
		}
		return filledlist;
	}
}
