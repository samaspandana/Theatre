package com.theatre;

import java.util.ArrayList;
import java.util.List;

public class TheatreRow {
	
	int rowNumber = -1;
	public List<TheatreSection> sections = new ArrayList<TheatreSection> ();
	private int totalAvaliable = 0;
	private MovieTheater theater;
	
	public TheatreRow(int rowNumber,MovieTheater theater){
		this.rowNumber = rowNumber;
		this.theater = theater;
	}
	
	public TheatreSection addAndReturnSection(int sectionNumber, int totalSeats){
		TheatreSection rowSection = new TheatreSection(sectionNumber,this);
		addSections(rowSection);
		rowSection.addSeats(totalSeats);
		return rowSection;
	}
	
	public void addSections(TheatreSection section){
		sections.add(section);
	}

	public int getTotalAvaliable() {
		return totalAvaliable;
	}

	public void setTotalAvaliable(int totalAvaliable) {
		this.totalAvaliable = totalAvaliable;
	}
	
	public ReturnResultWrapper canFit(int requestSeatNumbers){
		if(requestSeatNumbers > totalAvaliable){
			return new ReturnResultWrapper(ReturnEnum.CANNOT_FIT_ROW);
		}
		boolean alreadyset = false;
		ReturnResultWrapper returnResultWrapper = null;
		int tempTotalAvaliable = 0;
		for(TheatreSection section : sections){
			ReturnEnum returnTypeEnum = section.canFit(requestSeatNumbers);
			if(returnTypeEnum == ReturnEnum.BEST_FIT){
				return new ReturnResultWrapper(this,section,ReturnEnum.BEST_FIT);
			}
			if(returnTypeEnum == ReturnEnum.CAN_FIT && !alreadyset){
				returnResultWrapper = new ReturnResultWrapper(this,section,ReturnEnum.CAN_FIT);
				alreadyset = true;
			}
			tempTotalAvaliable = tempTotalAvaliable + section.getTotalAvaliable();
		}
		
		if(returnResultWrapper == null && tempTotalAvaliable == requestSeatNumbers){
			returnResultWrapper =  new ReturnResultWrapper(ReturnEnum.FIT_WITH_SPLIT);
		}
		
		return returnResultWrapper;
	}

	public int getRowNumber() {
		return rowNumber;
	}

	public MovieTheater getTheater() {
		return theater;
	}
	
 }
