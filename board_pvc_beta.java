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
    private int maxDepth=5;
    private int Board_Size;
	public Board_pvc_beta(int Board_Size) {

		super(Board_Size);
		
		this.Board_Size=Board_Size;

	}
	public void computer_turn() {
		System.out.println("123ssaas");
		Timeline timeline=new Timeline(new KeyFrame(Duration.millis(Computer_delay), e->{


			alpha_beta_prunning();


			Event.fireEvent(Square[pos_i][pos_j], new MouseEvent(MouseEvent.MOUSE_CLICKED, Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2,Square[pos_i][pos_j].getLayoutX()/2, Square[pos_i][pos_j].getLayoutY()/2, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));

		}));
		timeline.play();
	}

	private void alpha_beta_prunning() {
		System.out.println("123ss");
		Piece_beta[][] cur_state=new Piece_beta[8][8];
		//copy to cur_state
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {

				cur_state[i][j]=new Piece_beta(Board_Size, PieceType.NONE);
				cur_state[i][j].setType(board[i][j].getType());
			}

	}

}
