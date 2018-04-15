package game;

import java.security.KeyStore.PrivateKeyEntry;
import java.util.*;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.scene.transform.Rotate;
import javafx.util.*;
//Black 2- White 1
public class Board_Controll extends Pane{
	
	
	private Rectangle background;
	private int currentPlayer;
	private int nextPlayer;
	private Piece [][] board;
	private boolean in_play;
	private double cell_width;
    private double cell_height;
    private Line[] horizontal;
    private Line[] vertical;
    private Translate[] horizontal_t;
    private Translate[] vertical_t;
    private int B_score;
    private int W_score;
    private Piece[][][] board_state;
    private int cur_state;
    private boolean[][] can_verse;
    private int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private int flip_duration=500;
    private Duration Flip_duration;
    
	public Board_Controll(){
		currentPlayer=2;
		nextPlayer=1;
		board=new Piece[8][8];
		background=new Rectangle();
		horizontal = new Line[8];
        horizontal_t = new Translate[8];	
        vertical = new Line[8];
        vertical_t = new Translate[8];
        can_verse=new boolean[8][8];
        board_state=new Piece[64][8][8];
        cur_state=0;
        Flip_duration=Duration.millis(flip_duration);
        
        initbackground();
        initboard();
        resetGame();
	}
	
	public void resetGame() {
		in_play=true;
		reset_board();
		//black-first
		currentPlayer=2;
		nextPlayer=1;
		B_score=W_score=2;
		cur_state=0;
	}
	public void previous_move() {
		
		
		if(cur_state>0) {
			cur_state--;
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++) {
					board[i][j].setpiece(board_state[cur_state][i][j].getpiece());
				}
			swapPlayer();
			updatescore();
		}
	}
	
	private void reset_board() {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				
				board[i][j].setpiece(0);
				board_state[0][i][j].setpiece(0);
			}
		//white
		board[3][3].setpiece(1);
		board[4][4].setpiece(1);
		//black
		board[3][4].setpiece(2);
		board[4][3].setpiece(2);
		
		board_state[0][3][3].setpiece(1);
		board_state[0][4][4].setpiece(1);
		//black
		board_state[0][3][4].setpiece(2);
		board_state[0][4][3].setpiece(2);	

		
	}
	
	
	public void placePiece(double x,double y) {
		
		final int cellx = (int) (x / cell_width);
        final int celly = (int) (y / cell_height);
        
        
        
        //EndGmae
        if(!in_play)return ;
        //Have Piece
        if(!in_board(cellx, cellx))return;
        if(board[cellx][celly].getpiece()!=0) return ;
        
 
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		can_verse[i][j]=false;
        
        if(!determinevalidmove(cellx,celly))return ;
		
        cur_state++;
        //update_board
        board[cellx][celly].setpiece(currentPlayer);
        
        
      //  System.out.println(cellx+" "+celly);
        
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		if(can_verse[i][j]) {
        			if(i==cellx&&y==celly)continue;
        			
        			
        			Piece tmp=board[i][j];
        			Piece tmp2=board_state[cur_state][i][j];
        			
        			RotateTransition firstRotator = new RotateTransition(Flip_duration, tmp);
                    firstRotator.setAxis(Rotate.Y_AXIS);
                    firstRotator.setFromAngle(0);
                    firstRotator.setToAngle(90);
                    firstRotator.setInterpolator(Interpolator.LINEAR);
                    

                    
                    firstRotator.setOnFinished(e->{
                    		tmp.setpiece(nextPlayer);
                    		tmp2.setpiece(tmp.getpiece());
                    	}
                    );
                    	
                    	
                   
                    RotateTransition secondRotator = new RotateTransition(Flip_duration, tmp);
                    secondRotator.setAxis(Rotate.Y_AXIS);
                    secondRotator.setFromAngle(90);
                    secondRotator.setToAngle(180);
                    secondRotator.setInterpolator(Interpolator.LINEAR);


                    //SequentialTransition controller =new SequentialTransition(firstRotator, secondRotator);
                   // controller.play();
                    new SequentialTransition(firstRotator, secondRotator).play();
                    
                    board[i][j]=tmp;
                    
        		}
        
        swapPlayer();
        updatescore();
        checkgameEnd();
        if(!in_play)findWinner();
        for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board_state[cur_state][i][j].setpiece(board[i][j].getpiece());
       // System.out.println("666");
	}
	private void findWinner() {
		if(B_score>W_score) 
			System.out.println("Black Win~");
		else if(B_score<W_score)
			System.out.println("White Win~");
		else System.out.println("Draw");
	}
	private void checkgameEnd() {
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
	private boolean can_move() {
		 for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
	        	if(board[i][j].getpiece()==0&&determinevalidmove(i,j))
	        		return true;
	        }
		return false;
	}
	private void updatescore() {
		B_score=W_score=0;
		for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++) {
        		if(board[i][j].getpiece()==1)W_score++;
        		else if(board[i][j].getpiece()==2)B_score++;
        	}
	}
	
	private void swapPlayer() {
		currentPlayer=3-currentPlayer;
		nextPlayer=3-nextPlayer;
	}
	private int getpiece(final int x,final int y) {
		return x>=0&&y>=0&&x<8&&y<8?board[x][y].getpiece():-1;
	}
	private boolean in_board(int x,int y) {
		return x>=0&&y>=0&&x<8&&y<8;
	}
	
	
	private boolean determinevalidmove(final int x,final int y) {
		boolean isvalid=false;
		for(int[] way:direction) {
			
			int tx=x+way[0],ty=y+way[1];
			
			if(!in_board(tx, ty))continue;
			int cnt=0;
			while(in_board(tx, ty)&&board[tx][ty].getpiece()==nextPlayer) {
				tx+=way[0];
				ty+=way[1];
				cnt++;
			}
			if(in_board(tx, ty)&&board[tx][ty].getpiece()==currentPlayer&&cnt>0) {
			
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
	//System.out.println("");
	private void initbackground() {
		
        getChildren().add(background);
        background.setFill(Color.rgb(249, 214, 91));
       
        
        
        for(int i = 0; i < 8; i++) {
            horizontal[i] = new Line();
            horizontal[i].setStroke(Color.BLACK);
            horizontal[i].setStartX(0);
            horizontal[i].setStartY(0);
            horizontal[i].setEndY(0);
            horizontal_t[i] = new Translate(0,0);
            horizontal[i].getTransforms().add(horizontal_t[i]);
            getChildren().add(horizontal[i]);
        }

        for(int i = 0; i < 8; i++) {
            vertical[i] = new Line();
            vertical[i].setStroke(Color.BLACK);
            vertical[i].setStartX(0);
            vertical[i].setStartY(0);
            vertical[i].setEndX(0);
            vertical_t[i] = new Translate(0,0);
            vertical[i].getTransforms().add(vertical_t[i]);
            getChildren().add(vertical[i]);
        }
       
        
	}
	private void initboard() {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				board[i][j]=new Piece(0);
				getChildren().add(board[i][j]);
			}

		for(int k=0;k<64;k++)
			for(int i=0;i<8;i++)
				for(int j=0;j<8;j++) {
					board_state[k][i][j]=new Piece(0);
					getChildren().add(board_state[k][i][j]);
				}
		
	}
	public void resize(double width,double height) {
		
		super.resize(width,height);

		cell_width=width/8.0;
		cell_height=height/8.0;
		
		background.setWidth(width);
        background.setHeight(height);
       
        
        horizontalResizeRelocate(width);
        verticalResizeRelocate(height);
        pieceResizeRelocate();
	}
	 private void pieceResizeRelocate() {
	        for(int i = 0; i < 8; i++)
	            for(int j = 0; j < 8; j++) {

	                board[i][j].resize(cell_width, cell_height);
	                board[i][j].relocate(i * cell_width, j * cell_height);
	            }
	        
	    }
	public void horizontalResizeRelocate(double width) {
        for(int i = 0; i < 8; i++) {
            horizontal_t[i].setY((i + 1) * cell_height);
            horizontal[i].setEndX(width);
        }

    }
	public void verticalResizeRelocate(double height) {
        for(int i = 0; i < 8; i++) {
            vertical_t[i].setX((i + 1) * cell_width);
            vertical[i].setEndY(height);
        }

    }
}