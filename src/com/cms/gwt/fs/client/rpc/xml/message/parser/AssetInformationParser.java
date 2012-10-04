package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.item.AssetInformation;
import com.google.gwt.xml.client.Element;

public class AssetInformationParser extends FSModelParser 
{
	static private AssetInformationParser myself;
	
	public static final String XML_ELEMENT = "assetInformation";
	        
    private static final String XML_ELEMENT_TYPE = "assetType";    
    private static final String XML_ELEMENT_NUMBER = "assetNumber";
    private static final String XML_ELEMENT_DESCRIPTION = "assetDescription";
    private static final String XML_ELEMENT_LINE1 = "line1";
    private static final String XML_ELEMENT_LINE2 = "line2";
    private static final String XML_ELEMENT_LINE3 = "line3";

	            	 	
	 public AssetInformation parse(Element element) throws FSParseException
	 {
		try
		{
		 	AssetInformation result = new AssetInformation();
	
		 	result.setType(getElementValue(element, XML_ELEMENT_TYPE));
		 	result.setAssetNumber(getElementValue(element, XML_ELEMENT_NUMBER));
		 	
		 	Element assetDescElement = getSingleElementByTagName(element, XML_ELEMENT_DESCRIPTION);	 	
		 		 	
		 	result.setDescription1(getElementValue(assetDescElement, XML_ELEMENT_LINE1));
		 	result.setDescription2(getElementValue(assetDescElement, XML_ELEMENT_LINE2));
		 	result.setDescription3(getElementValue(assetDescElement, XML_ELEMENT_LINE3));
		 
		 	return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}		
	 }	

	 public static AssetInformationParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new AssetInformationParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }
}
