package game;


public class ScoreEval{

	private Board board;
	private Player player;
	//private final int alpha=10000; //score variable
	public ScoreEval() {

	}

	public int evaluate(Board board, Player player) {
		this.board=board;
		this.player=player;

		int score=0;
		score+=mobility();
		//score+=pieces();
		score+=board.board_val(player);
		return score;

	}
	public int mobility() {
		int player_moves=board.getPossibleMovesCount(player);
		int opponent_moves = board.getPossibleMovesCount(player.opponent());
		return player_moves-opponent_moves;
	}
	/*public long pieces() {
		return (board.count(player.color())-board.count(player.opponent().color()));

	}*/




}
