package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Othello extends Application{
	private StackPane root;
	private Gamecontroll gc;
	private Button startbutton;
	
	public void init() {
		startbutton=new Button();
		startbutton.setText("Start");
		startbutton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Hello JavaFX!");
			}
			
			
		});
		root=new StackPane();
		gc=new Gamecontroll();
		root.getChildren().add(gc);
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
