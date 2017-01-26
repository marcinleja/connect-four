package com.softniac.connectfour.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softniac.connectfour.core.model.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    public UserAccount findByLogin(String login);
}
