package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.accessHours.AccessHours;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetAccessHoursResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.AccessHoursParser;
import com.google.gwt.xml.client.Element;

public class ReceiveAccessHoursXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable {
	
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{		
		try
		{
			Element accessHoursElement = getSingleElementByTagName(rootElement, AccessHoursParser.XML_ELEMENT);		
			AccessHours accessHours = AccessHoursParser.getInstance().parse(accessHoursElement);
			
			return new GetAccessHoursResponse(accessHours);		 								

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