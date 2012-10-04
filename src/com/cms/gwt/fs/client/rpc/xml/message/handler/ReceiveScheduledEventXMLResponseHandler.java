package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEvent;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetScheduledEventResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ScheduledEventParser;
import com.google.gwt.xml.client.Element;

public class ReceiveScheduledEventXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element scheduledEventElement = getSingleElementByTagName(rootElement, ScheduledEventParser.XML_ELEMENT);		
			ScheduledEvent event = ScheduledEventParser.getInstance().parse(scheduledEventElement);
			
			event.setTicketNumber(getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER));
			
			// Note polymorphism is used here.
			return constructResponse(event);		
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
	
	/**
	 * Construct the ActionResponse.
	 * 
	 * @param event The event received in the response of the backend.
	 * 
	 * @return An Action response containing the received event.
	 */
	protected ActionResponse constructResponse(ScheduledEvent event)
	{
		return new GetScheduledEventResponse(event);
	}
}
