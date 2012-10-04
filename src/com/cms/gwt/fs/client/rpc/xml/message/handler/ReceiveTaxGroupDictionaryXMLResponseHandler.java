package com.cms.gwt.fs.client.rpc.xml.message.handler;

import java.util.HashMap;
import java.util.Map;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.model.TaxGroupDictionary;
import com.cms.gwt.fs.client.reflection.Instantiable;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTaxGroupDictionaryResponse;
import com.cms.gwt.fs.client.rpc.message.handler.ResponseHandler;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class ReceiveTaxGroupDictionaryXMLResponseHandler  extends XmlResponseHandler implements ResponseHandler, Instantiable 
{
		
	final static private String XML_ELEMENT_RECEIVE_TAXCODES = "Receive_TaxCodes";
	
	final static private String XML_ELEMENT_DATA_SET = "DataSet";
	final static private String XML_ELEMENT_DATA_ROW = "DataRow";
	final static private String XML_ELEMENT_TAX_GROUP = "TaxGroup";
	final static private String XML_ELEMENT_TAX_GROUP_CODE = "TaxGroupCode";
	final static private String XML_ELEMENT_TAX_GROUP_NAME = "TaxGroupName";
	final static private String XML_ELEMENT_TAX_RATE = "TaxRate";
	final static private String XML_ELEMENT_RATE_CODE = "RateCode";
	final static private String XML_ELEMENT_RATE_NAME = "RateName";
			
	@Override
	protected ActionResponse parseResponse(Element rootElement) throws FSParseException 
	{ 		
		try
		{
			Element receiveTaxCodesElement = getSingleElementByTagName(rootElement, XML_ELEMENT_RECEIVE_TAXCODES);
			Element dataSetElement = getSingleElementByTagName(receiveTaxCodesElement, XML_ELEMENT_DATA_SET);
			
			NodeList dataRowNodeList = dataSetElement.getElementsByTagName(XML_ELEMENT_DATA_ROW);
			
			String taxGroupCode;		
			NodeList taxRateNodeList;
			Element taxRateElement;
			Map<String, String> taxRate;
					
			Map<String, String> taxGroupDescs = new HashMap<String, String>();		
			Map<String, CodeDictionary> taxRateDictionaries = new HashMap<String, CodeDictionary>();		
					
			// Extract the tax group codes, tax group descriptions, tax rate codes and tax rate descriptions from the XML. 
			for (int i=0; i<dataRowNodeList.getLength(); i++)
			{
				// Assuming each dataRow will have 1 taxgroup only.
				Element taxGroupElement = getSingleElementByTagName(((Element)dataRowNodeList.item(i)), XML_ELEMENT_TAX_GROUP);
				
				taxGroupCode = getElementValue(taxGroupElement, XML_ELEMENT_TAX_GROUP_CODE);
				
				taxGroupDescs.put(taxGroupCode,
					getElementValue(taxGroupElement, XML_ELEMENT_TAX_GROUP_NAME));
							 
				taxRate = new HashMap<String, String>();
									
				taxRateNodeList = taxGroupElement.getElementsByTagName(XML_ELEMENT_TAX_RATE);
				for (int j=0; j<taxRateNodeList.getLength(); j++)
				{
					taxRateElement = (Element)taxRateNodeList.item(j);
					
					taxRate.put(getElementValue(taxRateElement, XML_ELEMENT_RATE_CODE),
							getElementValue(taxRateElement, XML_ELEMENT_RATE_NAME));
				}
				
				taxRateDictionaries.put(taxGroupCode, new CodeDictionary(taxGroupCode, taxRate));
			}		
					
			return new GetTaxGroupDictionaryResponse(new TaxGroupDictionary(CodeDictionaryFactory.TAX_GROUP_CODE, taxGroupDescs, taxRateDictionaries));
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}			
	}	
		
}

