package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Gamecontroll extends Control{
	private final Board board;
	
		
	public Gamecontroll(){
		setSkin(new Boardskin(this));
		board=new Board();
		getChildren().add(board);
		
		setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	
                board.placePiece(event.getX(), event.getY());
            }
        });

        // TODO: add press handler which calls reset when space is clicked - pt 4
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.SPACE) {
                	
                    board.resetGame();
                }
            }
        });

		
	}
	public void resize(double width, double height) {
        super.resize(width, height);
        board.resize(width, height);
    }
	
}
