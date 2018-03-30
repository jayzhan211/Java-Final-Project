package game;

public class Board {
	int[][] board=new int[8][8];
	public Board(Board a) {
		this.board=a.board;
	}
	public Board(int [][]a) {
		this.board=a;
	}
	public Board(){}
}
