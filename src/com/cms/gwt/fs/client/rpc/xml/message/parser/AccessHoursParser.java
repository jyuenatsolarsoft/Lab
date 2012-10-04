package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.accessHours.AccessDay;
import com.cms.gwt.fs.client.model.accessHours.AccessHours;
import com.google.gwt.xml.client.Element;

public class AccessHoursParser extends FSModelParser {

	private static AccessHoursParser myself;
	
    public static final String XML_ELEMENT = "accessHours";
           
    private static final String XML_ELEMENT_MONDAY = AccessHours.MONDAY;
    private static final String XML_ELEMENT_TUESDAY = AccessHours.TUESDAY;
    private static final String XML_ELEMENT_WEDNESDAY = AccessHours.WEDNESDAY;
    private static final String XML_ELEMENT_THURSDAY = AccessHours.THURSDAY;
    private static final String XML_ELEMENT_FRIDAY = AccessHours.FRIDAY;
    private static final String XML_ELEMENT_SATURDAY = AccessHours.SATURDAY;
    private static final String XML_ELEMENT_SUNDAY = AccessHours.SUNDAY;
    
    private static final String XML_ELEMENT_TIME_ZONE = "timeZone";
            
    private static final List<String> XML_ELEMENT_DAYS = Collections.unmodifiableList(
            Arrays.asList(new String[]{XML_ELEMENT_MONDAY, XML_ELEMENT_TUESDAY,
            		XML_ELEMENT_WEDNESDAY, XML_ELEMENT_THURSDAY, XML_ELEMENT_FRIDAY,
            		XML_ELEMENT_SATURDAY, XML_ELEMENT_SUNDAY}));


    public AccessHours parse(Element element) throws FSParseException
    {    	
    	try
    	{
	    	AccessHours result = new AccessHours();
	    	
	    	result.setTimeZone(getElementValue(element, XML_ELEMENT_TIME_ZONE));
	    	
	    	for (String xml_element_day : XML_ELEMENT_DAYS)
	    	{
	    		AccessDay accessDay = AccessDayParser.getInstance().parse(getSingleElementByTagName(element, xml_element_day));
	    		result.addAccessDay(accessDay.getDay(), accessDay);
	    	}
	    	
	    	result.setMessages(MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));
	    	    	    	
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
	
    private AccessHoursParser()
    {
    	// Disable default constructor.
    }
	
    public static AccessHoursParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new AccessHoursParser(); 
    	}
    	
    	return myself;
    }
    
	@Override
	public String getXmlElement() {
		return XML_ELEMENT;
	}

}
