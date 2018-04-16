package game;



import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class Board_Beta extends GridPane{
	protected String background_color = "#654321";
	protected Piece_beta[][] board;
	protected Piece_beta[][][] board_state;
	protected Pane[][] Square;
    protected int Board_Size;
    protected Duration flipDuration;
    protected int flip_duration=500;

    protected PieceType currentPlayer;
    protected PieceType nextPlayer;
	//private double cell_width;
   // private double cell_height;
    protected int B_score;
    protected int W_score;
    protected int cur_state;
    protected boolean[][] can_verse;
    protected int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    protected boolean in_play;

	public Board_Beta(int Board_Size) {

		super();
		currentPlayer=PieceType.BLACK;
		nextPlayer=PieceType.WHITE;
        can_verse=new boolean[8][8];
        cur_state=0;
		board=new Piece_beta[8][8];
		board_state=new Piece_beta[64][8][8];
		Square=new Pane[8][8];

		this.Board_Size=Board_Size;
		this.flipDuration= Duration.millis(flip_duration);

		for(int i=0;i<8;i++) {
			getRowConstraints().add( new RowConstraints(Board_Size));
			getColumnConstraints().add( new ColumnConstraints(Board_Size));
		}

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {



				Square[i][j]=new Pane();
				board[i][j]=new Piece_beta(Board_Size, PieceType.NONE);
				Square[i][j].setStyle("-fx-background-color: " + background_color + ";");
				Square[i][j].getChildren().add(board[i][j]);

				//getChildren().add(board[i][j]);
				//getChildren().add(Square[i][j]);

				add(Square[i][j],i,j);

			}
		for(int k=0;k<64;k++)
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++) {

					board_state[k][i][j]=new Piece_beta(Board_Size, PieceType.NONE);
					//getChildren().add(board_state[k][i][j]);
				}


		board[3][3].setType(PieceType.WHITE);
		board[4][4].setType(PieceType.WHITE);
		board[3][4].setType(PieceType.BLACK);
		board[4][3].setType(PieceType.BLACK);

		board_state[0][3][3].setType(PieceType.WHITE);
		board_state[0][4][4].setType(PieceType.WHITE);
		board_state[0][3][4].setType(PieceType.BLACK);
		board_state[0][4][3].setType(PieceType.BLACK);

		setGridLinesVisible(true);
		setAlignment(Pos.CENTER);

		resetGame();
	}

	public void placePiece(double x,double y) {
		final int cellx = (int) (x / Board_Size);
        final int celly = (int) (y / Board_Size);

        //System.out.println(cellx+" "+celly);
        //EndGmae
        if(!in_play)return ;
        //Have Piece
        if(!in_board(cellx, cellx))return;
        if(board[cellx][celly].getType()!=PieceType.NONE) return ;

        //System.out.println(cellx+" "+celly);
       // System.out.println(cellx+" "+celly);
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++) {
        		System.out.println(123);
        		can_verse[i][j]=false;

        	}
       // System.out.println(cellx+" "+celly);
        if(!determinevalidmove(cellx,celly))return ;
       // System.out.println(cellx+" "+celly);
        cur_state++;
        //update_board
        board[cellx][celly].setType(currentPlayer);

       // System.out.println(cellx+" "+celly);
      //  System.out.println(cellx+" "+celly);

        /*for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		if(can_verse[i][j]) {
        			if(i==cellx&&y==celly)continue;


        			Piece_beta tmp=board[i][j];
        			Piece_beta tmp2=board_state[cur_state][i][j];

        			RotateTransition firstRotator = new RotateTransition(flipDuration, tmp);
                    firstRotator.setAxis(Rotate.Y_AXIS);
                    firstRotator.setFromAngle(0);
                    firstRotator.setToAngle(90);
                    firstRotator.setInterpolator(Interpolator.LINEAR);



                    firstRotator.setOnFinished(e->{
                    		tmp.setType(nextPlayer);
                    		tmp2.setType(tmp.getType());
                    	}
                    );



                    RotateTransition secondRotator = new RotateTransition(flipDuration, tmp);
                    secondRotator.setAxis(Rotate.Y_AXIS);
                    secondRotator.setFromAngle(90);
                    secondRotator.setToAngle(180);
                    secondRotator.setInterpolator(Interpolator.LINEAR);


                    //SequentialTransition controller =new SequentialTransition(firstRotator, secondRotator);
                   // controller.play();
                    new SequentialTransition(firstRotator, secondRotator).play();

                    board[i][j]=tmp;

        		}*/
        flip(cellx,celly);
        swapPlayer();
        updatescore();
        checkgameEnd();
        if(!in_play)findWinner();
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board_state[cur_state][i][j].setType(board[i][j].getType());
       // System.out.println("666");
	}
	protected void flip(int cellx,int celly) {
		for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		if(can_verse[i][j]) {
        			if(i==cellx&&j==celly)continue;


        			Piece_beta tmp=board[i][j];
        			Piece_beta tmp2=board_state[cur_state][i][j];

        			RotateTransition firstRotator = new RotateTransition(flipDuration, tmp);
                    firstRotator.setAxis(Rotate.Y_AXIS);
                    firstRotator.setFromAngle(0);
                    firstRotator.setToAngle(90);
                    firstRotator.setInterpolator(Interpolator.LINEAR);



                    firstRotator.setOnFinished(e->{

                    		tmp.setType(nextPlayer);
                    		tmp2.setType(tmp.getType());
                    	}
                    );



                    RotateTransition secondRotator = new RotateTransition(flipDuration, tmp);
                    secondRotator.setAxis(Rotate.Y_AXIS);
                    secondRotator.setFromAngle(90);
                    secondRotator.setToAngle(180);
                    secondRotator.setInterpolator(Interpolator.LINEAR);


                    //SequentialTransition controller =new SequentialTransition(firstRotator, secondRotator);
                   // controller.play();
                    new SequentialTransition(firstRotator, secondRotator).play();

                   // board[i][j]=tmp;

        		}

	}
	protected void findWinner() {
		if(B_score>W_score)
			System.out.println("Black Win~");
		else if(B_score<W_score)
			System.out.println("White Win~");
		else System.out.println("Draw");
	}
	protected void checkgameEnd() {
		if(B_score+W_score==64||B_score==0||W_score==0) {
			in_play=false;
		}
		else if(!can_move()) {
			swapPlayer();
			if(!can_move()) {
				in_play=false;

			}
		}
	}

	public void resetGame() {
		in_play=true;
		reset_board();
		//black-first
		currentPlayer=PieceType.BLACK;
		nextPlayer=PieceType.WHITE;
		B_score=W_score=2;
		cur_state=0;
	}
	private void reset_board() {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {

				board[i][j].setType(PieceType.NONE);
				board_state[0][i][j].setType(PieceType.NONE);
			}
		//white
		board[3][3].setType(PieceType.WHITE);
		board[4][4].setType(PieceType.WHITE);
		//black
		board[3][4].setType(PieceType.BLACK);
		board[4][3].setType(PieceType.BLACK);

		board_state[0][3][3].setType(PieceType.WHITE);
		board_state[0][4][4].setType(PieceType.WHITE);
		//black
		board_state[0][3][4].setType(PieceType.BLACK);
		board_state[0][4][3].setType(PieceType.BLACK);


	}
	public void previous_move() {


		if(cur_state>0) {
			cur_state--;
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++) {
					board[i][j].setType(board_state[cur_state][i][j].getType());
				}
			swapPlayer();
			updatescore();
		}
	}
	protected void updatescore() {
		B_score=W_score=0;
		for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++) {
        		if(board[i][j].getType()==PieceType.WHITE)W_score++;
        		else if(board[i][j].getType()==PieceType.BLACK)B_score++;
        	}
	}
	protected void swapPlayer() {
		PieceType tmp=currentPlayer;
		currentPlayer=nextPlayer;
		nextPlayer=tmp;
	}
	private boolean can_move() {
		 for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
	        	if(board[i][j].getType()==PieceType.NONE&&determinevalidmove(i,j))
	        		return true;
	        }
		return false;
	}
	protected boolean in_board(int x,int y) {
		return x>=0&&y>=0&&x<8&&y<8;
	}
	protected boolean determinevalidmove(final int x,final int y) {
		boolean isvalid=false;
		for(int[] way:direction) {

			int tx=x+way[0],ty=y+way[1];

			if(!in_board(tx, ty))continue;
			int cnt=0;
			while(in_board(tx, ty)&&board[tx][ty].getType()==nextPlayer) {
				tx+=way[0];
				ty+=way[1];
				cnt++;
			}
			if(in_board(tx, ty)&&board[tx][ty].getType()==currentPlayer&&cnt>0) {

				tx-=way[0];
				ty-=way[1];
				while(tx!=x||ty!=y) {
					can_verse[tx][ty]=true;
					tx-=way[0];
					ty-=way[1];
				}


				isvalid=true;
			}

		}

		return isvalid;
	}


}
