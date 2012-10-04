package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.accessHours.AccessDay;
import com.cms.gwt.fs.client.model.accessHours.AccessTimeRange;
import com.google.gwt.xml.client.Element;

public class AccessDayParser extends FSModelParser {

	private static AccessDayParser myself;
	
    private static final String XML_ELEMENT_START1 = "start1";
    private static final String XML_ELEMENT_END1 = "end1";
    private static final String XML_ELEMENT_START2 = "start2";
    private static final String XML_ELEMENT_END2 = "end2";	
	
    public AccessDay parse(Element element) throws FSParseException
    {
    	try
    	{
	    	AccessDay result = new AccessDay(element.getTagName());
	    	    	    	
	    	result.setRange1(AccessTimeRange.getInstance(
	    		getElementValue(element, XML_ELEMENT_START1),
	    		getElementValue(element, XML_ELEMENT_END1)));
	
	    	result.setRange2(AccessTimeRange.getInstance(
	        		getElementValue(element, XML_ELEMENT_START2),
	        		getElementValue(element, XML_ELEMENT_END2)));
	    	    	    	    	    	    	
	    	return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
    	
    }
    
    
    public static AccessDayParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new AccessDayParser();    
    	}
    	
    	return myself;
    }
    
    private AccessDayParser()
    {
    	// Disable default constructor.
    }
    
	@Override
	public String getXmlElement() {
		// Do not have an XmlElement since this could be monday, tuesday, wednesday.....
		return null;
	}
	

}
