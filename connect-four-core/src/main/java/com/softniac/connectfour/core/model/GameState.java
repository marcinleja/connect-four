package com.softniac.connectfour.core.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "games_states")
public class GameState extends EntityBase {

    private static final long serialVersionUID = 1L;

    @Enumerated(EnumType.STRING)
    private PlayerTurn currentTurn;

    private Board board;

    public GameState() {
	currentTurn = PlayerTurn.PLAYER1;
	board = new Board();
    }

    public PlayerTurn getCurrentTurn() {
	return currentTurn;
    }

    public void setCurrentTurn(PlayerTurn currentTurn) {
	this.currentTurn = currentTurn;
    }

    public Board getBoard() {
	return board;
    }

    public void setBoard(Board board) {
	this.board = board;
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();

	stringBuilder.append("Game state: \n\n").append("Current player turn: " + currentTurn.getName()).append("\n\n")
		.append(board.toString());

	return stringBuilder.toString();
    }

    public void changePlayerTurn() {
	if (currentTurn.equals(PlayerTurn.PLAYER1)) {
	    currentTurn = PlayerTurn.PLAYER2;
	} else {
	    currentTurn = PlayerTurn.PLAYER1;
	}
    }

}
