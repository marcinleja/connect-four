package com.softniac.connectfour.web.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.softniac.connectfour.core.service.api.UserAccountService;
import com.softniac.connectfour.web.dto.UserRegistrationData;

@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody @Valid UserRegistrationData userRegistrationData,
	    HttpServletResponse response) {
	userAccountService.createUserAccount(userRegistrationData.getLogin(), userRegistrationData.getPassword(),
		userRegistrationData.getRepeatedPassword());

	response.setStatus(200);
    }
}
