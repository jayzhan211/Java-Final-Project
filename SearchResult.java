package game;
import javafx.geometry.Point2D;

public class SearchResult {
	private Point2D point;
	private int score;

	public int getScore() {
		return score;
	}

	public Point2D getPoint() {
		return point;
	}

	public SearchResult negated() {
		return new SearchResult(point, -score);
	}
	public SearchResult(Point2D nextPossibleMove, int score) {
		this.point = nextPossibleMove;
		this.score = score;
	}
}