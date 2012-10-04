package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianScheduledEvent;
import com.google.gwt.xml.client.Element;

public class TechnicianScheduledEventParser extends FSModelParser {
	
	private static TechnicianScheduledEventParser myself;
	
	final public static  String XML_ELEMENT = "scheduledEvent";
	
	final private static String XML_ELEMENT_EVENT_ID = "eventID";
	final private static String XML_ELEMENT_CUSTOMER_CODE = "customerCode";
	final private static String XML_ELEMENT_CUSTOMER_NAME = "customerName";
	final private static String XML_ELEMENT_SCHEDULED_START_DATE = "scheduledStartDate";
	final private static String XML_ELEMENT_SCHEDULED_START_TIME = "scheduledStartTime";
	final private static String XML_ELEMENT_TYPE_DESCRIPTION = "typeDescription";
	final private static String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final private static String XML_ELEMENT_TIME_ESTIMATE = "timeEstimate";
	final private static String XML_ELEMENT_DESCRIPTION = "description";
	final private static String XML_ELEMENT_STATUS_DESCRIPTION = "statusDescription";
	    
	
	public TechnicianScheduledEvent parse(Element element) throws FSParseException
	{
		try
		{
			TechnicianScheduledEvent result = new TechnicianScheduledEvent();
					
			result.setEventID(parseInt(getElementValue(element, XML_ELEMENT_EVENT_ID)));
			result.setCustomerCode(getElementValue(element, XML_ELEMENT_CUSTOMER_CODE));
			result.setCustomerName(getElementValue(element, XML_ELEMENT_CUSTOMER_NAME));
			result.setScheduledStartDate(createSqlDate(getElementValue(element, XML_ELEMENT_SCHEDULED_START_DATE)));
			result.setScheduledStartTime(getElementValue(element, XML_ELEMENT_SCHEDULED_START_TIME));
			result.setTypeDescription(getElementValue(element, XML_ELEMENT_TYPE_DESCRIPTION));
			result.setTicketNumber(getElementValue(element, XML_ELEMENT_TICKET_NUMBER));
			result.setTimeEstimate(getElementValue(element, XML_ELEMENT_TIME_ESTIMATE));
			result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			result.setStatusDescription(getElementValue(element, XML_ELEMENT_STATUS_DESCRIPTION));
			
			Element addressDetailsElement = getSingleElementByTagName(element, AddressDetailsParser.XML_ELEMENT);
			
			if (addressDetailsElement != null)
			{
				result.setAddressDetails(AddressDetailsParser.getInstance().parse(addressDetailsElement));
			}
			
			return result;
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
	
	public static TechnicianScheduledEventParser getInstance()
	{
		if (myself == null)
		{
			myself = new TechnicianScheduledEventParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
	
}
