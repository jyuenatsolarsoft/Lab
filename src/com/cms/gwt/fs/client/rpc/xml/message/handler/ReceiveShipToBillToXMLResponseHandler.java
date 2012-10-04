package com.cms.gwt.fs.client.rpc.xml.message.handler;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetShipToBillToResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.google.gwt.xml.client.Element;

public class ReceiveShipToBillToXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
		
	final static private String XML_ELEMENT_RECEIVE_SHIP_TO_BILL_TO = "Receive_ShipToBillTo";
	
	final static private String XML_ELEMENT_DATA_SET = "DataSet";
	final static private String XML_ELEMENT_DATA_ROW = "DataRow";
	
	final static private String XML_ELEMENT_CUSTOMER_NAME = "CustomerName";
			
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{ 		
		try
		{
			Element receiveShipToBillToElement = getSingleElementByTagName(rootElement, XML_ELEMENT_RECEIVE_SHIP_TO_BILL_TO);
	
			// Expect only 1 dataset.
			Element dataSetElement = getSingleElementByTagName(receiveShipToBillToElement, XML_ELEMENT_DATA_SET);		
			// Expect only 1 data row.
			Element dataRowElement = getSingleElementByTagName(dataSetElement, XML_ELEMENT_DATA_ROW);
							
			String customerName = getElementValue(dataRowElement, XML_ELEMENT_CUSTOMER_NAME);
													
			return new GetShipToBillToResponse(customerName);
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}	
		
}

