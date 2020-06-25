package com.casino.api.validators;

import com.casino.api.objects.Bet;

public class Validator {

	private Validator() {
		super();
	}
	
	public static boolean validBet(Bet request) {
		boolean validBet = validColor(request) || validNumber(request);
		
		return validBet;
	}
	
	private static boolean validColor(Bet request) {
		if (request.getSelectedColor() != null && (request.getSelectedColor().equals("rojo") || request.getSelectedColor().equals("negro")))
		{
			return true;
		} else {
			return false;
		}
	}
	
	private static boolean validNumber(Bet request) {
		if (request.getSelectedNumber() != null && (request.getSelectedNumber() >= 0 && request.getSelectedNumber() <= 36)) {
			return true;
		} else {
		return false;
		}
	}
}
