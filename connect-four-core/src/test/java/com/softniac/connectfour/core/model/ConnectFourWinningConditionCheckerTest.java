package com.softniac.connectfour.core.model;

import static com.softniac.connectfour.core.model.ConnectFourWinningConditionCheckerTestDataPreparator.prepareBoardWithFourEqualTokensInDiagonalBottomLeftToTopRight;
import static com.softniac.connectfour.core.model.ConnectFourWinningConditionCheckerTestDataPreparator.prepareBoardWithFourEqualTokensInDiagonalBottomRightToTopLeft;
import static com.softniac.connectfour.core.model.ConnectFourWinningConditionCheckerTestDataPreparator.prepareBoardWithFourEqualTokensInLineInColumn;
import static com.softniac.connectfour.core.model.ConnectFourWinningConditionCheckerTestDataPreparator.prepareBoardWithFourEqualTokensInLineInRow;
import static com.softniac.connectfour.core.model.ConnectFourWinningConditionCheckerTestDataPreparator.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ConnectFourWinningConditionCheckerTest {

    private ConnectFourWinningConditionChecker winningConditionChecker;

    private Board board;

    @Before
    public void init() {
	winningConditionChecker = ConnectFourWinningConditionChecker.getInstance();

	board = emptyBoard();
    }

    private Board emptyBoard() {
	return new Board();
    }

    @Test
    public void shouldNotFindHorizontalWinWhenBoardIsEmpty() {
	assertThat(winningConditionChecker.isHorizontalWin(board)).isFalse();
    }

    @Test
    public void shouldNotFindVerticalWinWhenBoardIsEmpty() {
	assertThat(winningConditionChecker.isVerticalWin(board)).isFalse();
    }

    @Test
    public void shouldNotFindDiagonalWinWhenBoardIsEmpty() {
	assertThat(winningConditionChecker.isDiagonalWin(board)).isFalse();
    }

    @Test
    public void shouldFindHorizontalWinWhenFourEqualTokensInLineExistInRow() {
	int bottomRowIndex = board.getRowDimension() - 1;

	for (int rowIndex = bottomRowIndex; rowIndex >= 0; rowIndex--) {
	    board = prepareBoardWithFourEqualTokensInLineInRow(rowIndex);
	    assertThat(winningConditionChecker.isHorizontalWin(board)).isTrue();
	}
    }

    @Test
    public void shouldFindVerticalWinWhenFourEqualTokensInLineExistInColumn() {
	int firstColumnIndex = 0;

	for (int columnIndex = firstColumnIndex; columnIndex < board.getColumnDimension(); columnIndex++) {
	    board = prepareBoardWithFourEqualTokensInLineInColumn(columnIndex);
	    assertThat(winningConditionChecker.isVerticalWin(board)).isTrue();
	}
    }

    @Test
    public void shouldFindDiagaonalWinWhenFourInLineExistInDiagonalBottomLeftToTopRight() {
	int bottomLeftRowIndex = 5;
	int mostLeftColumnIndex = 0;

	int rowIndexBeforeWhichNoFourInLineCanBeFound = 3;
	int columnIndexAfterWhichNoFourInLineCanBeFound = 3;

	for (int rowIndex = bottomLeftRowIndex; rowIndex >= rowIndexBeforeWhichNoFourInLineCanBeFound; rowIndex--) {
	    for (int columnIndex = mostLeftColumnIndex; columnIndex <= columnIndexAfterWhichNoFourInLineCanBeFound; columnIndex++) {
		board = prepareBoardWithFourEqualTokensInDiagonalBottomLeftToTopRight(rowIndex, columnIndex);
		assertThat(winningConditionChecker.isDiagonalWin(board)).isTrue();
	    }
	}
    }

    @Test
    public void shouldFindDiagaonalWinWhenFourInLineExistInDiagonalBottomRightToTopLeft() {
	int bottomLeftRowIndex = 5;
	int mostRightColumnIndex = 6;

	int rowIndexBeforeWhichNoFourInLineCanBeFound = 3;
	int columnIndexAfterWhichNoFourInLineCanBeFound = 3;

	for (int rowIndex = bottomLeftRowIndex; rowIndex >= rowIndexBeforeWhichNoFourInLineCanBeFound; rowIndex--) {
	    for (int columnIndex = mostRightColumnIndex; columnIndex >= columnIndexAfterWhichNoFourInLineCanBeFound; columnIndex--) {
		board = prepareBoardWithFourEqualTokensInDiagonalBottomRightToTopLeft(rowIndex, columnIndex);
		assertThat(winningConditionChecker.isDiagonalWin(board)).isTrue();
	    }
	}
    }

    @Test
    public void eachDiagonalShouldBeTreatedSeparatelyWhenSearchingForWinBottomRightToTopLeft() {
	board = prepareBoardWithFourEqualTokensOnTwoDiagonalBordersBottomRightToTopLeft();
	assertThat(winningConditionChecker.isDiagonalWin(board)).isFalse();
    }

    @Test
    public void eachDiagonalShouldBeTreatedSeparatelyWhenSearchingForWin() {
	board = prepareBoardWithFourEqualTokensOnTwoDiagonalBordersBottomLeftToTopRight();
	assertThat(winningConditionChecker.isDiagonalWin(board)).isFalse();
    }

}
