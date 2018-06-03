package game;


import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;

public class ScoreEval implements Evaluation {

	private Set<Point2D> corners;
	private int weight;
	private int tot_score;

	public ScoreEval(int weight) {
		this.weight = weight;
		this.corners = new HashSet<Point2D>(4);
		this.corners.add(new Point2D(0, 0));
		this.corners.add(new Point2D(Board.Board_Height, 0));
		this.corners.add(new Point2D(0, Board.Board_Width));
		this.corners.add(new Point2D(Board.Board_Height, Board.Board_Width));
	}
	@Override
	public int evaluate(Board board, Player player) {
		int score = board.count(player.color());
		for (Point2D p : corners) {
			if (board.getSquareState(p) == player.color()) {
				score += weight;
			}
		}
		tot_score+=score;
		tot_score+=board.count(player.color()) - board.count(player.opponent().color());
		tot_score+=board.count(player.color());
		return tot_score;
	}
}
