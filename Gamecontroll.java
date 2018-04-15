package game;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Gamecontroll extends Control{
	private Board_Controll board;
	
	private Board_Beta B1;
		
	public Gamecontroll(){
		
		setSkin(new Boardskin(this));
		
		
		
		
		
		//board=new Board_Controll();
		//getChildren().add(board);
		
		
		B1=new Board_Beta(100);
		//B1=new Board_Beta();
		getChildren().add(B1);
		
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	B1.placePiece(event.getX(), event.getY());
                //board.placePiece(event.getX(), event.getY());
                
            }
            
        });
		
		setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            	
            	
            	
                if(event.getCode() == KeyCode.R) {
                	
                	B1.previous_move();
                   // board.previous_move();
                }
                else if(event.getCode() == KeyCode.SPACE) {
                	
                	
                	B1.resetGame();
                    //board.resetGame();
                }
                
                
                
            }
        });
        
        
       

		
	}
	/*public void resize(double width, double height) {
		//System.out.println("--999");
        super.resize(width, height);
        
       // System.out.println("01119");
        
        //board.resize(width, height);
        
        
        
        //System.out.println("15999");
    }
	/*public boolean isResizable() {
		System.out.println("123");
		return true;
	}*/

}