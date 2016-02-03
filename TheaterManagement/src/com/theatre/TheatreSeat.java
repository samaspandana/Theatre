package com.theatre;

public class TheatreSeat {
	private boolean allotted;
	private int seatNumber;

	public TheatreSeat(int seatNumber){
		this.seatNumber = seatNumber;
	}
	public boolean isAllotted() {
		return allotted;
	}

	public void setAllotted(boolean allotted) {
		this.allotted = allotted;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

}
