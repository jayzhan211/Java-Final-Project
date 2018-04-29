package game;
interface Searcher {

	SearchResult search(final Board board, final Player player, int alpha,
			    int beta, final int depth, final Evaluation function);
}