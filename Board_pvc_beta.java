package game;

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
    private int maxDepth=5;
    private boolean can_move;
    private PieceType Human,Computer;
    private Piece_beta[][] Tmp_board;
    protected Title_Label title_Label;
    private int EV[][]={
    		{1 << 24, 1,       1 << 20, 1 << 16,1 << 16,1 << 20, 1,       1 << 24},
    	    {1,       1,       1 << 16, 1 << 4, 1 << 4, 1 << 16, 1,       1     },
    	    {1 << 20, 1 << 16, 1 << 12, 1 << 8, 1 << 8, 1 << 12, 1 << 16, 1 << 20},
    	    {1 << 16, 1 << 4,  1 << 8,  0,        0,        1 << 8,  1 << 4,  1 << 16},
    	    {1 << 16, 1 << 4,  1 << 8,  0,        0,        1 << 8,  1 << 4,  1 << 16},
    	    {1 << 20, 1 << 16, 1 << 12, 1 << 8, 1 << 8, 1 << 12, 1 << 16, 1 << 20},
    	    {1,       1,       1 << 16, 1 << 4, 1 << 4, 1 << 16, 1,       1     },
    	    {1 << 24, 1,       1 << 20, 1 << 16,1 << 16,1 << 20, 1,       1 << 24}
    	};


	public Board_pvc_beta(int Board_Size) {
		super(Board_Size);
		this.Board_Size=Board_Size;
		Human=PieceType.BLACK;
		Computer=PieceType.WHITE;
	}
	public void computer_turn(){
		Timeline timeline=new Timeline(new KeyFrame(Duration.millis(Computer_delay), e->{


			Move best_move=alpha_beta_prunning(board);
			//System.out.println(board[2][2].getType());
			pos_i=best_move.getX();
			pos_j=best_move.getY();
			//System.out.println(pos_i+" "+pos_j);
			Event.fireEvent(Square[pos_i][pos_j], new MouseEvent(MouseEvent.MOUSE_CLICKED, Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2,Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));

		}));
		timeline.play();
		//System.out.println("zaaaa");
	}

	private Move alpha_beta_prunning(Piece_beta[][] board) {
		Move find_move=max_Value(new_board(board), MIN,MAX, 0,-1,-1,new_board(board));
		if(find_move.getX()==-1) {

			System.out.println(find_move.getValue());
		}
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

		if(is_game_end(board)||depth==maxDepth) {
			Move move=new Move(last_x,last_y,evaluate(board,Computer));
			if(move.getX()==-1) {
				System.out.println(move.getValue());
				System.out.println(depth);
			}
			return move;
		}
		/*System.out.println("----------");
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[i][j].getType()==PieceType.WHITE)System.out.print("W");
				else if(board[i][j].getType()==PieceType.BLACK)System.out.print("B");
				else System.out.print("N");
			}
			System.out.println();
		}*/

		Move max_move=new Move(MIN);
		int xx=-1,yy=-1;
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				if(!is_valid_move(board, Computer, Human, i, j))continue;
				Tmp_board=make_next_board(board,i,j,Computer,Human);
				//System.out.println(i+" "+j);
				xx=i;
				yy=j;
				Move tmp=min_Value(new_board(Tmp_board), alpha, beta, depth+1, i, j,new_board(board));

			//	System.out.println("TNp"+tmp.getX()+" "+tmp.getY());

				if(tmp.getValue()>=max_move.getValue()) {
					if(tmp.getValue()>=beta)return tmp;
					max_move.setX(i);
					max_move.setY(j);
					max_move.setValue(tmp.getValue());
					//System.out.println("update "+i+" "+j);
				}
				alpha=Math.max(alpha,tmp.getValue());
				//System.out.println(max_move.getX()+" "+max_move.getY());
			}
		//System.out.println(max_move.getX()+" "+max_move.getY());
		if(xx==-1||max_move.getX()==-1) {
			System.out.println("xy "+xx+" "+yy);
			//System.out.println("AB "+alpha+" "+beta);
			int f=1;
			for(int i=0;i<8;i++) {
				for(int j=0;j<8;j++) {
					if(board[i][j].getType()==PieceType.WHITE)System.out.print("W");
					else if(board[i][j].getType()==PieceType.BLACK)System.out.print("B");
					else System.out.print("N");
				}
				System.out.println("");
			}
			//if(f==1)System.out.println("D");
			//else System.out.println("GG");
		}

		/*for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[i][j].getType()==PieceType.WHITE)System.out.print("W");
				else if(board[i][j].getType()==PieceType.BLACK)System.out.print("B");
				else System.out.print("N");
			}
			System.out.println();
		}*/

		return max_move;
	}
	private Move min_Value(Piece_beta[][] board ,int alpha,int beta,int depth,int last_x,int last_y,Piece_beta[][] last_board) {
		if(is_game_end(board)||depth==maxDepth) {
			Move move=new Move(last_x,last_y,evaluate(board,Human));
			if(move.getX()==-1) {
				System.out.println(move.getValue());
				System.out.println(depth);
			}
			return move;
		}
		Move min_move=new Move(MAX);
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				if(!is_valid_move(board, Human, Computer, i, j))continue;
				Tmp_board=make_next_board(board,i,j,Human, Computer);

				//System.out.println(i+" "+j);

				Move tmp=max_Value(new_board(Tmp_board), alpha, beta, depth+1, i, j,new_board(board));
				if(tmp.getValue()<=min_move.getValue()) {
					if(tmp.getValue()<=alpha)return tmp;
					min_move.setX(i);
					min_move.setY(j);
					min_move.setValue(tmp.getValue());
				}
				beta=Math.min(beta,tmp.getValue());
			}
		return min_move;
	}
	private Piece_beta[][] make_next_board(Piece_beta[][] board,int x,int y,PieceType cur_player,PieceType nxt_player) {
		Piece_beta[][] Tmp_board=new_board(board);
		Tmp_board[x][y].setType(cur_player);
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
					Tmp_board[tx][ty].setType(cur_player);
					tx-=way[0];
					ty-=way[1];
				}
			}
		}
		return Tmp_board;
	}
	private boolean is_game_end(Piece_beta[][] board) {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				if(board[i][j].getType()==PieceType.NONE)
					return false;
		return true;
	}

	public int evaluate(Piece_beta[][] board,PieceType cur_player) {
		int tot=0;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(board[i][j].getType()==cur_player)
					tot+=EV[i][j];
			}
		}
		return tot;
	}
	private boolean is_valid_move(Piece_beta[][] board,PieceType cur_player,PieceType nxt_player,int x,int y) {
		if(!in_board(x, y))return false;
		Piece_beta[][] Tmp_board =new_board(board);
		if(Tmp_board[x][y].getType()!=PieceType.NONE)return false;
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
				return true;
			}
		}
		return false;
	}
}
