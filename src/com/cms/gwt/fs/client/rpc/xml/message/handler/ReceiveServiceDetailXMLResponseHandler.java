package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.details.ServiceDetail;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.ServiceTicketTaskParser;
import com.google.gwt.xml.client.Element;

public class ReceiveServiceDetailXMLResponseHandler 
	 extends XmlResponseHandler implements ResponseHandler, Instantiable {
			
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{
		try
		{
			Element taskElement = getSingleElementByTagName(rootElement, ServiceTicketTaskParser.XML_ELEMENT);		
			ServiceDetail detail = ServiceTicketTaskParser.getInstance().parse(taskElement);
	
			return new GetServiceDetailResponse(detail);
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
