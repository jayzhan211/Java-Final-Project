package game;

import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;

public class Board_PvC extends Board_Beta{
	public Board_PvC(int Board_Size) {
		super(Board_Size);

	}
	public void placePiece(double x,double y) {
		final int cellx = (int) (x / Board_Size);
        final int celly = (int) (y / Board_Size);

        //EndGmae
        if(!in_play)return ;
        //Have Piece
        if(!in_board(cellx, cellx))return;
        if(board[cellx][celly].getType()!=PieceType.NONE) return ;

        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		can_verse[i][j]=false;
        //player can move
        if(determinevalidmove(cellx,celly)) {
        	cur_state++;
        	board[cellx][celly].setType(currentPlayer);
        	flip(cellx,celly);

        }
        swapPlayer();
        updatescore();
        checkgameEnd();
        if(!in_play)findWinner();
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board_state[cur_state][i][j].setType(board[i][j].getType());
        computer_turn();


	}
	private void computer_turn() {
		Timeline timeline=new Timeline();
	}
}
