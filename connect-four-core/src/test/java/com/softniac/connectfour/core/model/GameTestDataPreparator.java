package com.softniac.connectfour.core.model;

public class GameTestDataPreparator {

    public static Game prepareGameWithOneMoreMoveNeededToWin() {
	String winningPlayerName = "player1";
	String player2 = "player2";
	Game game = new Game(new UserAccount(), winningPlayerName, player2);
	Board board = game.getGameState().getBoard();

	String token = winningPlayerName;
	board.putTokenAt(token, 5, 0);
	board.putTokenAt(token, 4, 0);
	board.putTokenAt(token, 3, 0);

	return game;
    }
 
    public static Game prepareGameWithOneMoreMoveNeededToHaveADraw() {
	String tokenBase = "token";
	Game game = new Game();
	Board board = game.getGameState().getBoard();

	for (int rowIndex = board.getRowDimension() - 1; rowIndex >= 0; rowIndex--) {
	    for (int columnIndex = 0; columnIndex < board.getColumnDimension(); columnIndex++) {
		if (boardHasMoreThanOneFreeSpace(board, rowIndex, columnIndex)) {
		    board.putTokenAt(tokenBase + rowIndex + columnIndex, rowIndex, columnIndex);
		}
	    }
	}

	return game;
    }

    private static boolean boardHasMoreThanOneFreeSpace(Board board, int rowIndex, int columnIndex) {
	return !(rowIndex == 0 && columnIndex == board.getColumnDimension() - 1);
    }

    public static Game prepareGameWithPlayer1Turn() {
	return new Game(new UserAccount(), "player1", "player2");
    }

}
