package com.casino.api.objects;

import org.springframework.data.redis.core.index.Indexed;

public class Bet {

	@Indexed
	private Long rouletteID;
	private Long betAmount;
	private Long selectedNumber;
	private String userID;
	private String selectedColor;

	public Long getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(Long betAmount) {
		this.betAmount = betAmount;
	}

	public Long getSelectedNumber() {
		return selectedNumber;
	}

	public void setSelectedNumber(Long selectedNumber) {
		this.selectedNumber = selectedNumber;
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getSelectedColor() {
		return selectedColor;
	}

	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
	
	public Long getRouletteID() {
		return rouletteID;
	}

	public void getRouletteID(Long rouletteID) {
		this.rouletteID = rouletteID;
	}
}
