package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.billing.BillingTotals;
import com.google.gwt.xml.client.Element;

public class BillingTotalsParser extends FSModelParser {

	private static BillingTotalsParser myself;
	
    public static final String XML_ELEMENT = "billingTotals";
    
    public static final String XML_ELEMENT_COMPLETED = "completed";
    public static final String XML_ELEMENT_UNCOMPLETED = "uncompleted";
    public static final String XML_ELEMENT_LABOUR = "labour";
    public static final String XML_ELEMENT_PARTS = "parts";
    public static final String XML_ELEMENT_MISC = "miscellaneous";
    public static final String XML_ELEMENT_SUBTOTAL = "subtotal";
    public static final String XML_ELEMENT_TAXES = "taxes";
    public static final String XML_ELEMENT_TO_PAY = "toPay";
    public static final String XML_ELEMENT_TO_PAY_AND_ESTIMATE = "toPayAndEstimate";    
   
	
    public BillingTotals parse(Element element) throws FSParseException
    {    	    	    	
    	try
    	{
	    	BillingTotals result = new BillingTotals();
	    	
	    	Element completedElement = getSingleElementByTagName(element, XML_ELEMENT_COMPLETED);
	    	Element uncompletedElement = getSingleElementByTagName(element, XML_ELEMENT_UNCOMPLETED);
	    	
	    	result.setCompletedLabour(parseDouble(getElementValue(completedElement, XML_ELEMENT_LABOUR)));
	    	result.setCompletedParts(parseDouble(getElementValue(completedElement, XML_ELEMENT_PARTS)));
	    	result.setCompletedMisc(parseDouble(getElementValue(completedElement, XML_ELEMENT_MISC)));
	    	result.setCompletedSubtotal(parseDouble(getElementValue(completedElement, XML_ELEMENT_SUBTOTAL)));
	    	result.setCompletedTaxes(parseDouble(getElementValue(completedElement, XML_ELEMENT_TAXES)));
	    	result.setCompletedToPay(parseDouble(getElementValue(completedElement, XML_ELEMENT_TO_PAY)));
	    	
	    	result.setUncompletedLabour(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_LABOUR)));
	    	result.setUncompletedParts(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_PARTS)));
	    	result.setUncompletedMisc(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_MISC)));
	    	result.setUncompletedSubtotal(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_SUBTOTAL)));
	    	result.setUncompletedTaxes(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_TAXES)));
	    	result.setUncompletedToPay(parseDouble(getElementValue(uncompletedElement, XML_ELEMENT_TO_PAY)));
	    	
	    	result.setToPayAndEstimate(parseDouble(getElementValue(element, XML_ELEMENT_TO_PAY_AND_ESTIMATE)));
	    	
	    	return result;    	
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}    	
    }
    
    
    public static BillingTotalsParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new BillingTotalsParser();    
    	}
    	
    	return myself;
    }
    
    private BillingTotalsParser()
    {
    	// Disable default constructor.
    }
    
	@Override
	public String getXmlElement() 
	{
		return XML_ELEMENT;
	}
}	
	
