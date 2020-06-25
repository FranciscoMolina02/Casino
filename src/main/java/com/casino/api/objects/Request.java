package com.casino.api.objects;

public class Request {

	private int responseCode;
	private String responseMessage;

	public Request() {

	}

	public Request(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String mensajeRespuesta) {
		this.responseMessage = mensajeRespuesta;
	}
	
}
