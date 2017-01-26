package com.softniac.connectfour.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softniac.connectfour.core.model.Game;
import com.softniac.connectfour.core.service.api.GameService;
import com.softniac.connectfour.web.dto.GameCreationData;
import com.softniac.connectfour.web.dto.GameMoveDto;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping(method = RequestMethod.POST)
    public Game newGame(@RequestBody GameCreationData gameCreationData) {
	return gameService.newGame(gameCreationData.getPlayer1Name(), gameCreationData.getPlayer2Name());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Game> list() {
	return gameService.listGames();
    }

    @RequestMapping(path = "/{gameId}", method = RequestMethod.GET)
    public Game findOne(@PathVariable Long gameId) {
	return gameService.findById(gameId).orElse(null);
    }

    @RequestMapping(path = "/{gameId}/move", method = RequestMethod.POST)
    public Game makeMove(@PathVariable Long gameId, @RequestBody GameMoveDto gameMove) {
	return gameService.makeMove(gameId, gameMove.getColumnNumber());
    }

}
