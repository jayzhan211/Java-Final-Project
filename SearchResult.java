package game;

import java.awt.Point;

import javafx.geometry.Point2D;

/**
 * A search results consists of a mapping of a move to it's result
 * obtained by the evaluation method that was chosen
 *
 * Alternatively a we could maintain a hash table
 * of moves/keys to results/values, but we don't
 * actually need to store more the search result
 * with the best move, at a time. That is the map
 * would always have size 1, as if we were to insert
 * a new mapping we wouldn't need all the previous.
 */
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