package com.softniac.connectfour.core.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.softniac.connectfour.core.IntegrationTestBase;
import com.softniac.connectfour.core.model.Game;
import com.softniac.connectfour.core.model.UserAccount;

public class GameRepositoryIT extends IntegrationTestBase {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    public void shouldSaveGame() {
	UserAccount gameOwner = new UserAccount();
	gameOwner.setLogin("user1");
	gameOwner.setPassword("user_password");

	String player1Name = "John";
	String player2Name = "Dan";

	userAccountRepository.save(gameOwner);

	Game game = new Game(gameOwner, player1Name, player2Name);
	game = gameRepository.save(game);

	assertThat(game).isNotNull();
    }
}
