package game;

import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.scene.transform.Rotate;

public class Board_PvC extends Board{
	public void placePiece(double x,double y) {
		cellx=(int) (x / cell_width);
		celly = (int) (y / cell_height);
		
        //EndGmae
        if(!in_play)return ;
        //Have Piece
        if(!in_board(cellx, cellx))return;
        if(board[cellx][celly].getpiece()!=0) return ;
        
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		can_verse[i][j]=false;
        //player can move
        if(determinevalidmove(cellx,celly)) {
        	cur_state++;
        	board[cellx][celly].setpiece(currentPlayer);
        	flip(cellx,celly);
        	
        }
        swapPlayer();
        updatescore();
        checkgameEnd();
        if(!in_play)findWinner();
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board_state[cur_state][i][j].setpiece(board[i][j].getpiece());
        computer_turn();
       
        
	}
	private void computer_turn() {
		Timeline timeline=new Timeline();
	}
}
