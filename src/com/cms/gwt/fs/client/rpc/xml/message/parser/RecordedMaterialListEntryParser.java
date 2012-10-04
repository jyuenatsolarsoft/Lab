package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterialListEntry;
import com.cms.gwt.fs.client.model.workHistory.RecordedLabourListEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class RecordedMaterialListEntryParser extends FSModelParser 
{ 
	private static RecordedMaterialListEntryParser myself;
	
	final public static String XML_ELEMENT = "entry";

	final protected static String XML_ELEMENT_SEQUENCE = "sequence";
	final protected static String XML_ELEMENT_DESCRIPTION = "description";
	final protected static String XML_ELEMENT_LINE = "line";
	final protected static String XML_ELEMENT_PARTNUMBER = "partNumber";
	final protected static String XML_ELEMENT_PARTDESCRIPTION = "partDescription";
	final protected static String XML_ELEMENT_QUANTITY = "quantity";
	final protected static String XML_ELEMENT_WARRANTY = "warranty";	
	final protected static String XML_ELEMENT_LOT = "lot";	
	final protected static String XML_ELEMENT_SERIAL = "serial";
	
	final protected static String XML_ELEMENT_POSTED = "posted";

        
	/**
	 * Parse the data in the provided element.
	 *  
	 * @param element
	 * @return
	 */
	public RecordedMaterialListEntry parse(Element element) throws FSParseException
	{
		RecordedMaterialListEntry result = new RecordedMaterialListEntry();
		
		return parse(element, result);		
	}
	
	/**
	 * Populate the provided material entry with the data contained in the element.
	 * 
	 * @param element
	 * @param materialEntry
	 * 
	 * @return RecordedMaterialListEntry populated with the data from the provided element.
	 */
	public RecordedMaterialListEntry parse(Element element, RecordedMaterialListEntry materialEntry) throws FSParseException
	{
		try
		{
			materialEntry.setSequence(parseInt(getElementValue(element, XML_ELEMENT_SEQUENCE)));
			materialEntry.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			materialEntry.setLine(parseInt(getElementValue(element, XML_ELEMENT_LINE)));
			materialEntry.setPartNumber(getElementValue(element, XML_ELEMENT_PARTNUMBER));
			materialEntry.setPartDescription(getElementValue(element, XML_ELEMENT_PARTDESCRIPTION));
			materialEntry.setQuantity(parseDouble(getElementValue(element, XML_ELEMENT_QUANTITY)));		
			materialEntry.setWarranty(determineBoolValue((getElementValue(element, XML_ELEMENT_WARRANTY))));
			materialEntry.setLotNumber(getElementValue(element, XML_ELEMENT_LOT));
			materialEntry.setSerialNumber(getElementValue(element, XML_ELEMENT_SERIAL));		
			materialEntry.setPosted(parseBooleanYN(getElementValue(element, XML_ELEMENT_POSTED)));
					
			return materialEntry;		
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	}
	
	public static RecordedMaterialListEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new RecordedMaterialListEntryParser();
		}
		
		return myself;
	}
	
	@Override
	public String getXmlElement() 
	{		
		return XML_ELEMENT;
	}
	
	 /**
	  * Serialize the recorded labour.
	  * 
	  * @param entry 
	  * @return
	  */
	 public XmlRepresentable serialize(RecordedLabourListEntry entry)
	 {		
//		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
//		
//		childElements.add(new SimpleElement(XML_ELEMENT_SEQUENCE, convertToStr(entry.getSequence()))); 
//		childElements.add(new SimpleElement(XML_ELEMENT_LINE, convertToStr(entry.getLine())));
//		childElements.add(new SimpleElement(XML_ELEMENT_TIME, convertToStr(entry.getTime())));						
//		childElements.add(new SimpleElement(XML_ELEMENT_OVERTIME, convertToStr(entry.isOvertime())));
//		childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(entry.isWarranty())));
//		
//		return new ComplexElement(XML_ELEMENT, childElements);
		return null;
	 }	
}
