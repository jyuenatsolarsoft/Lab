package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.location.ServiceTicketLocation;
import com.google.gwt.xml.client.Element;

public class ServiceTicketLocationParser extends FSModelParser 
{
	
	static private ServiceTicketLocationParser myself;

	public static final String XML_ELEMENT = "serviceTicketLocation";

    // XML Element Names        
    
    private static final String XML_ELEMENT_CUSTOMER_ID = "customer";
    private static final String XML_ELEMENT_CUSTOMER_SITE = "customerSite";
    private static final String XML_ELEMENT_IN_HOUSE_PLANT = "inHousePlant";
    private static final String XML_ELEMENT_TERRITORY = "territory";
    private static final String XML_ELEMENT_SITE_LINE1 = "siteLine1";
    private static final String XML_ELEMENT_SITE_LINE2 = "siteLine2";
    private static final String XML_ELEMENT_SITE_LINE3 = "siteLine3";
    private static final String XML_ELEMENT_SITE_LINE4 = "siteLine4";
    private static final String XML_ELEMENT_SITE_LINE5 = "siteLine5";
    private static final String XML_ELEMENT_SITE_LINE6 = "siteLine6";
    private static final String XML_ELEMENT_SITE_LINE7 = "siteLine7";
    private static final String XML_ELEMENT_ADDRESS_LINE1 = "addressLine1";
    private static final String XML_ELEMENT_ADDRESS_LINE2 = "addressLine2";
    private static final String XML_ELEMENT_ADDRESS_LINE3 = "addressLine3";
    private static final String XML_ELEMENT_ADDRESS_LINE4 = "addressLine4";
    private static final String XML_ELEMENT_ADDRESS_LINE5 = "addressLine5";
    private static final String XML_ELEMENT_ADDRESS_LINE6 = "addressLine6";    

	
	public ServiceTicketLocation parse(Element element) throws FSParseException
	{
		try
		{
			ServiceTicketLocation location = new ServiceTicketLocation();
			
			location.setCustomer(getElementValue(element, XML_ELEMENT_CUSTOMER_ID));
			location.setInHousePlant(getElementValue(element, XML_ELEMENT_IN_HOUSE_PLANT));
			location.getAddressLine()[0] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE1);
			location.getAddressLine()[1] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE2);
			location.getAddressLine()[2] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE3);
			location.getAddressLine()[3] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE4);
			location.getAddressLine()[4] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE5);
			location.getAddressLine()[5] = getElementValue(element, XML_ELEMENT_ADDRESS_LINE6);		
			
			location.setCustomerSite(getElementValue(element, XML_ELEMENT_CUSTOMER_SITE));
			location.setTerritory(getElementValue(element, XML_ELEMENT_TERRITORY));
			
			location.getSiteLine()[0] = getElementValue(element, XML_ELEMENT_SITE_LINE1);
			location.getSiteLine()[1] = getElementValue(element, XML_ELEMENT_SITE_LINE2);
			location.getSiteLine()[2] = getElementValue(element, XML_ELEMENT_SITE_LINE3);
			location.getSiteLine()[3] = getElementValue(element, XML_ELEMENT_SITE_LINE4);	                       
			location.getSiteLine()[4] = getElementValue(element, XML_ELEMENT_SITE_LINE5);
			location.getSiteLine()[5] = getElementValue(element, XML_ELEMENT_SITE_LINE6);
			location.getSiteLine()[6] = getElementValue(element, XML_ELEMENT_SITE_LINE7);	
		    	    				
		    location.setMessages(MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));
		    
			return location;
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
	
	public static ServiceTicketLocationParser getInstance()
	{
		if (myself == null)
		{
			myself = new ServiceTicketLocationParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() {
	
		return XML_ELEMENT;
	}

}

