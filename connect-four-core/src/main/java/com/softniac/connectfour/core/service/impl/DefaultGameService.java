package com.softniac.connectfour.core.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softniac.connectfour.core.model.Game;
import com.softniac.connectfour.core.model.GameResult;
import com.softniac.connectfour.core.model.UserAccount;
import com.softniac.connectfour.core.repository.GameRepository;
import com.softniac.connectfour.core.security.api.SecurityContextService;
import com.softniac.connectfour.core.service.api.GameService;

@Service
public class DefaultGameService implements GameService {

    private static final Logger log = LoggerFactory.getLogger(DefaultGameService.class);

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    SecurityContextService securityContextService;
 
    @Override
    public Game newGame(String player1Name, String player2Name) {
	log.info("Creating new game for players: {}, {} ", player1Name, player2Name);
	UserAccount gameOwner = securityContextService.getCurrentUserAccount();

	Game newGame = new Game(gameOwner, player1Name, player2Name);

	return gameRepository.save(newGame);
    } 

    @Override
    public Game makeMove(Long gameId, Integer columnNumber) {
	log.info("Processing game move request for game with id: {}. Column number: {}", gameId, columnNumber);
	Game game = findById(gameId).orElseThrow(
		() -> new IllegalArgumentException("Game of given id doesn't exist or you have no rights to play it"));
	if (gameInProgress(game)) {
	    game.makeMove(columnNumber);
	} else {
	    throw new IllegalStateException("This game has already ended!");
	}

	return gameRepository.save(game);
    }

    private boolean gameInProgress(Game game) {
	return game.getGameResult().equals(GameResult.IN_PROGRESS);
    }

    @Override
    public List<Game> listGames() {
	UserAccount currentUser = securityContextService.getCurrentUserAccount();
	return gameRepository.findByOwner(currentUser);
    }

    @Override
    public Optional<Game> findById(Long gameId) {
	UserAccount currentUser = securityContextService.getCurrentUserAccount();
	return Optional.ofNullable(gameRepository.findByIdAndOwner(gameId, currentUser));
    }

}
