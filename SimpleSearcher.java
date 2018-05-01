package game;

interface SimpleSearcher {
	SearchResult simpleSearch(final Board board, final Player player,
				  final int depth, final Evaluation function);
}