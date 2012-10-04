package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.ServiceDetailMaterialList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailMaterialListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.MaterialListParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceDetailMaterialListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {
		
	private String XML_ELEMENT_SEQUENCE = "sequence";
	
	private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element materialListElement = getSingleElementByTagName(rootElement, MaterialListParser.XML_ELEMENT);		
			
			ServiceDetailMaterialList list = MaterialListParser.getInstance().parse(materialListElement);		
			
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			
			return GetServiceDetailMaterialListResponse.newInstance(list, ticketNumber); 
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
}
