package com.softniac.connectfour.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softniac.connectfour.core.model.Game;
import com.softniac.connectfour.core.model.UserAccount;

public interface GameRepository extends JpaRepository<Game, Long> {

    public List<Game> findByOwner(UserAccount owner);

    public Game findByIdAndOwner(Long id, UserAccount owner);
}
