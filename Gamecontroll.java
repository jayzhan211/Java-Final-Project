

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Gamecontroll extends Control{
	public Gamecontroll() {
		setSkin(new Boardskin(this));
		setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {

            	

                if(event.getCode() == KeyCode.R) {


                	System.out.println("aaaaaaaaaaaaa");
                }
                else if(event.getCode() == KeyCode.SPACE) {



                    //board.resetGame();
                }



            }
        });
	}
	/*private Board_Beta board;

	private int Square_size=100;

	public Gamecontroll(int booad_pane,int board_size){
		Square_size=board_size/8;
		setSkin(new Boardskin(this));

		if(booad_pane==1)
			board=new Board_Beta(Square_size);
		else board=new Board_PvC(Square_size);
		//board=new Board_Beta(Square_size);

		getChildren().add(board);



		//System.out.println("66ssa6");

		setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                board.placePiece(event.getX(), event.getY());

            }

        });

		setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {



                if(event.getCode() == KeyCode.R) {


                    board.previous_move();
                }
                else if(event.getCode() == KeyCode.SPACE) {



                    board.resetGame();
                }



            }
        });





	}
	public void resize(double width, double height) {
		//System.out.println("--999");
        super.resize(width, height);

       // System.out.println("01119");

        board.resize(width, height);



        //System.out.println("15999");
    }
	/*public boolean isResizable() {
		System.out.println("123");
		return true;
	}
	*/
}