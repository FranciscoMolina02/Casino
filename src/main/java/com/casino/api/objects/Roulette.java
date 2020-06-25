package com.casino.api.objects;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Roulette")
public class Roulette {

	@Id
	private Long rouletteID;
	private List<Bet> bets;
	private boolean status;

	public Roulette() {
		bets = new ArrayList<>();
		status = false;
	}

	public Roulette(Long rouletteID, boolean status) {
		this.rouletteID = rouletteID;
		this.status = status;
	}

	public Long getRouletteID() {
		return rouletteID;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bet) {
		this.bets = bet;
	}

	public void addBet(Bet bet) {
		this.bets.add(bet);
	}

	public void setRouletteID(Long rouletteID) {
		this.rouletteID = rouletteID;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
