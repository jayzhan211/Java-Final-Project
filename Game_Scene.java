package game;

import java.util.Collection;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class Game_Scene extends StackPane{
	public BoardUI board_state;
	public Label whitescore;
	public Label blackscore;
	public Label showturn;
	public static Player human = Player.BLACK;
	public boolean vsRobots = true;
	public Duration flipDuration;
    public int flip_duration=500;
    public MenuBar menuBar;
    public Menu menu;
	public Game_Scene(String game) {
		board_state=new BoardUI();
		Image image=new Image("/game/BGP.jpg",Othello.window_width,Othello.window_height,false, false, false);
		ImageView bgp = new ImageView(image);
		this.getChildren().addAll(bgp);

		whitescore=new Label("White");
		blackscore=new Label("Black");
		whitescore.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		whitescore.setTextFill(Color.WHITE);
		blackscore.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
		blackscore.setTextFill(Color.BLACK);

		showturn=new Label(game);
		showturn.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		showturn.setTextFill(Color.GOLD);



		whitescore.setTranslateX(250);
		whitescore.setTranslateY(300);
		blackscore.setTranslateX(-250);
		blackscore.setTranslateY(300);
		showturn.setTranslateX(0);
		showturn.setTranslateY(300);

		this.getChildren().addAll(board_state,whitescore,blackscore,showturn);
		this.flipDuration=Duration.millis(flip_duration);

	}
	public void updateScore(int blackscore, int whitescore) {
		this.blackscore.setText("Black: " + blackscore);
		this.whitescore.setText("White: " + whitescore);
	}
	public void declareDraw() {
		showturn.setText("Draw !?");
	}
	public void declareWinner(String winnerName) {
		showturn.setText(winnerName + " WINS!!!");
		if(winnerName==Player.BLACK.toString())
			this.showturn.setTextFill(Color.BLACK);
		else this.showturn.setTextFill(Color.WHITE);
		blackscore.setVisible(false);
		whitescore.setVisible(false);
	}
	public void markPossibleMoves(Collection<Point2D> possibleMoves, SquareType color) {
		for (Point2D possiblePoint : possibleMoves)
			setSquare(possiblePoint, color);
	}
	private void setSquare(Point2D point, SquareType squareType) {
		int x=(int)point.getX();
		int y=(int)point.getY();

		if(squareType==SquareType.BLACK||squareType==SquareType.WHITE) {
			RotateTransition firstRotator = new RotateTransition(flipDuration,board_state.board[x][y]);
			firstRotator.setAxis(Rotate.Y_AXIS);
			firstRotator.setFromAngle(0);
			firstRotator.setToAngle(90);
			firstRotator.setInterpolator(Interpolator.LINEAR);
			firstRotator.setOnFinished(e->{
				board_state.board[x][y].setType(squareType);
	    	});
		    RotateTransition secondRotator = new RotateTransition(flipDuration,board_state.board[x][y]);
		    secondRotator.setAxis(Rotate.Y_AXIS);
		    secondRotator.setFromAngle(90);
		    secondRotator.setToAngle(180);
		    secondRotator.setInterpolator(Interpolator.LINEAR);
		    new SequentialTransition(firstRotator, secondRotator).play();
	    }
		else board_state.board[x][y].setType(squareType);

	}
	public void unmarkPossibleMoves(Collection<Point2D> possibleMoves) {

		for (Point2D possiblePoint : possibleMoves)
			setSquare(possiblePoint,SquareType.EMPTY );
	}
	public void notifyLostTurn(Player player) {
		this.showturn.setText(player+": PASS!!!");
	}
	public void updateTurn(String player) {
		this.showturn.setText(player);
		if(player==Player.BLACK.toString())
			this.showturn.setTextFill(Color.BLACK);
		else this.showturn.setTextFill(Color.WHITE);
	}
	public Player getPlayerSelection() {
		return human;
	}
	public boolean againstRobots() {
		return vsRobots;
	}
	public void fill(Collection<Point2D> filledpoints, SquareType color) {
		for (Point2D toFill : filledpoints)
			setSquare(toFill, color);
	}

}
