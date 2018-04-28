package game;


import java.util.Set;

import javafx.geometry.Point2D;


public class UIGame{
	private Game_Scene boardUI;
	private Controller controller=Controller.getInstance();
	private Set<Point2D> possblMoves;

	public UIGame(boolean Vs_AI, int difficulty, Game_Scene board) {
		this.boardUI=board;
		//System.out.println(Vs_AI+" "+difficulty);
		this.boardUI.vsRobots=Vs_AI;

		//this.controller.init();
		//run();
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				boardUI.board_state.squares[i][j].setOnMouseClicked(e->{
					if (controller.endOfGame())
						gameEnd();
					else {
						possblMoves = markPossibleMoves();
						if (possblMoves.isEmpty())
							pass();
						else {
							if (controller.currentPlayer() != boardUI.getPlayerSelection()
							    && boardUI.againstRobots()) {
								boardUI.unmarkPossibleMoves(possblMoves);
								Point2D computerMove = controller.evalMove();
								makeMove(computerMove);
								updateStats();
								changeTurn();
							}
						}

					}

				});
			}

	}
	private void makeMove(Point2D move) {
		SquareType color = controller.currentPlayer().color()== SquareState.WHITE
				   ? SquareType.WHITE : SquareType.BLACK;
		Set<Point2D> squaresToChange = controller.makeMove(move);
		boardUI.fill(squaresToChange, color);
	}
	private Set<Point2D> markPossibleMoves() {
		Set<Point2D> moves = controller.markPossibleMoves();
		controller.unmarkPossibleMoves();
		if (!moves.isEmpty()) {
			SquareType color = controller.currentPlayer().color() == SquareState.WHITE
					   ? SquareType.PSSBLWHT : SquareType.PSSBLBLK;
			boardUI.markPossibleMoves(moves, color);
		}
		return moves;
	}


	private void gameEnd() {
		updateStats();
		if (controller.isDraw()) {
			boardUI.declareDraw();
		} else {
			boardUI.declareWinner(controller.getWinner().toString());
		}
	}
	private void updateStats() {
		boardUI.updateScore(controller.getBlackScore(), controller.getWhiteScore());
	}
	private void pass() {
		lostTurn();
		updateStats();
	}
	private void lostTurn() {
		boardUI.notifyLostTurn(controller.currentPlayer());
		changeTurn();
	}
	private void changeTurn() {
		controller.changeTurn();
		boardUI.updateTurn(controller.currentPlayer().toString());
	}
}
