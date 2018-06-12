package game;


import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.StackPane;

import javafx.scene.text.Font;

import javafx.stage.Stage;

public class Othello extends Application{

	private StackPane start_pane;
	private StackPane choose_pane;
	private Button startButton;
	private Button return_button;
	private Button PVP;
	private Button PVC_Easy;
	private Button PVC_Normal;
	private Button PVC_Hard;
	private Button PVC_Nightmare;

	private Scene start_scene;
	private Scene choose_scene;

	private int window_height=800;
	private int window_width=800;
    private Game_Scene game_board;
	public static  Scene game_scene;

	//private MenuBar menuBar;

	public void init() throws Exception {
		//First Scene
		start_pane = new StackPane();
		ImageView image = new ImageView(new Image(getClass().getResourceAsStream("/game/Start_Image.jpg")));
		start_pane.getChildren().add(image);

		startButton = new Button("Othello");
		start_pane.getChildren().add(startButton);
		startButton.setStyle(
				"-fx-background-radius: 500em; " +
                "-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 200px; " +
                "-fx-max-height: 80px;"
        );
		startButton.setFont(new Font("Arial Bold",25));
		start_scene=new Scene(start_pane,window_width, window_height);

		//Second Scene
		choose_pane=new StackPane();
		ImageView image1 = new ImageView(new Image("/game/othello.jpg",window_width,window_height, false, false, false));
		choose_pane.getChildren().add(image1);


		return_button=new Button("Return");
		PVP=new Button("Player vs Player");
		PVC_Easy=new Button("Easy");
		PVC_Normal=new Button("Normal");
		PVC_Hard=new Button("Hard");
		PVC_Nightmare=new Button("Nightmare");

		return_button.setTranslateX(-320);
		return_button.setTranslateY(-360);
		return_button.setScaleX(1.3);

		PVP.setTranslateX(0);
		PVP.setTranslateY(-100);

		PVC_Easy.setTranslateX(0);
		PVC_Easy.setTranslateY(-50);


		PVC_Normal.setTranslateX(0);
		PVC_Normal.setTranslateY(0);

		PVC_Hard.setTranslateX(0);
		PVC_Hard.setTranslateY(50);

		PVC_Nightmare.setTranslateX(0);
		PVC_Nightmare.setTranslateY(100);

		choose_pane.getChildren().addAll(return_button,PVP,PVC_Easy,PVC_Normal,PVC_Hard,PVC_Nightmare);
		choose_scene=new Scene(choose_pane,window_width, window_height);

		game_board=new Game_Scene();
		game_scene=new Scene(game_board,window_width, window_height);

		//game_board.getChildren().add(menuBar);
	}


	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Othello");
        primaryStage.setScene(start_scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        startButton.setOnAction(e->{
        	primaryStage.setScene(choose_scene);

		});
        return_button.setOnAction(e->{
        	primaryStage.setScene(start_scene);

		});
        PVP.setOnAction(e->{
        	new UIGame(false, "PVP",game_board);
        	//setMenuItemEvent();
        	primaryStage.setScene(game_scene);
		});
        PVC_Easy.setOnAction(e->{
        	new UIGame(true, "Easy",game_board);
        	primaryStage.setScene(game_scene);

		});
        PVC_Normal.setOnAction(e->{
        	new UIGame(true, "Normal",game_board);
        	primaryStage.setScene(game_scene);

		});
        PVC_Hard.setOnAction(e->{
        	new UIGame(true, "Hard",game_board);
        	primaryStage.setScene(game_scene);

		});

        PVC_Nightmare.setOnAction(e->{
        	new UIGame(true, "Nightmare",game_board);
        	primaryStage.setScene(game_scene);

		});
    }

    public static void main(String[] args) {
        launch(args);
    }
}
