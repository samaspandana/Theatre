package com.theatre;

public class TheatreRequestHandler {
	
	private MovieTheater theater;
	
	
	public TheatreRequestHandler(MovieTheater theater){
		this.theater = theater;
	}
	
	public void handleRequest(MovieTicketRequest request ){
		ReturnResultWrapper wrapper = theater.canFit(request.getRequestSeat());
		if(wrapper == null){
			request.setMessage(request.getName() +" Sorry, we can't handle your party.");
		}else if(wrapper.getReturnTypeEnum() == ReturnEnum.CANNOT_FIT_ENTIRE){
			request.setMessage(request.getName() +" Sorry, we can't handle your party.");
		}else if(wrapper.getReturnTypeEnum() == ReturnEnum.FIT_WITH_SPLIT){
			request.setMessage(request.getName() +" Call to split party.");
		}else{
			wrapper.getSection().alloteSeats(request.getRequestSeat());
			request.setMessage(request.getName() +  " Row " + wrapper.getRow().getRowNumber() + " Section " + wrapper.getSection().getSectionNumber());
		}
	}
	
}
