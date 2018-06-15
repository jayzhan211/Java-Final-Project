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
			if (possibleMoves.isEmpty()) { //no move ,pass
				possibleMoves = MoveExplorer.explore(board, player.opponent().color());
				if (possibleMoves.isEmpty()) { //game end
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
				else
					best = search(board, player.opponent(), -beta, -alpha, depth - 1, evfunction).negated();

			}
			else {
				boolean first_child=true;
				for (Point2D nextPossibleMove : possibleMoves) {
					Board subBoard = board.clone();
					subBoard.makeMove(nextPossibleMove, player.color());
					int score;
					if(first_child) {
						score = search(subBoard, player.opponent(), -beta, -alpha, depth - 1, evfunction).negated().getScore();
						first_child=false;
					}
					else {
						score = search(subBoard, player.opponent(), -alpha-1, -alpha, depth - 1, evfunction).negated().getScore();
						if(alpha<score&&score<beta) {
							score=search(subBoard, player.opponent(), -beta, score, depth - 1, evfunction).negated().getScore();
						}
					}
					if (alpha < score) { //update the range
						alpha = score;
						best = new SearchResult(nextPossibleMove, score);
					}
					if (alpha >= beta) //Pruning
						return best;

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
