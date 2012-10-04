package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.location.ServiceTicketLocation;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketLocationResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketLocationParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceTicketLocationXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {

	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element locationElement = getSingleElementByTagName(rootElement, ServiceTicketLocationParser.XML_ELEMENT);		
			ServiceTicketLocation location = ServiceTicketLocationParser.getInstance().parse(locationElement);		
	
			return new GetServiceTicketLocationResponse(location);
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
