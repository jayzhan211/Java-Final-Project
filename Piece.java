package game;

import javafx.scene.shape.Circle;

public class Piece extends Circle{
	private SquareType player =SquareType.EMPTY;
    public Piece(int size,SquareType player) {
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
    	squareType.getColor();
    	setFill(squareType.getColor());

    }
}
