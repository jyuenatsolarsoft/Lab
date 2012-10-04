package com.cms.gwt.fs.client.model.accessHours;

import com.cms.gwt.fs.client.model.FSModel;


/**
 * Contains the access hours for the service ticket on a particular day.
 *
 */
public class AccessDay extends FSModel {
    
	/**
	 * Monday, Tuesday, Wednesday.......
	 * 
	 */
	private String day;
	
	/**
	 * Time Range 1.
	 * 
	 * One of the two time ranges.
	 */
    private AccessTimeRange range1;
    
	/**
	 * Time Range 2.
	 * 
	 * One of the two time ranges.
	 */    
    private AccessTimeRange range2;
    
    @SuppressWarnings("unused")
	private AccessDay()
    {
    	//Disable this default constructor.
    }

    public AccessDay(String day)
    {
    	this.day = day;
    }
    
	public String getDay() {
		return day;
	}


	public AccessTimeRange getRange1() {
		return range1;
	}

	public void setRange1(AccessTimeRange range1) {
		this.range1 = range1;
	}

	public AccessTimeRange getRange2() {
		return range2;
	}

	public void setRange2(AccessTimeRange range2) {
		this.range2 = range2;
	}
    
    
}
