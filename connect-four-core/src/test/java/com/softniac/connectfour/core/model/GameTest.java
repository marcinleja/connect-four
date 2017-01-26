package com.softniac.connectfour.core.model;

import static com.softniac.connectfour.core.model.GameTestDataPreparator.prepareGameWithOneMoreMoveNeededToHaveADraw;
import static com.softniac.connectfour.core.model.GameTestDataPreparator.prepareGameWithOneMoreMoveNeededToWin;
import static com.softniac.connectfour.core.model.GameTestDataPreparator.prepareGameWithPlayer1Turn;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class GameTest {
 
    private Game game;

    @Test
    public void shouldSetGameWinnerAndUpdateGameResultAppropriatelyWhenGameIsWon() {
	String winningMovePerformerPlayerName = "player1";
	game = prepareGameWithOneMoreMoveNeededToWin();
	int winningMove = 0;

	game.makeMove(winningMove);

	assertThat(game.getGameResult()).isEqualTo(GameResult.WINNER_EMERGED);
	assertThat(game.getWinnerName()).isEqualTo(winningMovePerformerPlayerName);
    }

    @Test
    public void shouldUpdateGameResultAsDrawWhenBoardIsFullAndNobodyWon() {
	game = prepareGameWithOneMoreMoveNeededToHaveADraw();
	int moveThatWillCauseADraw = 6;

	game.makeMove(moveThatWillCauseADraw);

	assertThat(game.getGameResult()).isEqualTo(GameResult.DRAW);
	assertThat(game.getWinnerName()).isNull();
    }

    @Test
    public void shouldSwitchTurnAfterPlayersMove() {
	game = prepareGameWithPlayer1Turn();
	int columnIndex = 0;
	game.makeMove(columnIndex);

	assertThat(game.getGameState().getCurrentTurn()).isEqualTo(PlayerTurn.PLAYER2);
    }

}
