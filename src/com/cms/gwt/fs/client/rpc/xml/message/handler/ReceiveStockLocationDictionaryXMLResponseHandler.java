package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.HashMap;
import java.util.Map;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.model.StockLocationDictionary;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetStockLocationDictionaryResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ReceiveStockLocationDictionaryXMLResponseHandler extends XmlResponseHandler implements ResponseHandler, Instantiable 
{				
	final static private String XML_ELEMENT_DATA_SET = "DataSet";
	final static private String XML_ELEMENT_DATA_ROW = "DataRow";
	final static private String XML_ELEMENT_STOCK_LOCATION = "stockLocation";
	final static private String XML_ELEMENT_CODE = "code";
	final static private String XML_ELEMENT_DESCRIPTION = "description";
	final static private String XML_ELEMENT_BIN = "bin";
			
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{ 	
		try
		{
			Element dataSetElement = getSingleElementByTagName(rootElement, XML_ELEMENT_DATA_SET);
			
			NodeList dataRowNodeList = dataSetElement.getElementsByTagName(XML_ELEMENT_DATA_ROW);
			
			String stockLocationCode;		
			NodeList binLocationNodeList;
			Element binElement;
			Map<String, String> binLocationDescs;
					
			Map<String, String> stockLocationDescriptions = new HashMap<String, String>();		
			Map<String, CodeDictionary> binLocationDictionaries = new HashMap<String, CodeDictionary>();
			
			String binCode;
			String binDesc;
					
			// Extract the stock codes, stock descriptions, the corresponding bin codes and bin descriptions from the XML. 
			for (int i=0; i<dataRowNodeList.getLength(); i++)
			{
				// Assuming each dataRow will have 1 stock location only.
				Element stockLocationElement = getSingleElementByTagName(((Element)dataRowNodeList.item(i)), XML_ELEMENT_STOCK_LOCATION);
				
				stockLocationCode = getElementValue(stockLocationElement, XML_ELEMENT_CODE);
				
				stockLocationDescriptions.put(stockLocationCode,
					getElementValue(stockLocationElement, XML_ELEMENT_DESCRIPTION));
							 
				binLocationDescs = new HashMap<String, String>();
									
				binLocationNodeList = stockLocationElement.getElementsByTagName(XML_ELEMENT_BIN);
				for (int j=0; j<binLocationNodeList.getLength(); j++)
				{
					binElement = (Element)binLocationNodeList.item(j);
					
					binCode = getElementValue(binElement, XML_ELEMENT_CODE); 
					binDesc = getElementValue(binElement, XML_ELEMENT_DESCRIPTION);
					
					if (binCode != null)
					{
						binDesc = (binDesc == null || "".equals(binDesc.trim())) ? binCode + " [No Description Avail.]" : binDesc;
						binLocationDescs.put(binCode, binDesc);
					}
				}
				
				binLocationDictionaries.put(stockLocationCode, new CodeDictionary(stockLocationCode, binLocationDescs));
			}		
					
			return new GetStockLocationDictionaryResponse(new StockLocationDictionary(CodeDictionaryFactory.STOCK_LOCATION_CODE, stockLocationDescriptions, binLocationDictionaries));
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}		
	}	
		
}

