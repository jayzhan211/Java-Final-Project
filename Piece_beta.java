package game;

import javafx.scene.shape.Circle;

public class Piece_beta extends Circle{
	private SquareType player =SquareType.EMPTY;
    public Piece_beta(int size,SquareType player) {
    	super();
    	int center = size / 2;
        setType(player);
        setCenterX(center);
        setCenterY(center);
        setRadius(center-3);

    }
    public SquareType getType() { return player; }
    public void setType(SquareType squareType) {
    	this.player=squareType;
    	setFill(squareType.getColor());
    }
}
