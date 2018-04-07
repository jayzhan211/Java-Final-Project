package game;

import java.awt.image.TileObserver;
import java.util.ArrayList;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Othello extends Application{
	
	private StackPane start_pane;
	private StackPane main_pane;
	private Gamecontroll gamecontroll;
	private Button startButton;
	private Label game_title;

	private Scene start_scene;
	private Scene main_scene;
	
	public void init() {
		//main pane
		start_pane = new StackPane();
		main_pane=new StackPane();
		
		startButton = new Button("Start");
		
		game_title=new Label("Othello");
		
		
		gamecontroll=new Gamecontroll();
		
		//stackpane ,note that  button should be on top
		start_pane.getChildren().add(game_title);
		start_pane.getChildren().add(startButton);
		
		
		game_title.setMaxHeight(500);
		game_title.setFont(new Font("Fleisch",100));
		game_title.setAlignment(Pos.TOP_CENTER);
		game_title.setTextFill(Color.BLACK);
		
		startButton.setFont(new Font("Fleisch",14));
		
		startButton.setMaxSize(60, 40);
		
		main_pane.getChildren().add(gamecontroll);
		
		main_scene=new Scene(main_pane, 800, 800);
        start_scene=new Scene(start_pane,800,800);
		
		
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("Othello");
        primaryStage.setScene(start_scene);
        startButton.setOnAction(e->{
        	primaryStage.setScene(main_scene);
			
		});
        
        
        primaryStage.setResizable(true);

        primaryStage.show();
        
    }
	public void stop() {
		
	}
    public static void main(String[] args) {
        launch(args);
    }
}
