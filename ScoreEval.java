package game;


public class ScoreEval{

	private Board board;
	private Player player;
	public ScoreEval() {

	}
	public int evaluate(Board board, Player player) {
		int cnt=board.getPiecescount();
		if(cnt<24||cnt>44) {
			return board.board_val(player);
		}

		this.board=board;
		this.player=player;

		int score=0;
		score+=mobility();
		score+=board.board_val(player);
		return score;
	}
	public int mobility() {
		int player_moves=board.getPossibleMovesCount(player);
		int opponent_moves = board.getPossibleMovesCount(player.opponent());
		return player_moves-opponent_moves;
	}
}
