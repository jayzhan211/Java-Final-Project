package game;

import javafx.scene.Group;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

public class Piece extends Group
{
  private int Player;
  private Ellipse piece;
  private Translate t;
  
  public Piece(int player)
  {
    t = new Translate();
    piece = new Ellipse();
    piece.getTransforms().add(t);
    setpiece(player);
    getChildren().add(piece);
  }
  

  public void setpiece(int player)
  {
    Player = player;
    if (player == 0) {
      piece.setFill(javafx.scene.paint.Color.TRANSPARENT);
    }
    else if (player == 1) {
      piece.setFill(javafx.scene.paint.Color.WHITE);
    }
    else {
      piece.setFill(javafx.scene.paint.Color.BLACK);
    }
  }
  
  public void resize(double width, double height)
  {
    super.resize(width, height);
    
    piece.setCenterX(width / 2.0D);
    piece.setCenterY(height / 2.0D);
    
    piece.setRadiusX(width / 2.0D);
    piece.setRadiusY(height / 2.0D);
  }
  

  public void relocate(double x, double y)
  {
   // super.relocate(x, y);
    t.setX(x);
    t.setY(y);
  }
  
  public int getpiece() {
    return Player;
  }
}