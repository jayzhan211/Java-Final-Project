package game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Piece_beta extends Circle{
	private PieceType player = PieceType.NONE;
    public Piece_beta(int size,PieceType player) {
    	super();
    	int center = size / 2;
        setType(player);
        setCenterX(center);
        setCenterY(center);
        setRadius(center-3);
    }
    public PieceType getType() { return player; }
    public void setType(PieceType type) {
        this.player = type;
        setFill(type.getColor());
    }
	
    
}
enum PieceType {
    NONE,
    WHITE,
    BLACK;
    public Color getColor() {
        switch (this) {
            case WHITE: return Color.WHITE;
            case BLACK: return Color.BLACK;
            default: return null;
        }
    }
    public String toString() {
        switch (this) {
            case WHITE: return "White";
            case BLACK: return "Black";
            default: return "None";
        }
    }
};