package com.theatre;

public class MovieTicketRequest {

	private String name;
	private int requestSeat;
	private String message;
	
	public MovieTicketRequest(String name,  int requestSeat){
		this.name = name;
		this.requestSeat = requestSeat;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public int getRequestSeat() {
		return requestSeat;
	}
}
