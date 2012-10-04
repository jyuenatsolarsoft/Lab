package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedMaterialEntryParser;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveRecordedMaterialXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
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
			Element materialEntryElement = getSingleElementByTagName(rootElement, RecordedMaterialEntryParser.XML_ELEMENT);		
			RecordedMaterial material = RecordedMaterialEntryParser.getInstance().parse(materialEntryElement);
			
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
					
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			int eventId = Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID));
			workHistory.setTicketNumber(ticketNumber);
			material.setTicketNumber(ticketNumber);
			workHistory.setEventId(eventId);
			material.setEventId(eventId);
			 				 
			return constructResponse(material, workHistory);
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
	 * @param material The event received in the response
	 * @return The response.
	 */
	protected ActionResponse constructResponse(RecordedMaterial material, WorkHistory workHistory)
	{
		return new GetRecordedMaterialResponse(material, workHistory);
	}
	
	
}
