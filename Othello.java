package game;


import java.awt.image.TileObserver;
import java.io.IOException;
import java.util.ArrayList;



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

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
	private int board_size=800;

	private Board_PvC pvc_board;
	private Board_Beta pvp_board;
	private Board_pvc_beta pvc_beta_board;

	private int Square_size=60;

	public void init() throws IOException {
		//main pane

		start_pane = new StackPane();
		pvp_pane=new StackPane();
		pvc_pane=new StackPane();
		pvc_beta_pane=new StackPane();
		choose_pane=new StackPane();

		pvp_board=new Board_Beta(Square_size);
		pvc_board=new Board_PvC(Square_size);
		pvc_beta_board=new Board_pvc_beta(Square_size);

		pvp_pane.getChildren().add(pvp_board);
		pvc_pane.getChildren().add(pvc_board);
		pvc_beta_pane.getChildren().add(pvc_beta_board);

		//pvp_pane.getChildren().add(new Gamecontroll());
		//pvc_pane.getChildren().add(new Gamecontroll(2,board_size));

		startButton = new Button("Start");
		return_button=new Button("Return");
		go_to_pvp=new Button("Player vs Player");
		go_to_pvc=new Button("Player vs Computer");
		go_to_pvc_beta=new Button("Beta Zone");

		game_title=new Label("Othello");

		game_title.setMaxHeight(500);
		game_title.setFont(new Font("Fleisch",100));
		game_title.setAlignment(Pos.TOP_CENTER);
		game_title.setTextFill(Color.BLACK);

		//stackpane ,note that  button should be on top
		start_pane.getChildren().add(game_title);
		start_pane.getChildren().add(startButton);



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
		//choose_pane.getChildren().addAll(return_button,go_to_pvc,go_to_pvp);


		pvc_beta_scene=new Scene(pvc_beta_pane,window_width,window_height);
		pvc_scene=new Scene(pvc_pane,window_width, window_height);
		pvp_scene=new Scene(pvp_pane,window_width, window_height);
        start_scene=new Scene(start_pane,window_width, window_height);
		choose_scene=new Scene(choose_pane,window_width, window_height);


		pvp_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R) {

                	pvp_board.previous_move();

                }
                else if(event.getCode() == KeyCode.SPACE) {
                	pvp_board.resetGame();
                }
            }
        });
		pvc_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R) {

                	pvc_board.previous_move();
                	pvc_board.previous_move();

                }
                else if(event.getCode() == KeyCode.SPACE) {
                	//System.out.println("123");
                	pvc_board.resetGame();
                }
            }
        });
		pvc_beta_scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.R) {

                	pvc_beta_board.previous_move();
                	pvc_beta_board.previous_move();

                }
                else if(event.getCode() == KeyCode.SPACE) {
                	//System.out.println("123");
                	pvc_beta_board.resetGame();
                }
            }
        });
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
        go_to_pvc_beta.setOnAction(e->{
        	primaryStage.setScene(pvc_beta_scene);
        	

		});

        primaryStage.setResizable(true);

        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
