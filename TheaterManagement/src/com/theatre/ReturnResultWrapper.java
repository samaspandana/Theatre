package com.theatre;

public class ReturnResultWrapper {

	public ReturnEnum returnEnum;
	public TheatreRow row;
	public TheatreSection section;
	
	public ReturnResultWrapper(){
		
	}
	
    public ReturnResultWrapper(ReturnEnum returnTypeEnum){
		this.returnEnum = returnTypeEnum;
	}
    
    public ReturnResultWrapper(TheatreRow row,TheatreSection section,ReturnEnum returnTypeEnum){
		this.returnEnum = returnTypeEnum;
		this.row = row;
		this.section = section;
	}

	public ReturnEnum getReturnTypeEnum() {
		return returnEnum;
	}
	public void setReturnTypeEnum(ReturnEnum returnEnum) {
		this.returnEnum = returnEnum;
	}
	public TheatreRow getRow() {
		return row;
	}
	public void setRow(TheatreRow row) {
		this.row = row;
	}
	public TheatreSection getSection() {
		return section;
	}
	public void setSection(TheatreSection section) {
		this.section = section;
	}
	
}
