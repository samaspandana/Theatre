package com.theatre;

import java.util.ArrayList;
import java.util.List;

public class MovieTheater {

	public List<TheatreRow> rows = new ArrayList<TheatreRow> ();
	private int totalAvaliable = 0;
	
	
	public TheatreRow addAndReturnRow(int rowNumber){
		TheatreRow row = new TheatreRow(rowNumber,this);
		addRow(row);
		return row;
	}
	
	public void addRow(TheatreRow row){
		rows.add(row);
	}
	
	public ReturnResultWrapper canFit(int requestSeatNumbers){
		if(requestSeatNumbers > totalAvaliable){
			return new ReturnResultWrapper(ReturnEnum.CANNOT_FIT_ENTIRE);
		}
		//List<ReturnResultWrapper> returnResultWrappers = new ArrayList<ReturnResultWrapper>();
		ReturnResultWrapper finalResult = null;
		ReturnResultWrapper bestFitWrapper = null;
		ReturnResultWrapper canFitWrapper = null;
		ReturnResultWrapper fitWithSplitWrapper = null;
		int totalRows = rows.size();
		
		for(TheatreRow row : rows ){
			ReturnResultWrapper resultWrapper = row.canFit(requestSeatNumbers);
			
			if(resultWrapper != null && bestFitWrapper == null && resultWrapper.getReturnTypeEnum() ==  ReturnEnum.BEST_FIT){
				bestFitWrapper = resultWrapper;
			}else if(resultWrapper != null && canFitWrapper == null && resultWrapper.getReturnTypeEnum() ==  ReturnEnum.CAN_FIT){
				canFitWrapper = resultWrapper;
			}else if(resultWrapper != null && resultWrapper.getReturnTypeEnum() ==  ReturnEnum.FIT_WITH_SPLIT){
				fitWithSplitWrapper = resultWrapper;
			}
			
			if(bestFitWrapper != null && canFitWrapper == null){
				finalResult =  bestFitWrapper;
				break;
			}else if(canFitWrapper != null && bestFitWrapper != null && bestFitWrapper.getRow().getRowNumber() - canFitWrapper.getRow().getRowNumber()<=1){
				finalResult =  bestFitWrapper;
				break;
			}
			else if(canFitWrapper != null && bestFitWrapper == null && (row.getRowNumber()-canFitWrapper.getRow().getRowNumber()>1)){
				finalResult =  canFitWrapper;
				break;
			}
		}
		
		if(finalResult != null){
			return finalResult;
			
		}else if(fitWithSplitWrapper != null) {
			return new ReturnResultWrapper(ReturnEnum.FIT_WITH_SPLIT);
		}else{
			return new ReturnResultWrapper(ReturnEnum.CANNOT_FIT_ENTIRE);
		}
		
	}


	public int getTotalAvaliable() {
		return totalAvaliable;
	}


	public void setTotalAvaliable(int totalAvaliable) {
		this.totalAvaliable = totalAvaliable;
	}

	public List<TheatreRow> getRows() {
		return rows;
	}
}
