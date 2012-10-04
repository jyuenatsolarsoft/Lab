package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.model.billing.BillingDetailsListEntry;
import com.google.gwt.xml.client.Element;

public class BillingDetailsListEntryParser extends FSModelParser 
{ 
	private static BillingDetailsListEntryParser myself;
	
	final public static  String XML_ELEMENT = "entry";

    private static final String XML_ELEMENT_LINE = "line";    
    private static final String XML_ELEMENT_CHARGE_TYPE_DESCRIPTION = "chargeTypeDescription";       
    private static final String XML_ELEMENT_DESCRIPTION = "description";      
    private static final String XML_ELEMENT_QUANTITY = "quantity";
    private static final String XML_ELEMENT_QUANTITY_UOM = "quantityUOM";    
    private static final String XML_ELEMENT_UNIT_PRICE = "unitPrice";
    private static final String XML_ELEMENT_SUBTOTAL = "subtotal";        
    private static final String XML_ELEMENT_TAXES = "taxes";    
    private static final String XML_ELEMENT_TOTAL = "total";
    private static final String XML_ELEMENT_WARRANTY = "warranty";
    
    
	public BillingDetailsListEntry parse(Element element)
	{
		BillingDetailsListEntry result = new BillingDetailsListEntry();
		
		result.setLine(parseInt(getElementValue(element, XML_ELEMENT_LINE)));
		result.setChargeTypeDescription(getElementValue(element, XML_ELEMENT_CHARGE_TYPE_DESCRIPTION));
		result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
		result.setQuantity(parseDouble(getElementValue(element, XML_ELEMENT_QUANTITY)));
		result.setQuantityUOM(getElementValue(element, XML_ELEMENT_QUANTITY_UOM));
		result.setUnitPrice(parseDouble(getElementValue(element, XML_ELEMENT_UNIT_PRICE)));
		result.setSubtotal(parseDouble(getElementValue(element, XML_ELEMENT_SUBTOTAL)));
		result.setTaxes(parseDouble(getElementValue(element, XML_ELEMENT_TAXES)));
		result.setTotal(parseDouble(getElementValue(element, XML_ELEMENT_TOTAL)));
				
		result.setWarranty(determineBoolValue((getElementValue(element, XML_ELEMENT_WARRANTY))));
				
		return result;
	}
	
	public static BillingDetailsListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new BillingDetailsListEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() {
		
		return XML_ELEMENT;
	}
}
