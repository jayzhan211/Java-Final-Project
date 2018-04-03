package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Othello extends Application{
	
	private StackPane stackPane;
	private Gamecontroll gamecontroll;
	
	
	public void init() {
		stackPane = new StackPane();
		gamecontroll=new Gamecontroll();
		stackPane.getChildren().add(gamecontroll);
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("Othello");
        primaryStage.setScene(new Scene(stackPane, 800, 800));
        primaryStage.setResizable(true);
        primaryStage.show();
        
    }
	
    public static void main(String[] args) {
        launch(args);
    }
}
