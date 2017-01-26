package com.softniac.connectfour.core.service.api;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.softniac.connectfour.core.model.Game;

@Transactional
public interface GameService {

    public Game newGame(String player1Name, String player2Name);

    public Optional<Game> findById(Long gameId);

    public Game makeMove(Long gameId, Integer columnNumber);

    public List<Game> listGames();
}
