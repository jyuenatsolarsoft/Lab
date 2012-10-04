package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetBillingDetailResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.BillingDetailParser;
import com.google.gwt.xml.client.Element;

public class ReceiveBillingDetailXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{					
		try
		{		
			Element billingDetailElement = getSingleElementByTagName(rootElement, BillingDetailParser.XML_ELEMENT);		
			BillingDetail detail = BillingDetailParser.getInstance().parse(billingDetailElement);
			
			// Note polymorphism is used here.
			return constructResponse(detail);
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
	protected ActionResponse constructResponse(BillingDetail detail)
	{
		return new GetBillingDetailResponse(detail);
	}
}
