package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Othello extends Application{
	private StackPane root;
	private Button startbutton;
	private Board newgame;
	
	public void init() {
		
		
		root=new StackPane();
		root.getChildren().add(new Gamecontroll());
		startbutton=new Button();
		startbutton.setText("START");

		startbutton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				newgame.resetGame();
			}
			
			
		});
		root.getChildren().add(startbutton);
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.setResizable(true);
        primaryStage.show();
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}
