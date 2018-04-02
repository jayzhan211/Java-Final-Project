package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

//Piece
public class Piece extends Group{
	private int player;		// the player that this piece belongs to
    private Ellipse piece;	// ellipse representing the player's piece
    private Translate t;
	
	public Piece (int player) {
		t=new Translate();
		piece=new Ellipse();
		piece.getTransforms().add(t);
		setpiece(player);
		getChildren().add(piece);
	}
	
		
	
	public void setpiece(int player) {
		if(player==0) {
			piece.setFill(Color.TRANSPARENT);
		}
		else if(player==1) {
			piece.setFill(Color.WHITE);
		}
		else {
			piece.setFill(Color.BLACK);
		}
	}
}
