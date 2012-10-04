package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.model.billing.BillingDetailsList;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.NodeList;

public class BillingDetailsListParser extends FSModelParser 
{	
	private static BillingDetailsListParser myself;
	
	final public static String XML_ELEMENT = "billingDetailsList";
		
	public BillingDetailsList parse(Element element)
	{				
		BillingDetailsListEntryParser entryParser = BillingDetailsListEntryParser.getInstance(); 
		
		NodeList entryList = element.getElementsByTagName(BillingDetailsListEntryParser.XML_ELEMENT);
		
		BillingDetailsList entries = new BillingDetailsList();
				
		for (int i=0; i<entryList.getLength(); i++)
		{
			entries.addEntry(entryParser.parse((Element)entryList.item(i)));
		}
		
		return entries;
	}

	@Override
	public String getXmlElement() {
 
		return XML_ELEMENT;
	}
	
	 public static BillingDetailsListParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new BillingDetailsListParser();
	 	}
	 	
	 	return myself;
	 }	
}	
