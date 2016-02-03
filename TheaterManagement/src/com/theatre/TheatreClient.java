package com.theatre;

import java.util.List;

public class TheatreClient {
	public static void main(String[] args) {
		MovieTheater theater = new MovieTheater();
		List<MovieTicketRequest> ticketRequest = TheaterLayOutParser.parseAndPopuateTheater(theater, "TheatreInput.txt");
		TheatreRequestHandler theatreRequestHandler = new TheatreRequestHandler(theater);
		for (MovieTicketRequest request : ticketRequest) {
			theatreRequestHandler.handleRequest(request);
			System.out.println(request.getMessage());
		}
	}
}
