package com.cms.gwt.fs.client.model.accessHours;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;

/**
 * Contains the data on the Service Ticket Access Hours tab.
 *
 * Even though it's named <code>AccessHours</code>, it actually contains
 * days instead of hours.
 */
public class AccessHours extends FSModel {
	
	/** Key to retrieve the AccessDay stored in this class. */ 
    public static final String MONDAY = "monday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String TUESDAY = "tuesday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String WEDNESDAY = "wednesday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String THURSDAY = "thursday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String FRIDAY = "friday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String SATURDAY = "saturday";
    
    /** Key to retrieve the AccessDay stored in this class. */
    public static final String SUNDAY = "sunday";	
    
    /** Contains all keys to get the AccessDay(s). */
    public static final List<String> DAYS = Collections.unmodifiableList(
            Arrays.asList(new String[]{MONDAY, TUESDAY, WEDNESDAY, THURSDAY, 
            		FRIDAY, SATURDAY, SUNDAY}));
    
    private String timeZone;
    
    private Messages messages;

    /** Map key is the day string (See the public contants in this class) and map value is the corresponding AccessDay. */
    private Map<String, AccessDay> accessDays = new HashMap<String, AccessDay>();


    public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public void addAccessDay(String day, AccessDay accessday) {
		accessDays.put(day, accessday);
		
	}
	
	/**
	 * Return the AccessDay with the specified day.
	 * 
	 * Please refer to the public DAY constants.
	 * 
	 * @return AccessDay with the specified day.
	 */
	public AccessDay getAccessDay(String day) {
		
		return accessDays.get(day);
		
	}
	
	/**
	 * Return all access days. 
	 * 
	 * @return a Map containing the day as the key and the AccessDay as the corresponding
	 * Map value.
	 */
	public Map<String, AccessDay> getAccessDays()
	{
		return accessDays;
	}
	public Messages getMessages() {
		return messages;
	}
	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	
    
    
    

    
}
