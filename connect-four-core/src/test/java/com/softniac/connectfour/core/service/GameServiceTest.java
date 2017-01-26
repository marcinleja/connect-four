package com.softniac.connectfour.core.service;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.softniac.connectfour.core.model.Game;
import com.softniac.connectfour.core.model.GameResult;
import com.softniac.connectfour.core.model.UserAccount;
import com.softniac.connectfour.core.repository.GameRepository;
import com.softniac.connectfour.core.security.api.SecurityContextService;
import com.softniac.connectfour.core.service.impl.DefaultGameService;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceTest {

    @Mock
    private GameRepository gameRepository;

    @Mock
    SecurityContextService securityContextService;
 
    @InjectMocks
    private DefaultGameService gameService;

    @Rule
    public ExpectedException thrownException = ExpectedException.none();

    Game game;
    UserAccount gameOwner;
    Long gameId;
    int columnNumber;

    @Before
    public void init() {
	gameOwner = new UserAccount();
	gameId = 0L;
	columnNumber = 0;
    }

    @Test
    public void shouldThrowExceptionWhenTryingToMakeMoveOnAGameThatHasEnded() {
	game = prepareGameThatHasEnded();

	when(securityContextService.getCurrentUserAccount()).thenReturn(gameOwner);
	when(gameRepository.findByIdAndOwner(gameId, gameOwner)).thenReturn(game);

	thrownException.expect(IllegalStateException.class);
	gameService.makeMove(gameId, columnNumber);
    }

    private Game prepareGameThatHasEnded() {
	Game gameThatHasEnded = new Game();
	gameThatHasEnded.setWinnerName("player1");
	gameThatHasEnded.setGameResult(GameResult.WINNER_EMERGED);

	return gameThatHasEnded;
    }

    @Test
    public void shouldThrowExceptionWhenTryingToMakeMoveOnAGameThatDoesNotExist() {
	when(securityContextService.getCurrentUserAccount()).thenReturn(gameOwner);
	when(gameRepository.findByIdAndOwner(gameId, gameOwner)).thenReturn(null);

	thrownException.expect(IllegalArgumentException.class);
	gameService.makeMove(gameId, columnNumber);
    }

    @Test
    public void shouldThrowExceptionWhenTryingToMakeMoveOnAGameThatIsNotOwnedByRequestor() {
	UserAccount someUserThatDoestOwnTheGame = new UserAccount();

	when(securityContextService.getCurrentUserAccount()).thenReturn(someUserThatDoestOwnTheGame);
	when(gameRepository.findByIdAndOwner(gameId, someUserThatDoestOwnTheGame)).thenReturn(null);

	thrownException.expect(IllegalArgumentException.class);
	gameService.makeMove(gameId, columnNumber);
    }

    @Test
    public void shouldMakeMoveOnAGame() {
	game = spy(Game.class);

	when(securityContextService.getCurrentUserAccount()).thenReturn(gameOwner);
	when(gameRepository.findByIdAndOwner(gameId, gameOwner)).thenReturn(game);

	gameService.makeMove(gameId, columnNumber);

	verify(game, times(1)).makeMove(columnNumber);
    }
}
