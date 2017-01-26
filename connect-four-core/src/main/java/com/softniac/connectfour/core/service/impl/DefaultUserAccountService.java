package com.softniac.connectfour.core.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softniac.connectfour.core.model.UserAccount;
import com.softniac.connectfour.core.repository.UserAccountRepository;
import com.softniac.connectfour.core.service.api.UserAccountService;

@Service
public class DefaultUserAccountService implements UserAccountService {

    private static final Logger log = LoggerFactory.getLogger(DefaultUserAccountService.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccountRepository userAccountRepostiory;

    @Override
    public void createUserAccount(String login, String password, String repeatedPassword) {
	log.info("Creating user accout for: {}", login);
	if (passwordsAreNotEqual(password, repeatedPassword)) {
	    throw new IllegalArgumentException("Password and repeated passwords must be equal!");
	}
	UserAccount userAccount = new UserAccount(login, passwordEncoder.encode(password));
	userAccountRepostiory.save(userAccount);
	log.info("Successfully created user account: {}", login);
    }

    private boolean passwordsAreNotEqual(String password, String repeatedPassword) {
	return !password.equals(repeatedPassword);
    }

}
