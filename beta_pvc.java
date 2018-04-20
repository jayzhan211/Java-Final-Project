package game;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class Board_pvc_beta extends Board_PvC {
	private int pos_i=0,pos_j=0;
	private final int MAX=Integer.MAX_VALUE;
    private final int MIN=Integer.MIN_VALUE;
    private int Board_Size;
    private PieceType first_player,next_player;
    private int maxDepth=5;
    private boolean can_move;
    private Piece_beta[][] Tmp_board;

	public Board_pvc_beta(int Board_Size) {

		super(Board_Size);

		this.Board_Size=Board_Size;
		this.first_player=first_player;
		this.next_player=next_player;

	}
	public void computer_turn() {
		System.out.println("123ssaas");
		Timeline timeline=new Timeline(new KeyFrame(Duration.millis(Computer_delay), e->{


			Move best_move=alpha_beta_prunning(board);
			System.out.println("zzz11");
			pos_i=best_move.getX();
			pos_j=best_move.getY();
			Event.fireEvent(Square[pos_i][pos_j], new MouseEvent(MouseEvent.MOUSE_CLICKED, Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2,Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));

		}));
		timeline.play();
	}

	private Move alpha_beta_prunning(Piece_beta[][] board) {
		Move find_move=max_Value(new_board(board), MIN,MAX, 0,-1,-1,new_board(board));
		return find_move;
	}
	private Piece_beta[][] new_board(Piece_beta[][] board) {
		Piece_beta[][] cur_state=new Piece_beta[8][8];
		//copy to cur_state
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				cur_state[i][j]=new Piece_beta(Board_Size, PieceType.NONE);
				cur_state[i][j].setType(board[i][j].getType());
			}
		return cur_state;
	}
	private Move max_Value(Piece_beta[][] board ,int alpha,int beta,int depth,int last_x,int last_y,Piece_beta[][] last_board) {
		if(in_play||depth==maxDepth) {
			Move move=new Move(last_x,last_y,evaluate(board));
			return move;
		}
		Move max_move=new Move(MIN);
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				can_move=false;
				check_next_move(i,j,board,next_player,first_player);
				if(can_move==false)continue;
				Move tmp=min_Value(new_board(Tmp_board), alpha, beta, depth+1, i, j,board);
				if(tmp.getValue()>=max_move.getValue()) {
					if(tmp.getValue()>=beta)return tmp;
					max_move.setX(last_x);
					max_move.setY(last_y);
					max_move.setValue(tmp.getValue());
				}
				alpha=Math.max(alpha,tmp.getValue());
			}
		return max_move;
	}
	private Move min_Value(Piece_beta[][] board ,int alpha,int beta,int depth,int last_x,int last_y,Piece_beta[][] last_board) {
		if(in_play||depth==maxDepth) {
			Move move=new Move(last_x,last_y,evaluate(board));
			return move;
		}
		Move min_move=new Move(MIN);
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				can_move=false;
				check_next_move(i,j,board,next_player,first_player);
				if(can_move==false)continue;
				Move tmp=min_Value(new_board(Tmp_board), alpha, beta, depth+1, i, j,board);
				if(tmp.getValue()<=min_move.getValue()) {
					if(tmp.getValue()<=alpha)return tmp;
					min_move.setX(last_x);
					min_move.setY(last_y);
					min_move.setValue(tmp.getValue());
				}
				beta=Math.min(beta,tmp.getValue());
			}
		return min_move;
	}
	private void check_next_move(int x,int y,Piece_beta[][] board,PieceType cur_player,PieceType nxt_player) {
			Tmp_board=new_board(board);
			if(Tmp_board[x][y].getType()==PieceType.NONE&&in_board(x,y)) {
				for(int ti=0;ti<8;ti++)
			        for(int tj=0;tj<8;tj++) {
		        		can_verse[ti][tj]=false;
		        	}

				if(Tmp_board[x][y].getType()==PieceType.NONE&&in_board(x,y)) {
					for(int[] way:direction) {
						int tx=x+way[0],ty=y+way[1];
						if(!in_board(tx, ty))continue;
						int cnt=0;
						while(in_board(tx, ty)&&board[tx][ty].getType()==nxt_player) {
							tx+=way[0];
							ty+=way[1];
							cnt++;
						}
						if(in_board(tx, ty)&&board[tx][ty].getType()==cur_player&&cnt>0) {

							tx-=way[0];
							ty-=way[1];
							while(tx!=x||ty!=y) {
								can_verse[tx][ty]=true;
								tx-=way[0];
								ty-=way[1];
							}
							can_move=true;
						}

					}

				}
				if(can_move) {
					Tmp_board[x][y].setType(cur_player);
					for(int i=0;i<8;i++)
						for(int j=0;j<8;j++) {
							if(can_verse[i][j]) {
								Tmp_board[i][j].setType(cur_player);
							}

						}

				}

		}
	}

}
