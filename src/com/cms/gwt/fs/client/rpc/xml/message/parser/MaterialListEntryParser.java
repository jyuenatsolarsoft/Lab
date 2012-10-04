package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.ServiceDetailMaterial;
import com.google.gwt.xml.client.Element;

public class MaterialListEntryParser extends FSModelParser 
{ 
	private static MaterialListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";

	private static final String XML_ELEMENT_LINE_NUMBER = "lineNumber";
	private static final String XML_ELEMENT_PART = "part";
	private static final String XML_ELEMENT_PART_DESCRIPTION = "partDescription";
	private static final String XML_ELEMENT_QUANTITY_REQUIRED = "quantityRequired";
	private static final String XML_ELEMENT_QUANTITY_REQUIRED_UOM = "quantityRequiredUOM";
	private static final String XML_ELEMENT_PROBABILITY = "probability";
	private static final String XML_ELEMENT_USED = "used";
	
	//private static final String XML_ELEMENT_MESSAGES = "Messages";


	public ServiceDetailMaterial parse(Element element) throws FSParseException
	{
		try
		{
			ServiceDetailMaterial result = new ServiceDetailMaterial();
			
			result.setLineNumber(parseInt(getElementValue(element, XML_ELEMENT_LINE_NUMBER)));
			result.setPart(getElementValue(element, XML_ELEMENT_PART));
			result.setPartDescription(getElementValue(element, XML_ELEMENT_PART_DESCRIPTION));
			result.setQuantityRequired(parseDouble(getElementValue(element, XML_ELEMENT_QUANTITY_REQUIRED)));
			result.setQuantityRequiredUOM(getElementValue(element, XML_ELEMENT_QUANTITY_REQUIRED_UOM));
			result.setProbability(parseInt(getElementValue(element, XML_ELEMENT_PROBABILITY)));
			result.setQuantityUsed(parseDouble(getElementValue(element, XML_ELEMENT_USED)));
			
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}		
	}
	
	public static MaterialListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new MaterialListEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
}
