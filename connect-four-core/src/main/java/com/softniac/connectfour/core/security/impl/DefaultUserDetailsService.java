package com.softniac.connectfour.core.security.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.softniac.connectfour.core.model.UserAccount;
import com.softniac.connectfour.core.repository.UserAccountRepository;
import com.softniac.connectfour.core.security.api.Role;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserDetailsService.class);

    @Autowired
    PasswordEncoder bcryptPasswordEncoder;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	logger.info("Looking for user:" + username);

	UserAccount userAccount = userAccountRepository.findByLogin(username);

	if (userAccount != null) {
	    return new User(userAccount.getLogin(), userAccount.getPassword(), defaultAuthorities());
	}

	return null;
    }

    private Set<GrantedAuthority> defaultAuthorities() {
	Set<GrantedAuthority> authorities = new HashSet<>();
	authorities.add(new SimpleGrantedAuthority(Role.ROLE_USER.name()));
	return authorities;
    }

}