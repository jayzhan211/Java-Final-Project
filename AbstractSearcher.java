package game;
public abstract class AbstractSearcher implements Searcher{

	@Override
	public abstract SearchResult search(final Board board, final Player player, int alpha,int beta, final int depth, final Evaluation function);
	protected int max(int a, int b) {
		return Math.max(a, b);
	}
	protected int min(int a, int b) {
		return Math.min(a, b);
	}
	protected boolean isEndState(final Board board) {
		return board.isFull()|| board.count(SquareState.BLACK) == 0|| board.count(SquareState.WHITE) == 0;
	}
}