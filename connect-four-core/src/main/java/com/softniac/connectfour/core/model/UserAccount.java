package com.softniac.connectfour.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_accounts")
public class UserAccount extends EntityBase {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Column(nullable = false, unique = true)
    String login;

    @JsonIgnore
    @NotNull
    @Column(nullable = false)
    String password;

    public UserAccount() {
    }

    public UserAccount(String login, String password) {
	this.login = login;
	this.password = password;
    }

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

}
