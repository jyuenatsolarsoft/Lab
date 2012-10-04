package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.billing.BillingDetailsList;
import com.cms.gwt.fs.client.model.billing.BillingSummary;
import com.cms.gwt.fs.client.model.billing.BillingTotals;
import com.google.gwt.xml.client.Element;

public class BillingSummaryParser extends FSModelParser {

	private static BillingSummaryParser myself;
	
    public static final String XML_ELEMENT = "billingSummary";

	
    public BillingSummary parse(Element element) throws FSParseException
    {    	    	    	    	
    	try
    	{
	    	BillingDetailsList detailsList = BillingDetailsListParser.getInstance().parse(getSingleElementByTagName(element, BillingDetailsListParser.XML_ELEMENT));
	    	BillingTotals totals = BillingTotalsParser.getInstance().parse(getSingleElementByTagName(element, BillingTotalsParser.XML_ELEMENT));
	    	
	    	return new BillingSummary(detailsList, totals);    	    	    	    	    	    	    	    	    	    	    
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
    
    
    public static BillingSummaryParser getInstance()
    {
    	if (myself == null)
    	{
    		myself = new BillingSummaryParser();    
    	}
    	
    	return myself;
    }
    
    private BillingSummaryParser()
    {
    	// Disable default constructor.
    }
    
	@Override
	public String getXmlElement() {		
		return XML_ELEMENT;
	}
}
