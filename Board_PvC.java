import java.awt.Robot;

import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.util.Duration;

public class Board_PvC extends Board_Beta{
	double Computer_delay=1000;
	public Board_PvC(int Board_Size) {

		super(Board_Size);
	}
	public void placepiece() {

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				final int f_i=i,f_j=j;
				final Piece_beta currentplayer=board[i][j];
				Square[i][j].setOnMouseClicked(e->{
					//System.out.println("here");
					if(!in_play)return ;
					if(currentplayer.getType()==PieceType.NONE&&in_board(f_i, f_j)) {
						for(int ti=0;ti<8;ti++)
					        for(int tj=0;tj<8;tj++) {
				        		can_verse[ti][tj]=false;
				        	}
						//player can move
						if(determinevalidmove(f_i, f_j)) {

							cur_state++;

							board[f_i][f_j].setType(currentPlayer);
							flip(f_i,f_j);
							for(int ti=0;ti<8;ti++)
						        for(int tj=0;tj<8;tj++)
									board_state[cur_state][ti][tj].setType(board[ti][tj].getType());
							swapPlayer();
							updatescore();
							checkgameEnd();
					        if(!in_play) {
					        	findWinner();
					        	return ;
					        }
					        //computer move
					        if(currentPlayer==PieceType.WHITE)
					        	computer_turn();
						}
						


					}


				});
			}
	}
	private void computer_turn() {
		System.out.println("123");
		Timeline timeline=new Timeline(new KeyFrame(Duration.millis(Computer_delay), e->{



				int pos_i =0,pos_j=0;
				int tmp_cnt=0;
				for(int i=0;i<8;i++)
					for(int j=0;j<8;j++) {

						if(board[i][j].getType()==PieceType.NONE&&in_board(i,j)) {
							for(int ti=0;ti<8;ti++)
						        for(int tj=0;tj<8;tj++) {
					        		can_verse[ti][tj]=false;
					        	}
							if(determinevalidmove(i, j)) {
								int pos_cnt=flip_cnt();
								if(pos_cnt>tmp_cnt) {
									tmp_cnt=pos_cnt;
									pos_i=i;
									pos_j=j;
								}

							}
						}
				}
				Event.fireEvent(Square[pos_i][pos_j], new MouseEvent(MouseEvent.MOUSE_CLICKED, Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2,Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
				//(node, new MouseEvent(MouseEvent.MOUSE_CLICKED, node.getLayoutX()/2, node.getLayoutY()/2, node.getLayoutX()/2, node.getLayoutY()/2, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
		}));
		timeline.play();
	}
	private int flip_cnt() {
		int cnt=0;
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				if(can_verse[i][j])
					cnt++;
		return cnt;
	}
}
