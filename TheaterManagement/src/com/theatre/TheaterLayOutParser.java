package com.theatre;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TheaterLayOutParser {

	public static List<MovieTicketRequest> parseAndPopuateTheater(MovieTheater movieTheatre,String fileName) {
		
		FileInputStream fstream = null;
		BufferedReader br = null;
		List<MovieTicketRequest> ticketRequests = new ArrayList<MovieTicketRequest>();
		try{
			fstream = new FileInputStream(fileName);
			br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			int count = 1;
			boolean isRequestFound = false;
			while ((strLine = br.readLine()) != null) {
				if (strLine.trim().isEmpty()) {
					isRequestFound = true;
					continue;
				}
				if (!isRequestFound) {
					String[] strs = strLine.split(" ");
					TheatreRow row = movieTheatre.addAndReturnRow(count++);
					for (int i = 0; i < strs.length; i++) {
						try {
							row.addAndReturnSection(i + 1, Integer.parseInt(strs[i]));
						} catch (NumberFormatException ex) {
							throw new RuntimeException("Invalid Seat number given at Row #" + (i + 1));
						}
					}
				} else {
					String[] strs = strLine.split(" ");
					if(strs.length !=2){
						throw new RuntimeException("Invalid request received");
					}
					String name = strs[0];
					if(name == null || name.isEmpty()){
						throw new RuntimeException("Invalid Ticket holder name,please provide the name.");
					}
					String requestSeatAsStr = strs[1];
					try {
						int requestSeat = Integer.parseInt(requestSeatAsStr);
						ticketRequests.add(new MovieTicketRequest(name,requestSeat));
					
					}catch (NumberFormatException ex) {
						throw new RuntimeException("Invalid Seat number,please provide the correct requested ticket number");
					}
					
					
				}
			}
		}catch(IOException ex){
			throw new RuntimeException("Exceptoin occured during file parsing");
			
		}finally{
			try {
				fstream.close();
                br.close();
            } catch (IOException ex) {
            	System.out.println("Exceptoin occured during the closing the resource");
            }
		}
		
		if(movieTheatre.getRows().isEmpty()){
			throw new RuntimeException("No row information provided");
		}
		
		if(ticketRequests.isEmpty()){
			throw new RuntimeException("No ticketin requestor information provided");
		}
		return ticketRequests;
		
	}
}
