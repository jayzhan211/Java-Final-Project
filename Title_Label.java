package game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Title_Label extends Label{
	final int padding = 30;
	//private Title_Label() {}
    public Title_Label(int fontSize, boolean bold) {
        setFont(new Font(30));
        setMaxWidth(Double.MAX_VALUE);
        setTranslateX(280);
        setTranslateY(-300);
        //setPadding(new Insets(30,0,-30,0));
        if (bold) setStyle("-fx-font-weight: bold");
    }
    public void updateTitle(Title_Label t,PieceType cur) {
    	t.setText( cur.toString() + " Player's Turn ");
    }
}
