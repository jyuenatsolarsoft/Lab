package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.ServiceTicket;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceTicketXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	//final private String XML_RESPONSE_NAME = "receiveServiceTicket";
	 
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{
		try
		{
			Element ticketElement = getSingleElementByTagName(rootElement, ServiceTicketParser.XML_ELEMENT);		
			ServiceTicket ticket = ServiceTicketParser.getInstance().parse(ticketElement);		
			
			return new GetServiceTicketResponse(ticket); 									
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
