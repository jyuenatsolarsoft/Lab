package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetWorkHistoryResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveWorkHistoryXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{			
		try
		{
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);		
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
			
			workHistory.setTicketNumber(getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER));				
			workHistory.setEventId(Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID)));
			
			return constructResponse(workHistory);
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
	protected ActionResponse constructResponse(WorkHistory workHistory)
	{
		return new GetWorkHistoryResponse(workHistory);
	}
}
