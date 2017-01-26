package com.softniac.connectfour.core.model;

import java.util.Objects;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;

import org.assertj.core.util.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
public class Board {

    @JsonProperty("board") 
    @Lob
    @Column(length = 20000)
    private String[][] board;

    public Board() {
	board = new String[6][7];
    }

    public int getRowDimension() {
	return board.length;
    }

    public int getColumnDimension() {
	return board[0].length;
    }

    public String getToken(int row, int column) {
	return board[row][column];
    }

    public void putTokenInColumn(String token, int columnIndex) {
	if (columnIndex >= getColumnDimension()) {
	    throw new IllegalArgumentException(
		    "Column index must be in range [0, " + (getColumnDimension() - 1) + " ]");
	}
	putTokenInColumnAtFirstEmptyBottomRow(token, columnIndex);
    }

    private void putTokenInColumnAtFirstEmptyBottomRow(String token, int columnIndex) {
	int firstEmptyBottomRowIndex = getFirstEmptyBottomRowIndexInColumn(columnIndex)
		.orElseThrow(() -> new IllegalStateException(
			"Cannot add token. This column has already reached maximum number of tokens"));
	putTokenAt(token, firstEmptyBottomRowIndex, columnIndex);
    }

    protected void putTokenAt(String token, int rowIndex, int columnIndex) {
	board[rowIndex][columnIndex] = token;
    }

    private Optional<Integer> getFirstEmptyBottomRowIndexInColumn(int columnIndex) {
	for (int rowIndex = board.length - 1; rowIndex >= 0; rowIndex--) {
	    if (Strings.isNullOrEmpty(board[rowIndex][columnIndex])) {
		return Optional.of(rowIndex);
	    }
	}
	return Optional.empty();
    }

    @JsonIgnore
    public boolean isFull() {
	for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
	    for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++) {
		if (Objects.isNull(board[rowIndex][columnIndex])) {
		    return false;
		}
	    }
	}
	return true;
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();

	stringBuilder.append("Board: \n");

	for (int rowIndex = 0; rowIndex < board.length; rowIndex++) {
	    for (int columnIndex = 0; columnIndex < board[rowIndex].length; columnIndex++) {
		stringBuilder.append(board[rowIndex][columnIndex]).append(" ");
	    }
	    stringBuilder.append("\n");
	}

	return stringBuilder.toString();
    }

}
