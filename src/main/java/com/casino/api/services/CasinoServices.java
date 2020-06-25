package com.casino.api.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casino.api.common.RouletteRepository;
import com.casino.api.objects.Bet;
import com.casino.api.objects.Request;
import com.casino.api.objects.Roulette;

@Service
public class CasinoServices {
	@Autowired
	HttpServletRequest httpRequest;

	@Autowired
	RouletteRepository rouletteRepository;

	public List<Roulette> getRoulettes() {
		List<Roulette> roulettes = new ArrayList<>();
		rouletteRepository.findAll().forEach(roulettes::add);
		return roulettes;
	}

	public List<Bet> closeRoulettes(Long id) {
		Optional<Roulette> optional = rouletteRepository.findById(id);
		if (optional.isPresent()) {
			Roulette roulette = optional.get();
			ArrayList<Bet> bets = new ArrayList<Bet>(roulette.getBets());
			roulette.setStatus(false);
			roulette.setBets(null);
			rouletteRepository.save(roulette);
			return  bets;
		}
		return new ArrayList<>();
	}

	public Request openRoulette(Long id) {
		Optional<Roulette> optional = rouletteRepository.findById(id);
		Request request = new Request();
		if (optional.isPresent()) {
			Roulette roulette = optional.get();
			roulette.setStatus(true);
			rouletteRepository.save(roulette);
			request = performResponse(200, "Ruleta abierta con exito");
		} else {
			request = performResponse(400, "No se logró abrir la ruleta");
		}
		return request;
	}

	public Long createRoulette() {
		Roulette roulette = new Roulette(null, false);
		Roulette repoRoulette = rouletteRepository.save(roulette);
		return repoRoulette.getRouletteID();
	}

	public Request createBet(Bet bet) {
		boolean status = rouletteStatus(bet.getRouletteID());
		Request request = new Request();
		if (status && isValidAmount(bet)) {
			request = saveBet(bet);
		} else {
			request = performResponse(400, "La apuesta no cumple las condiciones de la ruleta");
		}
		return request;
	}
	
	private boolean isValidAmount(Bet bet) {
		if (bet.getBetAmount() != null && bet.getBetAmount() >= 1 && bet.getBetAmount() <= 10000) {
			return true;
		}
		return false;
	}

	private Request saveBet(Bet bet) {
		String user = httpRequest.getHeader("id");
		Roulette roulette = rouletteRepository.findById(bet.getRouletteID()).get();
		if (bet.getSelectedColor() != null) {
			bet.setSelectedNumber(null);
		} else {
			bet.setSelectedColor(null);
		}
		bet.setUserID(user);
		roulette.addBet(bet);
		rouletteRepository.save(roulette);
		return performResponse(200, "Apuesta guardada de manera exitosa");
	}
	
	private Request performResponse(int code, String message) {
		Request answer = new Request();
		answer.setResponseCode(code);
		answer.setResponseMessage(message);
		
		return answer;
	}
	

	private boolean rouletteStatus(Long rouletteID) {
		Optional<Roulette> optional = rouletteRepository.findById(rouletteID);
		if (optional.isPresent()) {
			if (optional.get().getStatus()) {
				return true;
			}
		}
		return false;
	}

}
