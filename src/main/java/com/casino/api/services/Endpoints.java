package com.casino.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.casino.api.objects.Bet;
import com.casino.api.objects.Request;
import com.casino.api.objects.Roulette;
import com.casino.api.validators.Validator;

@RestController
public class Endpoints {

	@Autowired
	CasinoServices services;

	@GetMapping("/create_roulette")
	public Long crearRuleta() {
		return services.createRoulette();
	}

	@PostMapping("/roulette_opening")
	public Request OpenRoulette(@RequestParam("id") Long id) {
		return services.openRoulette(id);
	}

	@PostMapping("/new_bet")
	public Request betInRoulette(@RequestBody Bet request) {
		boolean result = Validator.validBet(request);
		Request answer = new Request();
		if (result) {
			answer = services.createBet(request);
		} else {
			answer.setResponseCode(400);
			answer.setResponseMessage("No se puede crear la apuesta");
		}
		return answer;
	}

	@GetMapping("/close_roulette")
	public List<Bet> cerrarRuletaConResultados(@RequestParam("roulette") Long rouletteID) {
		return services.closeRoulettes(rouletteID);
	}

	@GetMapping("/get_all")
	public List<Roulette> getRoulettes() {
		return services.getRoulettes();
	}

}
