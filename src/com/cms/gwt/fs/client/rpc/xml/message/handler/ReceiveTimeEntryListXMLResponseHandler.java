package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTimeEntryListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.TimeEntryListParser;
import com.cms.gwt.fs.client.rpc.xml.message.parser.WorkHistoryParser;
import com.google.gwt.xml.client.Element;


public class ReceiveTimeEntryListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	final static private String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
	final static private String XML_ELEMENT_EVENT_ID = "eventID";
	
	private static final String XML_ELEMENT_TOTAL_HOURS_WORKED = "totalHoursWorked";	
	
	/**
	 * {@inheritDoc}
	 */
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{				
		try
		{
			Element timeEntryListElement = getSingleElementByTagName(rootElement, TimeEntryListParser.XML_ELEMENT);		
			TimeEntryList timeEntryList = TimeEntryListParser.getInstance().parse(timeEntryListElement);
			
			Element workHistoryElement = getSingleElementByTagName(rootElement, WorkHistoryParser.XML_ELEMENT);
			WorkHistory workHistory = WorkHistoryParser.getInstance().parse(workHistoryElement);
					
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			int eventId = Integer.parseInt(getElementValue(rootElement, XML_ELEMENT_EVENT_ID));
			workHistory.setTicketNumber(ticketNumber);
			workHistory.setEventId(eventId);
			timeEntryList.setTicketNumber(ticketNumber);
			timeEntryList.setEventId(eventId);
					
			String totalHoursWorked = getElementValue(rootElement, XML_ELEMENT_TOTAL_HOURS_WORKED);
			if (totalHoursWorked != null && !"".equals(totalHoursWorked.trim()))
			{
				double totalTime = Double.parseDouble(totalHoursWorked);
				workHistory.setTotalHoursWorked(totalTime);
			}
			 				 
			return new GetTimeEntryListResponse(timeEntryList, workHistory);		
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
