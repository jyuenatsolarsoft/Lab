package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class ScheduledEventParser extends FSModelParser 
{	 	
	static private ScheduledEventParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketScheduledEvent";
	
    // XML Element Names    
    private static final String XML_ELEMENT_SERVICE_TICKET_SCHEDULED_EVENT = "serviceTicketScheduledEvent";
    private static final String XML_ELEMENT_ENTRY = "entry";
    private static final String XML_ELEMENT_EVENT_ID = "eventID";
    private static final String XML_ELEMENT_TYPE = "type";
    private static final String XML_ELEMENT_TYPE_DESCRIPTION = "typeDescription";
    private static final String XML_ELEMENT_PARENT_EVENT_ID = "parentEventID";
    private static final String XML_ELEMENT_PARENT_SPLIT_TYPE = "parentSplitType";
    private static final String XML_ELEMENT_MANPOWER = "manpower";
    private static final String XML_ELEMENT_TIME_ESTIMATE = "timeEstimate";
    private static final String XML_ELEMENT_SPECIAL_EVENT_CODE = "specialEventCode";
    private static final String XML_ELEMENT_TECHNICIAN = "technician";
    private static final String XML_ELEMENT_TECHNICIAN_DESCRIPTION = "technicianDescription";
    private static final String XML_ELEMENT_SCHEDULED_START_DATE = "scheduledStartDate";
    private static final String XML_ELEMENT_SCHEDULED_START_TIME = "scheduledStartTime";
    private static final String XML_ELEMENT_SCHEDULEDTIME_ZONE = "scheduledTimeZone";
    private static final String XML_ELEMENT_TRAVEL_TIME_ESTIMATE = "travelTimeEstimate";
    private static final String XML_ELEMENT_SERVICE_CATEGORY = "serviceCategory";
    private static final String XML_ELEMENT_OVERRIDE_RATES = "overrideRates";
    private static final String XML_ELEMENT_LABOUR_RATE = "labourRate";
    private static final String XML_ELEMENT_OVERTIME_RATE = "overtimeRate";
    private static final String XML_ELEMENT_STATUS = "status";
    private static final String XML_ELEMENT_STATUS_DESCRIPTION = "statusDescription";
    private static final String XML_ELEMENT_MESSAGES = "Messages";	
	            	 	
	 public ScheduledEvent parse(Element element) throws FSParseException
	 {
		try
		{
		 	ScheduledEvent event = new ScheduledEvent();
	
		 	event.setEventId(parseInt(getElementValue(element, XML_ELEMENT_EVENT_ID)));
		 	event.setType(getElementValue(element,XML_ELEMENT_TYPE));
		 	event.setTypeDescription(
		 			getDesc(CodeDictionaryFactory.SCHEDULE_EVENT_TYPE, element, XML_ELEMENT_TYPE));
		 				 	
		 	event.setParentEventId(parseInt((getElementValue(element,XML_ELEMENT_PARENT_EVENT_ID))));
		 	event.setParentSplitType(getElementValue(element,XML_ELEMENT_PARENT_SPLIT_TYPE));
		 	event.setParentSplitTypeDescription(
		 			getDesc(CodeDictionaryFactory.PARENT_SPLIT_TYPE, element, XML_ELEMENT_PARENT_SPLIT_TYPE));
		 		 	
		 	event.setManpower(parseDouble(getElementValue(element,XML_ELEMENT_MANPOWER)));
		 	event.setTimeEstimate(parseDouble(getElementValue(element,XML_ELEMENT_TIME_ESTIMATE)));
		 	event.setSpecialEventCode(getElementValue(element,XML_ELEMENT_SPECIAL_EVENT_CODE));
		 	event.setTechnician(getElementValue(element,XML_ELEMENT_TECHNICIAN));
		 	
		 	event.setTechnicianDescription(
		 			getElementValue(element,XML_ELEMENT_TECHNICIAN));
		 			 			
		 	event.setScheduledStartDate(createSqlDate(getElementValue(element,XML_ELEMENT_SCHEDULED_START_DATE)));
		 	event.setScheduledStartTime(getElementValue(element,XML_ELEMENT_SCHEDULED_START_TIME));
		 	event.setTravelTimeEstimate(parseDouble(getElementValue(element,XML_ELEMENT_TRAVEL_TIME_ESTIMATE)));
		 	event.setServiceCategory(getElementValue(element,XML_ELEMENT_SERVICE_CATEGORY));
		 	event.setOverrideRates(determineOverrideRates(getElementValue(element,XML_ELEMENT_OVERRIDE_RATES)));
		 	event.setLabourRate(parseDouble(getElementValue(element,XML_ELEMENT_LABOUR_RATE)));
		 	event.setOvertimeRate(parseDouble(getElementValue(element,XML_ELEMENT_OVERTIME_RATE)));
		 	event.setStatus(getElementValue(element,XML_ELEMENT_STATUS));
	
		 	event.setMessages(MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));
		 	
		 	return event;
		}
		catch (FSParseException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}				
	 }	
	 
	 /**
	  * Helper method to determine whether the overrate rates is true or not.
	  * @param value The String value of the override rates.
	  * @return true if it's yes, false otherwise.
	  */
	 private boolean determineOverrideRates(String value)
	 {
		final String TRUE_VALUE = "1";
		final String FALSE_VALUE = "2";
		 
		if (value == null || FALSE_VALUE.equals(value))
		{
			return false;
		}
		else if (TRUE_VALUE.equals(value))
		{
			return true;
		}
		else 
		{
			// the value is something else.
			return false;
		}
	 }

	 public static ScheduledEventParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new ScheduledEventParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }


	 /**
	  * Serialize the ScheduledEvent.
	  * 
	  * @param event
	  * @return
	  */
	 public XmlRepresentable serialize(ScheduledEvent event)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		childElements.add(new SimpleElement(XML_ELEMENT_EVENT_ID, convertToStr(event.getEventId())));
		childElements.add(new SimpleElement(XML_ELEMENT_TYPE, event.getType()));
		childElements.add(new SimpleElement(XML_ELEMENT_PARENT_EVENT_ID, convertToStr(event.getParentEventId())));
		childElements.add(new SimpleElement(XML_ELEMENT_PARENT_SPLIT_TYPE, event.getParentSplitType()));
		childElements.add(new SimpleElement(XML_ELEMENT_MANPOWER, convertToStr(event.getManpower())));
		childElements.add(new SimpleElement(XML_ELEMENT_TIME_ESTIMATE, convertToStr(event.getTimeEstimate())));
		childElements.add(new SimpleElement(XML_ELEMENT_SPECIAL_EVENT_CODE, event.getSpecialEventCode()));
		childElements.add(new SimpleElement(XML_ELEMENT_TECHNICIAN, event.getTechnician()));
		childElements.add(new SimpleElement(XML_ELEMENT_SCHEDULED_START_DATE, convertToStr(event.getScheduledStartDate())));
		childElements.add(new SimpleElement(XML_ELEMENT_SCHEDULED_START_TIME, event.getScheduledStartTime()));
		childElements.add(new SimpleElement(XML_ELEMENT_TRAVEL_TIME_ESTIMATE, convertToStr(event.getTravelTimeEstimate())));
		childElements.add(new SimpleElement(XML_ELEMENT_SERVICE_CATEGORY, event.getServiceCategory()));
		childElements.add(new SimpleElement(XML_ELEMENT_OVERRIDE_RATES, convertToStr(event.isOverrideRates())));
		childElements.add(new SimpleElement(XML_ELEMENT_LABOUR_RATE, convertToStr(event.getLabourRate())));
		childElements.add(new SimpleElement(XML_ELEMENT_OVERTIME_RATE, convertToStr(event.getOvertimeRate())));
		childElements.add(new SimpleElement(XML_ELEMENT_STATUS, event.getStatus()));
		
		return new ComplexElement(XML_ELEMENT, childElements);		
	 }
}
