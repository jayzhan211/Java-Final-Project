package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

//Piece
public class Piece extends Group{
	private int Player;		// the player that this piece belongs to
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
		Player=player;
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
	
	@Override
    public void resize(double width, double height) {
        super.resize(width, height);

        piece.setCenterX(width / 2);
        piece.setCenterY(height / 2);

        piece.setRadiusX(width / 2);
        piece.setRadiusY(height / 2);
    }

    // overridden version of the relocate method to position the piece correctly
    @Override
    public void relocate(double x, double y) {
        super.relocate(x, y);
        t.setX(x);
        t.setY(y);
    }
	
	public int getpiece() {
		return Player;
	}
	
}