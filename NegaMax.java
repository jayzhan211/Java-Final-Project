package game;


import javafx.geometry.Point2D;
import java.util.Set;

public class NegaMax{

	public SearchResult search(final Board board, final Player player, int alpha, int beta, final int depth, final ScoreEval evfunction) {
		if (depth <= 0 || isEndState(board)) 
			return new SearchResult(null, evfunction.evaluate(board, player));
		else { 
			Set<Point2D> possibleMoves = MoveExplorer.explore(board, player.color());
			SearchResult best = new SearchResult(null, alpha);
			if (possibleMoves.isEmpty()) { /* turn is lost - check next player */
				possibleMoves = MoveExplorer.explore(board, player.opponent().color());
				if (possibleMoves.isEmpty()) { /* end of game - is there a winner ? */
					switch (Integer.signum(board.count(player.color()) - board.count(player.opponent().color()))) {
						case -1:
							best = new SearchResult(null, Integer.MIN_VALUE);
							break;
						case 0:
							best = new SearchResult(null, 0);
							break;
						case 1:
							best = new SearchResult(null, Integer.MAX_VALUE);
							break;
					}
				}
				else { /* game continues - no moves to check */
					best = search(board, player.opponent(), -beta, -alpha, depth - 1, evfunction).negated();
				}
			}
			else{ /* check the score of each move */
				for (Point2D nextPossibleMove : possibleMoves) {
					Board subBoard = board.clone();
					subBoard.makeMove(nextPossibleMove, player.color());
					int score = search(subBoard, player.opponent(), -beta, -alpha, depth - 1, evfunction).negated().getScore();
					if (alpha < score) {
						alpha = score;
						best = new SearchResult(nextPossibleMove, score);
					}
					/* Alpha Beta Pruning */
					if (alpha >= beta) {
						return best;
					}
				}
			}
			return best;
		}
	}
	private boolean isEndState(final Board board) {
		return	board.isFull() ||
				board.count(SquareState.BLACK) == 0||
				board.count(SquareState.WHITE) == 0;
	}
}
