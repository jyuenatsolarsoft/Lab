package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.scheduledEvent.ScheduledEventList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetScheduledEventListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ScheduleListParser;
import com.google.gwt.xml.client.Element;

public class ReceiveScheduledEventListXMLResponseHandler
 extends XmlResponseHandler implements ResponseHandler, Instantiable {
				
	private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element eventListElement = getSingleElementByTagName(rootElement, ScheduleListParser.XML_ELEMENT);		
			
			ScheduledEventList eventList = ScheduleListParser.getInstance().parse(eventListElement);		
			
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			
			return GetScheduledEventListResponse.newInstance(eventList, ticketNumber); 
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

