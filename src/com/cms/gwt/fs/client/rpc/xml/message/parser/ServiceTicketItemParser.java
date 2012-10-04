package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.item.ServiceTicketItem;
import com.google.gwt.xml.client.Element;

public class ServiceTicketItemParser extends FSModelParser 
{	 	
	static private ServiceTicketItemParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketItem";
	            	 		 
	 public ServiceTicketItem parse(Element element) throws FSParseException
	 {
		try
		{
		 	ServiceTicketItem item = new ServiceTicketItem();
		 	
		 	Element assetInfoElement = getSingleElementByTagName(element, AssetInformationParser.XML_ELEMENT);
		 	Element productionInfoElement = getSingleElementByTagName(element, ProductInformationParser.XML_ELEMENT);
		 	
		 	if (assetInfoElement != null)
		 	{
		 		item.setAsset(AssetInformationParser.getInstance().parse(assetInfoElement));
		 	}
		 	
		 	if (productionInfoElement != null)
		 	{
		 		item.setProductInformation(ProductInformationParser.getInstance().parse(productionInfoElement));
		 	}
		 	
		 	return item;
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

	 public static ServiceTicketItemParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new ServiceTicketItemParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }

	

}
