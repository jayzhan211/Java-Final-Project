package game;


import java.util.Set;



import javafx.geometry.Point2D;


public class UIGame{
	private Game_Scene boardUI;
	private Controller controller=Controller.getInstance();
	private Set<Point2D> possblMoves;
	private boolean game_end;

	public UIGame(boolean Vs_AI, String difficulty, Game_Scene board) {
		this.boardUI=board;
		this.game_end=false;
		this.boardUI.vsRobots=Vs_AI;
		switch (difficulty) {
			case "EASY":
				controller.setDifficulty(DifficultyLevel.EASY);
			case "NORMAL":
				controller.setDifficulty(DifficultyLevel.NORMAL);
			case "HARD":
				controller.setDifficulty(DifficultyLevel.HARD);
			case "Nightmare":
				controller.setDifficulty(DifficultyLevel.Nightmare);
			break;
		}

		this.controller.init();

		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++) {
				final int ii=i,jj=j;
				boardUI.board_state.squares[i][j].setOnMouseClicked(e->{
					if(game_end)return ;

					if (!findPossibleMoves()) {
						pass();
						computer_turn();


					}
					else {
						possblMoves = markPossibleMoves();
						Point2D selectedMove=new Point2D(ii, jj);
						if (possblMoves.contains(selectedMove)) {
							boardUI.unmarkPossibleMoves(possblMoves);
							makeMove(selectedMove);
							updateStats();
							changeTurn();
							computer_turn();
						}
					}

					if (controller.endOfGame())
						gameEnd();

				});
			}

	}


	private void computer_turn() {
		if (boardUI.againstRobots()&&controller.currentPlayer() != boardUI.getPlayerSelection()) {
			Point2D computerMove = controller.evalMove();
			makeMove(computerMove);
			updateStats();
			changeTurn();
		}

	}


	private void makeMove(Point2D move) {
		SquareType color = controller.currentPlayer().color()== SquareState.WHITE? SquareType.WHITE : SquareType.BLACK;
		Set<Point2D> squaresToChange = controller.makeMove(move);
		boardUI.fill(squaresToChange, color);
	}
	private boolean findPossibleMoves() {
		Set<Point2D> moves = controller.markPossibleMoves();
		controller.unmarkPossibleMoves();
		return !moves.isEmpty();
	}
	private Set<Point2D> markPossibleMoves() {
		Set<Point2D> moves = controller.markPossibleMoves();
		controller.unmarkPossibleMoves();
		if (!moves.isEmpty()) {
			SquareType color = controller.currentPlayer().color() == SquareState.WHITE? SquareType.PSSBLWHT : SquareType.PSSBLBLK;
			boardUI.markPossibleMoves(moves, color);
		}
		return moves;
	}


	private void gameEnd() {
		game_end=true;
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
