package game;




import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Translate;

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
    
    
	public Board(){
		currentPlayer=2;
		nextPlayer=1;
		board=new Piece[8][8];
		background=new Rectangle();
		horizontal = new Line[8];
        horizontal_t = new Translate[8];	
        vertical = new Line[8];
        vertical_t = new Translate[8];
        
        
        
        initbackground();
        initboard();
        resetGame();
		
	}
	public void resetGame() {
		//System.out.println("123");
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
	private void reset_board() {
		
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
				board[i][j].setpiece(0);
		
	}
	public void placePiece(double x,double y) {
		
		
	}
	private void initbackground() {
		background.setFill(Color.SILVER);
        getChildren().addAll(background);
        for(int i = 0; i < 8; i++) {
            horizontal[i] = new Line();
            horizontal[i].setStroke(Color.WHITE);
            horizontal[i].setStartX(0);
            horizontal[i].setStartY(0);
            horizontal[i].setEndY(0);
            horizontal_t[i] = new Translate(0,0);
            horizontal[i].getTransforms().add(horizontal_t[i]);
            getChildren().add(horizontal[i]);
        }

        for(int i = 0; i < 8; i++) {
            vertical[i] = new Line();
            vertical[i].setStroke(Color.WHITE);
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
}
