package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.ServiceTicketList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketListParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceTicketListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	//final private String XML_RESPONSE_NAME = "receiveServiceTicketList";	
	
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{							
		try
		{
			Element ticketListElement = getSingleElementByTagName(rootElement, ServiceTicketListParser.XML_ELEMENT);		
			ServiceTicketList tickets = ServiceTicketListParser.parse(ticketListElement);		
			GetServiceTicketListResponse result = new GetServiceTicketListResponse(tickets);
						
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

}
