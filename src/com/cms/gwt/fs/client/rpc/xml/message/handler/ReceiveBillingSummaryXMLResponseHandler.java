package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.billing.BillingSummary;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetBillingSummaryResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.cms.gwt.fs.client.rpc.xml.message.parser.BillingSummaryParser;
import com.google.gwt.xml.client.Element;

public class ReceiveBillingSummaryXMLResponseHandler  extends XmlResponseHandler implements ResponseHandler, Instantiable {
					
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException
	{				
		try
		{								
			Element billingSummaryElement = getSingleElementByTagName(rootElement, BillingSummaryParser.XML_ELEMENT);		
			
			BillingSummary summary = BillingSummaryParser.getInstance().parse(billingSummaryElement);		
							
			return new GetBillingSummaryResponse(summary); 
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

