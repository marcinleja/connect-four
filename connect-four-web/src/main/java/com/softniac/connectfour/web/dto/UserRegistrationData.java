package com.softniac.connectfour.web.dto;

import javax.validation.constraints.NotNull;

public class UserRegistrationData {

    @NotNull
    String login;

    @NotNull
    String password;

    @NotNull
    String repeatedPassword;

    public String getLogin() {
	return login;
    }

    public void setLogin(String login) {
	this.login = login;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getRepeatedPassword() {
	return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
	this.repeatedPassword = repeatedPassword;
    }

}
