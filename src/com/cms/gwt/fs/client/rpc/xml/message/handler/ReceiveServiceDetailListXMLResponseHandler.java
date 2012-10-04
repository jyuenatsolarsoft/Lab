package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.details.ServiceDetailList;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailListResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.TaskListParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceDetailListXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {
	
	private final static String XML_ELEMENT_TICKET_NUMBER = "ticketNumber";
		
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{			
		try
		{
			Element taskListElement = getSingleElementByTagName(rootElement, TaskListParser.XML_ELEMENT);		
			
			ServiceDetailList detailList = TaskListParser.getInstance().parse(taskListElement);
			String ticketNumber = getElementValue(rootElement, XML_ELEMENT_TICKET_NUMBER);
			
			return new GetServiceDetailListResponse(ticketNumber, detailList);		 							
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
