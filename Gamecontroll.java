package game;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Gamecontroll extends Control{
	private Board board;
	
		
	public Gamecontroll(){
		
		setSkin(new Boardskin(this));
		
		
		board=new Board();
		getChildren().add(board);
		
		
		
		//System.out.println("66ssa6");
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	
                board.placePiece(event.getX(), event.getY());
                
            }
            
        });
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	
            	
            	
                if(event.getCode() == KeyCode.R) {
                	
                	
                    board.previous_move();
                }
                else if(event.getCode() == KeyCode.SPACE) {
                	
                	
                	
                    board.resetGame();
                }
                
                
                
            }
        });
        
        
       

		
	}
	public void resize(double width, double height) {
		//System.out.println("--999");
        super.resize(width, height);
        
       // System.out.println("01119");
        
        board.resize(width, height);
        
        
        
        //System.out.println("15999");
    }
	/*public boolean isResizable() {
		System.out.println("123");
		return true;
	}*/

}