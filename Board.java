package game;

import org.omg.CORBA.PRIVATE_MEMBER;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

//Black 2- White 1
public class Board extends Pane{
	public Board(){
		//black-first
		currentPlayer=2;
		nextPlayer=1;
		board=new Piece[8][8];
		background=new Rectangle();
		horizontal = new Line[8];
        horizontal_t = new Translate[8];
        vertical = new Line[8];
        vertical_t = new Translate[8];
		
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
		
	}
	public void reset_board() {
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board[i][j].setpiece(0);
		
	}
	public void placePiece(double x,double y) {
		
		
	}
	public void resize(double width,double height) {
		super.resize(width,height);
		cell_width=width/8.0;
		cell_height=height/8.0;
		background.setWidth(width);
        background.setHeight(height);
        horizontalResizeRelocate(width);
        verticalResizeRelocate(height);
		
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
}
