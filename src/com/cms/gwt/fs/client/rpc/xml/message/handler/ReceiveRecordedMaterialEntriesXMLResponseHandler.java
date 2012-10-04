package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialEntriesResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.RecordedMaterialEntriesParser;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveRecordedMaterialEntriesXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	//final static private String XML_ELEMENT_TOTAL_TIME_RECORDED = "totalTimeRecorded";
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element materialEntriesElement = getSingleElementByTagName(rootElement, RecordedMaterialEntriesParser.XML_ELEMENT);		
			RecordedMaterialEntries materialEntries = RecordedMaterialEntriesParser.getInstance().parse(materialEntriesElement);
			
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
					
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			int eventId = Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID));
			workHistory.setTicketNumber(ticketNumber);
			workHistory.setEventId(eventId);
			materialEntries.setTicketNumber(ticketNumber);
			materialEntries.setEventId(eventId);
	 				 
			return new GetRecordedMaterialEntriesResponse(materialEntries, workHistory);		
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
