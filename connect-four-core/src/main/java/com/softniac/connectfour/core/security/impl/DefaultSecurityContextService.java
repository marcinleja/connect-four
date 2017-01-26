package com.softniac.connectfour.core.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.softniac.connectfour.core.model.UserAccount;
import com.softniac.connectfour.core.repository.UserAccountRepository;
import com.softniac.connectfour.core.security.api.SecurityContextService;

@Service
public class DefaultSecurityContextService implements SecurityContextService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public UserAccount getCurrentUserAccount() {
	String currentUserLogin = getCurrentUserDetails().getUsername();
	return userAccountRepository.findByLogin(currentUserLogin);
    }

    private UserDetails getCurrentUserDetails() {
	return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
