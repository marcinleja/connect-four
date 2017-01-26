package com.softniac.connectfour.core.model;

public enum PlayerTurn {

    PLAYER1("player1"), PLAYER2("player2");

    private String name;

    private PlayerTurn(String name) {
	this.name = name;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
