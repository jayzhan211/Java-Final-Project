package game;



import javafx.geometry.Pos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.util.Duration;

public class BoardUI extends GridPane{
	private String background_color = "#654321";
	private int Board_Size=60;
	public Pane[][] squares;
	protected Piece_beta[][] board;
	protected Duration flipDuration;
    protected int flip_duration=500;

	public BoardUI() {
		super();
		board=new Piece_beta[8][8];
		squares=new Pane[8][8];
		this.flipDuration= Duration.millis(flip_duration);


		for(int i=0;i<8;i++) {
			getRowConstraints().add( new RowConstraints(Board_Size));
			getColumnConstraints().add( new ColumnConstraints(Board_Size));
		}

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				squares[i][j]=new Pane();
				board[i][j]=new Piece_beta(Board_Size, SquareType.EMPTY);
				squares[i][j].setStyle("-fx-background-color: " + background_color + ";");
				squares[i][j].getChildren().add(board[i][j]);
				add(squares[i][j],i,j);
			}
		board[3][3].setType(SquareType.WHITE);
		board[4][4].setType(SquareType.WHITE);
		board[3][4].setType(SquareType.BLACK);
		board[4][3].setType(SquareType.BLACK);
		setGridLinesVisible(true);
		setAlignment(Pos.CENTER);


	}

}
