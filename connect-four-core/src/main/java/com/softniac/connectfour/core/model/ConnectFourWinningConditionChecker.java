package com.softniac.connectfour.core.model;

import org.assertj.core.util.Strings;
import org.springframework.stereotype.Component;

@Component
public class ConnectFourWinningConditionChecker {

    private static ConnectFourWinningConditionChecker instance = null;

    protected ConnectFourWinningConditionChecker() {
    } 

    public static ConnectFourWinningConditionChecker getInstance() {
	if (instance == null) {
	    instance = new ConnectFourWinningConditionChecker();
	}
	return instance;
    }

    public boolean isHorizontalWin(Board board) {
	int boardRowDimension = board.getRowDimension();

	int bottomRowIndex = boardRowDimension - 1;
	for (int rowIndex = bottomRowIndex; rowIndex >= 0; rowIndex--) {
	    if (rowHasFourEqualTokensInLine(rowIndex, board)) {
		return true;
	    }
	}
	return false;
    }

    private boolean rowHasFourEqualTokensInLine(int rowIndex, Board board) {
	int boardColumnDimension = board.getColumnDimension();

	String currentToken = null;
	String previousToken = null;

	int countOfTokensInLine = 0;

	int columnIndex = 0;
	while (columnIndex < boardColumnDimension) {

	    currentToken = board.getToken(rowIndex, columnIndex);

	    if (!fourTokensInLineAreStillPossibleToFindInRow(currentToken, previousToken, columnIndex)) {
		return false;
	    }

	    if (tokensAreEqual(currentToken, previousToken)) {
		countOfTokensInLine = incrementCountOfTokensInLine(countOfTokensInLine);
	    } else {
		countOfTokensInLine = 0;
	    }

	    if (countOfTokensInLine == 4) {
		return true;
	    }
	    previousToken = currentToken;

	    columnIndex = moveIndexToRightColumn(columnIndex);
	}
	return false;
    }

    private int incrementCountOfTokensInLine(int countOfTokensInLine) {
	if (matchedTheFirstTwoTokens(countOfTokensInLine)) {
	    countOfTokensInLine += 2;
	} else {
	    countOfTokensInLine += 1;
	}
	return countOfTokensInLine;
    }

    private boolean matchedTheFirstTwoTokens(int countOfTokensInLine) {
	return countOfTokensInLine == 0;
    }

    private boolean fourTokensInLineAreStillPossibleToFindInRow(String currentToken, String previousToken,
	    int columnIndex) {
	if (numberOfSpotsToCheckInRowIsLessThanFour(columnIndex)
		&& (Strings.isNullOrEmpty(currentToken) || !currentToken.equals(previousToken))) {
	    return false;
	}
	return true;
    }

    private boolean numberOfSpotsToCheckInRowIsLessThanFour(int columnIndex) {
	return columnIndex > 3;
    }

    private boolean tokensAreEqual(String currentToken, String previousToken) {
	return !Strings.isNullOrEmpty(currentToken) && currentToken.equals(previousToken);
    }

    public boolean isVerticalWin(Board board) {

	int boardColumnDimension = board.getColumnDimension();

	for (int columnIndex = 0; columnIndex < boardColumnDimension; columnIndex++) {
	    if (columnHasFourEqualTokensInLine(columnIndex, board)) {
		return true;
	    }
	}
	return false;
    }

    private boolean columnHasFourEqualTokensInLine(int columnIndex, Board board) {
	int boardRowDimension = board.getRowDimension();

	String currentToken = null;
	String previousToken = null;
	int countOfTokensInLine = 0;

	// tokens are put bottom -> up, so checking starts from the bottom
	int rowIndex = boardRowDimension - 1;

	while (rowIndex >= 0) {

	    currentToken = board.getToken(rowIndex, columnIndex);

	    if (!fourInLineAreStillPossibleToFindInColumn(currentToken, previousToken, rowIndex)) {
		return false;
	    }

	    if (tokensAreEqual(currentToken, previousToken)) {
		countOfTokensInLine = incrementCountOfTokensInLine(countOfTokensInLine);
	    } else {
		countOfTokensInLine = 0;
	    }

	    if (countOfTokensInLine == 4) {
		return true;
	    }
	    previousToken = currentToken;

	    rowIndex = moveIndexToUpperRow(rowIndex);
	}
	return false;

    }

    private boolean fourInLineAreStillPossibleToFindInColumn(String currentToken, String previousToken, int rowIndex) {
	if (Strings.isNullOrEmpty(currentToken)
		|| (numberOfTokensToCheckInColumnIsLessThanFour(rowIndex) && !currentToken.equals(previousToken))) {
	    return false;
	}
	return true;
    }

    private boolean numberOfTokensToCheckInColumnIsLessThanFour(int rowIndex) {
	return rowIndex < 4;
    }

    public boolean isDiagonalWin(Board board) {
	return bottomLeftToTopRightHasFourTokensInLine(board) || bottomRightToTopLeftHasFourTokensInLine(board);
    }

    private boolean bottomLeftToTopRightHasFourTokensInLine(Board board) {
	int boardRowDimension = board.getRowDimension();
	int boardColumnDimension = board.getColumnDimension();

	String currentToken = null;
	String previousToken = null;
	int countOfTokensInLine = 0;

	int bottomRowIndex = boardRowDimension - 1;
	int leftColumnIndex = 0;

	int rowIndexAfterWhichFourInDiagonalCannotBeFound = 3;
	int columnIndexAfterWhichFourInDiagonalCannotBeFound = 3;

	for (int rowIndex = bottomRowIndex; rowIndex >= rowIndexAfterWhichFourInDiagonalCannotBeFound; rowIndex--) {
	    for (int columnIndex = leftColumnIndex; columnIndex <= columnIndexAfterWhichFourInDiagonalCannotBeFound; columnIndex++) {

		int tempRowIndex = rowIndex;
		int tempColumnIndex = columnIndex;

		currentToken = null;
		previousToken = null;
		countOfTokensInLine = 0;

		while (tempRowIndex >= 0 && tempColumnIndex < boardColumnDimension) {

		    currentToken = board.getToken(tempRowIndex, tempColumnIndex);

		    if (tokensAreEqual(currentToken, previousToken)) {
			countOfTokensInLine = incrementCountOfTokensInLine(countOfTokensInLine);
		    } else {
			countOfTokensInLine = 0;
		    }

		    if (countOfTokensInLine == 4) {
			return true;
		    }
		    previousToken = currentToken;
		    tempRowIndex = moveIndexToUpperRow(tempRowIndex);
		    tempColumnIndex = moveIndexToRightColumn(tempColumnIndex);
		}

	    }
	}

	return false;
    }

    private int moveIndexToUpperRow(int rowIndex) {
	return --rowIndex;
    }

    private int moveIndexToRightColumn(int columnIndex) {
	return ++columnIndex;
    }

    private boolean bottomRightToTopLeftHasFourTokensInLine(Board board) {
	int boardRowDimension = board.getRowDimension();
	int boardColumnDimension = board.getColumnDimension();

	String currentToken = null;
	String previousToken = null;
	int countOfTokensInLine = 0;

	int bottomRowIndex = boardRowDimension - 1;
	int mostRightColumnIndex = boardColumnDimension - 1;

	int rowIndexAfterWhichFourInDiagonalCannotBeFound = 3;
	int columnIndexAfterWhichFourInDiagonalCannotBeFound = 3;

	for (int rowIndex = bottomRowIndex; rowIndex >= rowIndexAfterWhichFourInDiagonalCannotBeFound; rowIndex--) {
	    for (int columnIndex = mostRightColumnIndex; columnIndex >= columnIndexAfterWhichFourInDiagonalCannotBeFound; columnIndex--) {

		int tempRowIndex = rowIndex;
		int tempColumnIndex = columnIndex;

		currentToken = null;
		previousToken = null;
		countOfTokensInLine = 0;

		while (tempRowIndex >= 0 && tempColumnIndex >= 0) {

		    currentToken = board.getToken(tempRowIndex, tempColumnIndex);

		    if (tokensAreEqual(currentToken, previousToken)) {
			countOfTokensInLine = incrementCountOfTokensInLine(countOfTokensInLine);
		    } else {
			countOfTokensInLine = 0;
		    }

		    if (countOfTokensInLine == 4) {
			return true;
		    }
		    previousToken = currentToken;
		    tempRowIndex = moveIndexToUpperRow(tempRowIndex);
		    tempColumnIndex = moveIndexToLeftColumn(tempColumnIndex);
		}

	    }
	}

	return false;
    }

    private int moveIndexToLeftColumn(int columnIndex) {
	return --columnIndex;

    }

}
