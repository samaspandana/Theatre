package com.theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreSection {

	public List<TheatreSeat> seats = new ArrayList<TheatreSeat> ();
	private int totalAvaliable =0;
	private int totalSeats = 0;
	 private int sectionNumber;
	 private TheatreRow row;
	
	 public TheatreSection(int sectionNumber,TheatreRow row){
			this.sectionNumber = sectionNumber;
			this.row = row;
	}
	 
	 
	 
	public void addSeats(int totalSeats){
		for(int i= 0;i< totalSeats;i++){
			seats.add(new TheatreSeat(i+1));
		}
		totalAvaliable = totalAvaliable + totalSeats;
		this.row.setTotalAvaliable(this.row.getTotalAvaliable() + totalSeats);
		this.row.getTheater().setTotalAvaliable(this.row.getTheater().getTotalAvaliable() + totalSeats);
		this.totalSeats  = this.totalSeats + totalSeats;
		
		
	}
	
	public boolean isSeatAvaliables(int requestSeatNumbers){
		if(requestSeatNumbers > totalAvaliable){
			return false;
		}
		return true;
	}
	
	public void alloteSeats(int requestSeatNumbers){
		int tempRequestNumber = requestSeatNumbers;
		for(int i= 0;i< seats.size();i++){
			if(requestSeatNumbers >0 && !seats.get(i).isAllotted()){
				seats.get(i).setAllotted(true);
				requestSeatNumbers--;
				totalAvaliable = totalAvaliable - 1;
			}
		}
		row.setTotalAvaliable(row.getTotalAvaliable() - tempRequestNumber);
		row.getTheater().setTotalAvaliable(row.getTheater().getTotalAvaliable() - tempRequestNumber);
	}
	
	public ReturnEnum canFit(int requestSeatNumbers){
		if(requestSeatNumbers > totalAvaliable){
			return ReturnEnum.CANNOT_FIT_SECTION;
		} else if (requestSeatNumbers == totalAvaliable){
			return ReturnEnum.BEST_FIT;
		}else {
			return ReturnEnum.CAN_FIT;
		}
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public int getSectionNumber() {
		return sectionNumber;
	}

	public int getTotalAvaliable() {
		return totalAvaliable;
	}
	
}
