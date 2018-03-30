package game;
import java.util.*;
public class State {
	
	public Board board=new Board();
	public  void ff() {
		for(int i=0;i<8;i++) {
			System.out.print(board.board[i][i]+i+i+i*i);
		}
	}
		
}
