package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventListEntry;
import com.google.gwt.xml.client.Element;

public class ScheduleListEntryParser  extends FSModelParser 
{ 
	private static ScheduleListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";

    // XML Element Names
    private static final String XML_ELEMENT_EVENT_ID = "eventID";
    private static final String XML_ELEMENT_TYPE = "type";
    private static final String XML_ELEMENT_TYPE_DESCRIPTION = "typeDescription";
    private static final String XML_ELEMENT_MANPOWER = "manpower";
    private static final String XML_ELEMENT_TIME_ESTIMATE = "timeEstimate";
    private static final String XML_ELEMENT_TECHNICIAN = "technician";
    private static final String XML_ELEMENT_TECHNICIAN_DESCRIPTION = "technicianDescription";
    private static final String XML_ELEMENT_SCHEDULED_START_DATE = "scheduledStartDate";
    private static final String XML_ELEMENT_SCHEDULED_START_TIME = "scheduledStartTime";
    private static final String XML_ELEMENT_TIME_ZONE = "scheduledTimeZone";
    private static final String XML_ELEMENT_STATUS = "status";
    private static final String XML_ELEMENT_STATUS_DESCRIPTION = "statusDescription";
    //private static final String XML_ELEMENT_MESSAGES = "Messages";

	
	public ScheduledEventListEntry parse(Element element) throws FSParseException
	{
		try
		{
			ScheduledEventListEntry result = new ScheduledEventListEntry();
			
			result.setEventId(parseInt(getElementValue(element, XML_ELEMENT_EVENT_ID)));
			result.setType(getElementValue(element, XML_ELEMENT_TYPE));
			result.setTypeDescription(getElementValue(element, XML_ELEMENT_TYPE_DESCRIPTION));
			result.setManpower(parseDouble(getElementValue(element, XML_ELEMENT_MANPOWER)));
			result.setTimeEstimate(parseDouble(getElementValue(element, XML_ELEMENT_TIME_ESTIMATE)));
			result.setTechnician(getElementValue(element, XML_ELEMENT_TECHNICIAN));
			result.setTechnicianDescription(getElementValue(element, XML_ELEMENT_TECHNICIAN_DESCRIPTION));
			result.setScheduledStartDate(createSqlDate(getElementValue(element, XML_ELEMENT_SCHEDULED_START_DATE)));
			result.setScheduledStartTime(getElementValue(element, XML_ELEMENT_SCHEDULED_START_TIME));
			result.setTimeZone(getElementValue(element, XML_ELEMENT_TIME_ZONE));
			result.setStatus(getElementValue(element, XML_ELEMENT_STATUS));
			result.setStatusDescription(getElementValue(element, XML_ELEMENT_STATUS_DESCRIPTION));						
			
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}
	
	public static ScheduleListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new ScheduleListEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
}
