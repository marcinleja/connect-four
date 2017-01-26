package com.softniac.connectfour.web.dto;

import javax.validation.constraints.NotNull;

public class GameCreationData {

    @NotNull
    private String player1Name;

    @NotNull
    private String player2Name;

    public String getPlayer1Name() {
	return player1Name;
    }

    public void setPlayer1Name(String player1Name) {
	this.player1Name = player1Name;
    }

    public String getPlayer2Name() {
	return player2Name;
    }

    public void setPlayer2Name(String player2Name) {
	this.player2Name = player2Name;
    }

}
