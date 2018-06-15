package game;



import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import javafx.geometry.Point2D;
public class MoveExplorer {


	private static boolean shouldSearch(final Board board, final Point2D cur, final Direction direction) {
		Point2D nextPoint = direction.next(cur);
		return pointIsValid(nextPoint) ? board.getSquareState(nextPoint) == board.getSquareState(cur).opposite() : false;
	}
	private static boolean pointIsValid(Point2D point) {
		return point.getX() >= 0 && point.getX() < Board.BOARD_LENGTH
		       && point.getY() >= 0 && point.getY() < Board.BOARD_WIDTH;
	}

	public static Set<Point2D> explore(final Board board, final SquareState state) {
		Set<Point2D> possibleMoves = new HashSet<Point2D>();
		Set<Point2D> statePoints = board.getSquares(state);
		for (Point2D cur : statePoints) {
			for (Direction direction : Direction.values()) {
				if (shouldSearch(board, cur, direction)) {
					Point2D nextPoint = direction.next(cur);
					nextPoint = direction.next(nextPoint);
					while (pointIsValid(nextPoint)) {
						if (board.getSquareState(nextPoint) == state) {
							break;
						}
						else if (board.getSquareState(nextPoint) == SquareState.EMPTY) {
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
		SquareState cur_state = board.getSquareState(seed);
		for (Direction direction : Direction.values()) {
			if (shouldSearch(board, seed, direction)) {
				Point2D nextPoint = direction.next(seed);
				LinkedList<Point2D> templist = new LinkedList<Point2D>();
				while (pointIsValid(nextPoint)) {
					SquareState nextState = board.getSquareState(nextPoint);
					if (nextState == cur_state.opposite()) {
						templist.add(nextPoint);
					} else if (nextState == cur_state) {
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
