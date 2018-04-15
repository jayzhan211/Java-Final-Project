package game;

import java.awt.image.TileObserver;
import java.util.ArrayList;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Othello extends Application{
	
	private StackPane start_pane;
	private StackPane pvp_pane;
	private StackPane choose_pane;
	private StackPane pvc_pane;
	
	private Gamecontroll gamecontroll;
	private Button startButton;
	private Label game_title;
	private Button return_button;
	//player vs player
	private Button go_to_pvp;
	//player vs computer
	private Button go_to_pvc;
	
	private Scene start_scene;
	private Scene choose_scene;
	private Scene pvp_scene;
	private Scene pvc_scene;
	
	private int window_height=800;
	private int window_width=800;
	
	public void init() {
		//main pane
		start_pane = new StackPane();
		pvp_pane=new StackPane();
		pvc_pane=new StackPane();
		choose_pane=new StackPane();
		gamecontroll=new Gamecontroll();
		
		pvp_pane.getChildren().add(gamecontroll);
		
		
		
		//pvp_pane.getChildren().add(new Board_Controll());
		
		
		
		pvc_pane.getStyleClass().add("pvc_skin");

		
		startButton = new Button("Start");
		return_button=new Button("Return");
		go_to_pvp=new Button("Player vs Player");
		go_to_pvc=new Button("Player vs Computer");
		
		game_title=new Label("Othello");
		
		game_title.setMaxHeight(500);
		game_title.setFont(new Font("Fleisch",100));
		game_title.setAlignment(Pos.TOP_CENTER);
		game_title.setTextFill(Color.BLACK);
		
		//stackpane ,note that  button should be on top
		start_pane.getChildren().add(game_title);
		start_pane.getChildren().add(startButton);
		
		

		return_button.setTranslateX(-window_height/2.5);
		return_button.setTranslateY(-360);
		return_button.setScaleX(1.3);
		//return_button.setAlignment(Pos.TOP_LEFT);

		
		go_to_pvp.setTranslateX(0);
		go_to_pvp.setTranslateY(-50);
		
		
		
		startButton.setFont(new Font("Fleisch",14));
		startButton.setMaxSize(60, 40);
		
		
		
		choose_pane.getChildren().addAll(return_button,go_to_pvc,go_to_pvp);
		
		pvc_scene=new Scene(pvc_pane,window_width, window_height);
		pvp_scene=new Scene(pvp_pane,window_width, window_height);
        start_scene=new Scene(start_pane,window_width, window_height);
		choose_scene=new Scene(choose_pane,window_width, window_height);
		
		//pvc_paneskin.css
		
		pvc_scene.getStylesheets().add(getClass().getResource("skin.css").toExternalForm());
		
	}

	@Override
    public void start(Stage primaryStage) throws Exception {
		
        primaryStage.setTitle("Othello");
        primaryStage.setScene(start_scene);
        
        startButton.setOnAction(e->{
        	primaryStage.setScene(choose_scene);
			
		});
        return_button.setOnAction(e->{
        	primaryStage.setScene(start_scene);
			
		});
        go_to_pvp.setOnAction(e->{
        	primaryStage.setScene(pvp_scene);
			
		});
        go_to_pvc.setOnAction(e->{
        	primaryStage.setScene(pvc_scene);
			
		});
        
        
        
        primaryStage.setResizable(true);

        primaryStage.show();
		
		
		
		
		/*try {
			Parent root=FXMLLoader.load(getClass().getResource("A.fxml"));
	    
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		}
		catch (Exception e) {
			 e.printStackTrace();
		}*/
		
        
    }
    public static void main(String[] args) {
        launch(args);
    }
}