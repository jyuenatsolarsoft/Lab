package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.details.ServiceDetailListEntry;
import com.google.gwt.xml.client.Element;

public class TaskListEntryParser extends FSModelParser {
	
	private static TaskListEntryParser myself;
	
	final public static String XML_ELEMENT = "entry";	
	
    // XML Element Names   
    private static final String XML_ELEMENT_SEQUENCE = "sequence";
    private static final String XML_ELEMENT_FROM_PROCEDURE = "fromProcedure";
    private static final String XML_ELEMENT_DESCRIPTION1 = "descriptionLine1";
    private static final String XML_ELEMENT_MANPOWER = "manpower";
    private static final String XML_ELEMENT_TIME_ESTIMATE = "timeEstimate";
    private static final String XML_ELEMENT_WARRANTY_TASK = "warrantyTask";
    private static final String XML_ELEMENT_STATUS = "status";
    private static final String XML_ELEMENT_STATUS_DESCRIPTION = "statusDescription";
    
	
	public ServiceDetailListEntry parse(Element element) throws FSParseException
	{
		try
		{
			ServiceDetailListEntry result = new ServiceDetailListEntry();
					
			result.setSequence(getElementValue(element, XML_ELEMENT_SEQUENCE));
			result.setDescriptionLine1(getElementValue(element, XML_ELEMENT_DESCRIPTION1));
			result.setManpower(getElementValue(element, XML_ELEMENT_MANPOWER));
			result.setTimeEstimate(getElementValue(element, XML_ELEMENT_TIME_ESTIMATE));
			result.setWarrantyTask(determineBoolValue(getElementValue(element, XML_ELEMENT_WARRANTY_TASK)));
			result.setStatus(getElementValue(element, XML_ELEMENT_STATUS));
			result.setStatusDescription(getElementValue(element, XML_ELEMENT_STATUS_DESCRIPTION));
			result.setFromProcedure(getElementValue(element, XML_ELEMENT_FROM_PROCEDURE));
			
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}
	
	public static TaskListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new TaskListEntryParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
}