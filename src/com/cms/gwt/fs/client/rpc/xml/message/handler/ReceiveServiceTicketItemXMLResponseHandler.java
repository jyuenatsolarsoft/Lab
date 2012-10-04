package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.item.ServiceTicketItem;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketItemResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketItemParser;
import com.google.gwt.xml.client.Element;
             
public class ReceiveServiceTicketItemXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{		
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element itemElement = getSingleElementByTagName(rootElement, ServiceTicketItemParser.XML_ELEMENT);		
					
			ServiceTicketItem item = ServiceTicketItemParser.getInstance().parse(itemElement);		
			
			return new GetServiceTicketItemResponse(item);	
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
