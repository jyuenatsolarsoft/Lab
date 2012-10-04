package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.ServiceTicketListEntry;
import com.google.gwt.xml.client.Element;

public class ServiceTicketListEntryParser extends FSModelParser {
	
	private static ServiceTicketListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";
	
	final private static String XML_ELEMENT_TICKETNUMBER = "ticketNumber";
	final private static String XML_ELEMENT_TECHNICIAN = "technician";
	final private static String XML_ELEMENT_STATUS = "status";
	final private static String XML_ELEMENT_OPEN_DATE = "openDate";
	final private static String XML_ELEMENT_CUSTOMERID = "customerID";
	final private static String XML_ELEMENT_SUBJECT = "subject";
	
	public ServiceTicketListEntry parse(Element element) throws FSParseException
	{
		try
		{
			ServiceTicketListEntry result = new ServiceTicketListEntry();
			
			result.setCustomerId("stub");
			result.setSubject(getElementValue(element, XML_ELEMENT_SUBJECT));
			result.setTechnician(getElementValue(element, XML_ELEMENT_TECHNICIAN));
			result.setTicketNumber(getElementValue(element, XML_ELEMENT_TICKETNUMBER));
			result.setStatus(getElementValue(element, XML_ELEMENT_STATUS));
			
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
		
	}
	
	public static ServiceTicketListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new ServiceTicketListEntryParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
	
}
