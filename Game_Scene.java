package game;

import java.util.Collection;

import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class Game_Scene extends StackPane{
	public BoardUI board_state;
	public Label whitescore;
	public Label blackscore;
	public Label showturn;
	public static Player human = Player.BLACK;
	public boolean vsRobots = true;
	public Game_Scene() {
		board_state=new BoardUI();
		whitescore=new Label("White");
		blackscore=new Label("Black");
		showturn=new Label("Othello_Beta");
		whitescore.setTranslateX(250);
		whitescore.setTranslateY(300);
		blackscore.setTranslateX(-250);
		blackscore.setTranslateY(300);
		this.getChildren().addAll(board_state,whitescore,blackscore);



	}
	public void updateScore(int blackscore, int whitescore) {
		this.blackscore.setText("Black: " + blackscore);
		this.whitescore.setText("Black: " + whitescore);
	}
	public void declareDraw() {
		//this.showTurn.setFont(showTurn.getFont().deriveFont(Font.BOLD));
		this.showturn.setText("Draw !?");
	}
	public void declareWinner(String winnerName) {
		//this.showTurn.setFont(showTurn.getFont().deriveFont(Font.BOLD));
		this.showturn.setText(winnerName + "!");
	}
	public void markPossibleMoves(Collection<Point2D> possibleMoves, SquareType color) {
		System.out.println("bbb1");
		for (Point2D possiblePoint : possibleMoves) {
			//System.out.println(possiblePoint.getX()+" "+possiblePoint.getY());
			setSquare(possiblePoint, color);
		}
		System.out.println("bbb2");
	}
	private void setSquare(Point2D point, SquareType squareType) {
		int x=(int)point.getX();
		int y=(int)point.getY();
		board_state.board[x][y].setType(squareType);

	}
	public void unmarkPossibleMoves(Collection<Point2D> possibleMoves) {

		for (Point2D possiblePoint : possibleMoves)
			setSquare(possiblePoint,SquareType.EMPTY );
	}
	public void notifyLostTurn(Player player) {
		this.showturn.setText(player+": PASS!!!");
	}
	public void updateTurn(String player) {
		this.showturn.setText(player+"'s turn");
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
	public Piece_beta[][] getSquares(){
		return board_state.board;
	}
}
