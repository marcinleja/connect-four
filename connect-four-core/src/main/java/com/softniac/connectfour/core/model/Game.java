package com.softniac.connectfour.core.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.softniac.connectfour.core.service.impl.DefaultGameService;

@Entity
@Table(name = "games")
public class Game extends EntityBase {

    private static final Logger log = LoggerFactory.getLogger(DefaultGameService.class);

    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    private UserAccount owner;

    @Column(nullable = false)
    private String player1Name;

    @Column(nullable = false)
    private String player2Name;

    private String winnerName;

    @Enumerated(EnumType.STRING)
    private GameResult gameResult;

    @OneToOne(optional = false, cascade = { CascadeType.ALL }, orphanRemoval = true)
    private GameState gameState;

    public Game() {
	gameState = new GameState();
	gameResult = GameResult.IN_PROGRESS;
    }

    public Game(UserAccount owner, String player1Name, String player2Name) {
	this();
	this.owner = owner;
	this.player1Name = player1Name;
	this.player2Name = player2Name;
    }

    public UserAccount getOwner() {
	return owner;
    }

    public void setOwner(UserAccount owner) {
	this.owner = owner;
    }

    public GameResult getGameResult() {
	return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
	this.gameResult = gameResult;
    }

    public GameState getGameState() {
	return gameState;
    }

    public void setGameState(GameState gameState) {
	this.gameState = gameState;
    }

    public String getPlayer1Name() {
	return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
	this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
	return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
	this.player2Name = player2Name;
    }

    public String getWinnerName() {
	return winnerName;
    }

    public void setWinnerName(String winnerName) {
	this.winnerName = winnerName;
    }

    public void makeMove(Integer columnNumber) {
	log.info("Player: {} is making move...", getCurrentTurnPlayerName());
	Board board = gameState.getBoard();
	String currentPlayerToken = gameState.getCurrentTurn().getName();

	board.putTokenInColumn(currentPlayerToken, columnNumber);

	if (winnerEmerged(board)) {
	    updateGameResult(GameResult.WINNER_EMERGED);
	    markGameWinner();
	    log.info("Player {} has won the game!", winnerName);
	} else if (isDraw(board)) {
	    updateGameResult(GameResult.DRAW);
	    log.info("The game has ended with a draw!");
	} else {
	    log.info("Switching turns...");
	    switchTurn();
	}

	log.trace(this.gameState.toString());
    }

    private void switchTurn() {
	PlayerTurn currentPlayerTurn = gameState.getCurrentTurn();
	if (currentPlayerTurn.equals(PlayerTurn.PLAYER1)) {
	    gameState.setCurrentTurn(PlayerTurn.PLAYER2);
	} else {
	    gameState.setCurrentTurn(PlayerTurn.PLAYER1);
	}
    }

    private void updateGameResult(GameResult updatedGameStatus) {
	this.gameResult = updatedGameStatus;

    }

    private boolean winnerEmerged(Board board) {
	return isVerticalWin(board) || isHorizontalWin(board) || isDiagonalWin(board);

    }

    private boolean isDraw(Board board) {
	return board.isFull();
    }

    private void markGameWinner() {
	PlayerTurn currentPlayerTurn = gameState.getCurrentTurn();
	if (currentPlayerTurn.equals(PlayerTurn.PLAYER1)) {
	    this.winnerName = this.player1Name;
	} else {
	    this.winnerName = this.player2Name;
	}
    }

    private String getCurrentTurnPlayerName() {
	PlayerTurn currentPlayerTurn = gameState.getCurrentTurn();
	if (currentPlayerTurn.equals(PlayerTurn.PLAYER1)) {
	    return this.player1Name;
	} else {
	    return this.player2Name;
	}
    }

    private boolean isHorizontalWin(Board board) {
	return ConnectFourWinningConditionChecker.getInstance().isHorizontalWin(board);
    }

    private boolean isVerticalWin(Board board) {
	return ConnectFourWinningConditionChecker.getInstance().isVerticalWin(board);
    }

    private boolean isDiagonalWin(Board board) {
	return ConnectFourWinningConditionChecker.getInstance().isDiagonalWin(board);
    }

    @Override
    public String toString() {
	StringBuilder stringBuilder = new StringBuilder();

	stringBuilder.append("Game status:\n");
	stringBuilder.append("Owner username: ").append(owner.getLogin()).append("\n");

	stringBuilder.append("Player1 : ").append(player1Name).append("\n");
	stringBuilder.append("Player2 : ").append(player2Name).append("\n").append("\n");

	stringBuilder.append(gameState.toString());

	return stringBuilder.toString();
    }

}
