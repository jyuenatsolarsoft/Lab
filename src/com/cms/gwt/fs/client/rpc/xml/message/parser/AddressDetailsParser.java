package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.AddressDetails;
import com.google.gwt.xml.client.Element;

public class AddressDetailsParser extends FSModelParser {
	
	private static AddressDetailsParser myself;
	
	final public static String XML_ELEMENT = "addressDetails";
	
	final private static String XML_ELEMENT_ADDRESS = "Address";
	final private static String XML_ELEMENT_CITY = "City";
	final private static String XML_ELEMENT_PROVINCE_STATE = "ProvinceState";
	final private static String XML_ELEMENT_POSTAL_CODE = "PostalCode";
	final private static String XML_ELEMENT_COUNTRY = "Country";
	
	final private static int NUM_OF_ADDRESS_LINES = 10;
		    
	
	public AddressDetails parse(Element element) throws FSParseException
	{
		try
		{
			AddressDetails result = new AddressDetails();
			
			String[] addressLines = new String[10];
			for (int i=0; i<NUM_OF_ADDRESS_LINES; i++)
			{
				addressLines[i] = getElementValue(element, XML_ELEMENT_ADDRESS + i);
			}
	
			result.setAddress(addressLines);
			result.setCity(getElementValue(element, XML_ELEMENT_CITY));
			result.setProvinceState(getElementValue(element, XML_ELEMENT_PROVINCE_STATE));
			result.setPostalCode(getElementValue(element, XML_ELEMENT_POSTAL_CODE));
			result.setCountry(getElementValue(element, XML_ELEMENT_COUNTRY));
						
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}
	
	public static AddressDetailsParser getInstance()
	{
		if (myself == null)
		{
			myself = new AddressDetailsParser();
		}
		
		return myself;
	}

	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
	
}
