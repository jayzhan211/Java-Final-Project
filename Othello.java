package game;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.scene.layout.StackPane;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.EventHandler;
import javafx.geometry.Pos;

public class Othello extends Application{

	private StackPane start_pane;
	private StackPane pvp_pane;
	private StackPane choose_pane;
	private StackPane pvc_pane;
	private StackPane pvc_beta_pane;

	private Button startButton;
	private Button return_button;
	//player vs player
	private Button go_to_pvp;
	//player vs computer
	private Button go_to_pvc;
	private Button go_to_pvc_beta;

	private Label game_title;

	private Scene start_scene;
	private Scene choose_scene;
	private Scene pvp_scene;
	private Scene pvc_scene;
	private Scene pvc_beta_scene;

	private int window_height=800;
	private int window_width=800;


	private int Square_size=60;
	private BoardUI board;
	private StackPane board_pane;

    private Label whitescore;
    private Label blackscore;
    private Game_Scene game_board;
	private Scene game_scene;

	public void init() throws Exception {
		//First Scene
		start_pane = new StackPane();
		startButton = new Button("Start");
		start_pane.getChildren().add(startButton);
		startButton.setFont(new Font("Fleisch",14));
		startButton.setMaxSize(60, 40);
		start_scene=new Scene(start_pane,window_width, window_height);
		//Second Scene
		choose_pane=new StackPane();
		return_button=new Button("Return");
		go_to_pvp=new Button("Player vs Player");
		go_to_pvc=new Button("Player vs Computer");
		go_to_pvc_beta=new Button("Beta Zone");

		return_button.setTranslateX(-320);
		return_button.setTranslateY(-360);
		return_button.setScaleX(1.3);

		go_to_pvp.setTranslateX(0);
		go_to_pvp.setTranslateY(-50);

		go_to_pvc_beta.setTranslateX(100);
		go_to_pvc_beta.setTranslateY(100);

		startButton.setFont(new Font("Fleisch",14));
		startButton.setMaxSize(60, 40);

		choose_pane.getChildren().addAll(return_button,go_to_pvc,go_to_pvp,go_to_pvc_beta);
		choose_scene=new Scene(choose_pane,window_width, window_height);

		//board_pane=new StackPane();
		//board_pane.getChildren().add(new Game_Scene());
		//game_board=new Game_Scene();

		//board_pane.getChildren().addAll(board,whitescore,blackscore);
		game_board=new Game_Scene();
		game_scene=new Scene(game_board,window_width, window_height);
	}


	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        primaryStage.setScene(start_scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        startButton.setOnAction(e->{
        	primaryStage.setScene(choose_scene);

		});
        return_button.setOnAction(e->{
        	primaryStage.setScene(start_scene);

		});
        go_to_pvp.setOnAction(e->{
        	new UIGame(false, -1,game_board);

        	primaryStage.setScene(game_scene);
		});
        go_to_pvc.setOnAction(e->{
        	new UIGame(true, 1,game_board);
        	primaryStage.setScene(game_scene);

		});
        go_to_pvc_beta.setOnAction(e->{
        	new UIGame(true, 3,game_board);
        	primaryStage.setScene(game_scene);

		});
    }

    public static void main(String[] args) {
        launch(args);
    }
}