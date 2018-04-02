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
		
		board=new Board();
		
		getChildren().add(board);
		

		
	}
	
}

