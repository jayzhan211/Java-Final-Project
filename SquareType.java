package game;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public enum SquareType {
	BLACK,
	WHITE,
	EMPTY,
	PSSBLBLK,
	PSSBLWHT;

	public Color getColor() {
        switch (this) {
            case WHITE:return Color.WHITE;
            case BLACK:return Color.BLACK;
            case EMPTY:return Color.TRANSPARENT;
            case PSSBLWHT:return new Color(255,255,255,0.3);
            case PSSBLBLK:return new Color(0,0,0,0.3);
            default: return null;
        }
    }
}