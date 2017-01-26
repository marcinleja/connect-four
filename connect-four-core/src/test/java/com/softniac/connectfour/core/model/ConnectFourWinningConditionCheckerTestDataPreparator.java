package com.softniac.connectfour.core.model;

public class ConnectFourWinningConditionCheckerTestDataPreparator {

    public static Board prepareBoardWithFourEqualTokensInDiagonalBottomLeftToTopRight(int startAtRowIndex,
	    int startAtColumnIndex) {
	String token = "token";
	Board board = new Board();

	int desiredNumberOfTokensInLine = 4;

	int finishRowIndex = startAtRowIndex - desiredNumberOfTokensInLine;
	int finishColumnIndex = desiredNumberOfTokensInLine + startAtColumnIndex;

	for (int rowIndex = startAtRowIndex, columnIndex = startAtColumnIndex; rowIndex > finishRowIndex
		&& columnIndex < finishColumnIndex; rowIndex--, columnIndex++) {
	    board.putTokenAt(token, rowIndex, columnIndex);
	}

	return board;
    }

    public static Board prepareBoardWithFourEqualTokensInDiagonalBottomRightToTopLeft(int startAtRowIndex,
	    int startAtColumnIndex) {
	String token = "token";
	Board board = new Board();

	int desiredNumberOfTokensInLine = 4;

	int finishBeforeRowIndex = startAtRowIndex - desiredNumberOfTokensInLine;
	int finishBeforeColumnIndex = startAtColumnIndex - desiredNumberOfTokensInLine;

	for (int rowIndex = startAtRowIndex, columnIndex = startAtColumnIndex; rowIndex > finishBeforeRowIndex
		&& columnIndex > finishBeforeColumnIndex; rowIndex--, columnIndex--) {
	    board.putTokenAt(token, rowIndex, columnIndex);
	}

	return board;
    }

    public static Board prepareBoardWithFourEqualTokensInLineInRow(int equalTokensRowIndex) {
	String token = "token";
	Board board = new Board();

	for (int rowCount = 0; rowCount <= equalTokensRowIndex; rowCount++) {
	    for (int columnIndex = 1; columnIndex < 5; columnIndex++) {

		if (rowCount != equalTokensRowIndex) {
		    board.putTokenInColumn(token + columnIndex, columnIndex);
		} else {
		    board.putTokenInColumn(token, columnIndex);
		}

	    }
	}
	return board;
    }

    public static Board prepareBoardWithFourEqualTokensInLineInColumn(int equalTokensColumnIndex) {
	String token = "token";
	String differentToken = "differentToken";
	Board board = new Board();

	board.putTokenInColumn(differentToken, equalTokensColumnIndex);

	for (int tokensCount = 0; tokensCount < 4; tokensCount++) {
	    board.putTokenInColumn(token, equalTokensColumnIndex);
	}
	return board;
    }

    public static Board prepareBoardWithFourEqualTokensOnTwoDiagonalBordersBottomRightToTopLeft() {
	String token = "token";
	Board board = new Board();

	board.putTokenAt(token, 1, 0);
	board.putTokenAt(token, 2, 1);
	board.putTokenAt(token, 4, 2);
	board.putTokenAt(token, 5, 3);

	return board;
    }

    public static Board prepareBoardWithFourEqualTokensOnTwoDiagonalBordersBottomLeftToTopRight() {
	String token = "token";
	Board board = new Board();

	board.putTokenAt(token, 5, 3);
	board.putTokenAt(token, 4, 4);
	board.putTokenAt(token, 1, 6);
	board.putTokenAt(token, 2, 5);

	return board;
    }
}
