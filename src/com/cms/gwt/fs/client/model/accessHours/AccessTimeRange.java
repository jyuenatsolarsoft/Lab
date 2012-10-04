package com.cms.gwt.fs.client.model.accessHours;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Contains the start and end time of a time range for the service ticket.
 *
 */
public class AccessTimeRange extends FSModel {
	
	/**
	 * Start time of the time range.
	 * 
	 */
    private String startTime;
    
    /**
     * Start time of the end range.
     * 
     */
    private String endTime;

    @SuppressWarnings("unused")
	private AccessTimeRange()
    {
    	// Disable default constructor.
    }
            
    protected AccessTimeRange(String startTime, String endTime)
    {
    	this.startTime = startTime;
    	this.endTime = endTime;
    }

	public String getStartTime() {
		return startTime;
	}


	public String getEndTime() {
		return endTime;
	}

	
	public static AccessTimeRange getInstance(String startTime, String endTime)
	{
		// preset the value if the input is null or empty.
		if (startTime == null || "".equals(startTime.trim()))
		{
			startTime = "00.00.00";
		}
		
		if (endTime == null || "".equals(endTime.trim()))
		{
			endTime = "00.00.00";
		}
		
		return new AccessTimeRange(startTime, endTime);
	}
    
    
}
