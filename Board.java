package game;




import java.security.KeyStore.PrivateKeyEntry;
import java.util.*;

	

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;
import javafx.util.Pair;


//Black 2- White 1
public class Board extends Pane{
	
	
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
    
    
    //private int cnt=0;
    //private int reverse_cnt=0; 
    
    private boolean[][] can_verse;
    
    private int[][] direction = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    
	public Board(){
		currentPlayer=2;
		nextPlayer=1;
		
		board=new Piece[8][8];
		
		background=new Rectangle();
		
		horizontal = new Line[8];
        horizontal_t = new Translate[8];	
        vertical = new Line[8];
        vertical_t = new Translate[8];
        
        can_verse=new boolean[8][8];

        
        
        
        initbackground();
        initboard();
        resetGame();
        
        
	}
	public void resetGame() {
		
		in_play=true;
		reset_board();
		//white
		board[3][3].setpiece(1);
		board[4][4].setpiece(1);
		//black
		board[3][4].setpiece(2);
		board[4][3].setpiece(2);
		
		//black-first
		currentPlayer=2;
		nextPlayer=1;
		
		B_score=W_score=2;
		
		
		
	}
	private void reset_board() {
		
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board[i][j].setpiece(0);
		
	}
	
	
	public void placePiece(double x,double y) {
		int cellx = (int) (x / cell_width);
        int celly = (int) (y / cell_height);
        
        System.out.print("place\n");
        //EndGmae
        if(!in_play)return ;
        //Have Piece
        if(!in_board(cellx, cellx))return;
        if(board[cellx][celly].getpiece()!=0) return ;
        
        
       
       // reverse_cnt=0;
        System.out.println("asdasd");
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		can_verse[i][j]=false;
        System.out.println("60229dd]n");
        if(!determinevalidmove(cellx,celly))return ;
		
        System.out.print("pass222\n");
        
        board[cellx][celly].setpiece(currentPlayer);
        
        for(int i=0;i<8;i++)
        	for(int j=0;j<8;j++)
        		if(can_verse[i][j]) {
        			board[i][j].setpiece(currentPlayer);
        			//reverse_cnt++;
        		}
        
        swapPlayer();
        
        updatescore();
        
        
        checkgameEnd();
        if(!in_play)findWinner();
        
        System.out.print("place\n");
        
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
		System.out.println("60229]n");
		for(int[] way:direction) {
			
			int tx=x+way[0],ty=y+way[1];
			
			while(in_board(tx, ty)&&board[tx][ty].getpiece()==nextPlayer) {
				
				can_verse[tx][ty]=true;
				
				System.out.println("12131");
				tx+=way[0];
				ty+=way[1];
			}
			if(in_board(tx, ty)&&board[tx][ty].getpiece()==currentPlayer) {
				isvalid=true;
			}
			
			
		}
		return isvalid;
	}
	
	private void initbackground() {
		
        getChildren().addAll(background);
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
        
        //System.out.println(cell_height+" "+cell_width);
        
	}
	private void initboard() {
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				board[i][j]=new Piece(0);
				getChildren().add(board[i][j]);
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
        
        
        
        //cnt+=1;
        //System.out.println(cnt);
	}
	 private void pieceResizeRelocate() {
	        for(int i = 0; i < 8; i++)
	            for(int j = 0; j < 8; j++) {
	            	//System.out.println(cell_height+" "+cell_width);
	                board[i][j].resize(cell_width, cell_height);
	                board[i][j].relocate(i * cell_width, j * cell_height);
	            }
	        
	    }
	public void horizontalResizeRelocate(double width) {
        for(int i = 0; i < 8; i++) {
            horizontal_t[i].setY((i + 1) * cell_height);
            horizontal[i].setEndX(width);
        }
        //System.out.println("**");
        //System.out.println(cnt);
    }
	public void verticalResizeRelocate(double height) {
        for(int i = 0; i < 8; i++) {
            vertical_t[i].setX((i + 1) * cell_width);
            vertical[i].setEndY(height);
        }
        //System.out.println("**");
        //System.out.println(cnt);
    }
}
