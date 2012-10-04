package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.LotEntry;
import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.material.SerialEntry;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;


// TODO: Consider adding inheritance or composition to RecordedMaterialEntry and RecordedMaterialListEntry.
public class RecordedMaterialEntriesEntryParser extends FSModelParser 
{ 
	private static RecordedMaterialEntriesEntryParser myself;
	
	final public static String XML_ELEMENT = "entry";

	final private static String XML_ELEMENT_SEQUENCE = "sequence";
	final private static String XML_ELEMENT_DESCRIPTION = "description";
	final private static String XML_ELEMENT_LINE = "line";
	final private static String XML_ELEMENT_PART = "part";
	final private static String XML_ELEMENT_PARTDESCRIPTION = "partDescription";
    private static final String XML_ELEMENT_REQUIRED_QUANTITY = "requiredQuantity";
    private static final String XML_ELEMENT_RECORDED_QUANTITY = "recordedQuantity";
    private static final String XML_ELEMENT_QUANTIY_UOM = "quantityUOM";
    private static final String XML_ELEMENT_USED_QUANTITY = "usedQuantity";
    private static final String XML_ELEMENT_WARRANTY = "warranty";
    private static final String XML_ELEMENT_SERIAL_CONTROLLED = "serialControlled";
    private static final String XML_ELEMENT_LOT_CONTROLEED = "lotControlled";
       			
    // Used for serialization only.
    private static final String XML_ELEMENT_SERIAL_NUMBERS = "serialNumbers";
    private static final String XML_ELEMENT_LOT_NUMBERS = "lotNumbers";
	        
	public RecordedMaterialEntriesEntry parse(Element element) throws FSParseException
	{
		try
		{
			RecordedMaterialEntriesEntry result = new RecordedMaterialEntriesEntry();
							
			result.setSequence(parseInt(getElementValue(element, XML_ELEMENT_SEQUENCE)));
			result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
			result.setLine(parseInt(getElementValue(element, XML_ELEMENT_LINE)));
			result.setPart(getElementValue(element, XML_ELEMENT_PART));
			result.setPartDescription(getElementValue(element, XML_ELEMENT_PARTDESCRIPTION));
			result.setRequiredQuantity(parseDouble(getElementValue(element, XML_ELEMENT_REQUIRED_QUANTITY)));		
			result.setRecordedQuantity(parseDouble(getElementValue(element, XML_ELEMENT_RECORDED_QUANTITY)));
			result.setQuantityUOM(getElementValue(element, XML_ELEMENT_QUANTIY_UOM));
			//result.setSerialControlled(getElementValue(element, XML_ELEMENT_SERIA));
			//result.setLotControlled(getElementValue(element, XML_ELEMENT_));
			result.setUsedQuantity(parseDouble(getElementValue(element, XML_ELEMENT_USED_QUANTITY)));
			result.setWarranty(parseBooleanInt(getElementValue(element, XML_ELEMENT_WARRANTY)));
			result.setSerialControlled(parseBooleanYN(getElementValue(element, XML_ELEMENT_SERIAL_CONTROLLED)));
			result.setLotControlled(parseBooleanYN(getElementValue(element, XML_ELEMENT_LOT_CONTROLEED)));
							
			return result;
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}				
	}	
	
	public static RecordedMaterialEntriesEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new RecordedMaterialEntriesEntryParser();
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
	 public XmlRepresentable serialize(RecordedMaterialEntriesEntry entry)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_SEQUENCE, convertToStr(entry.getSequence()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_LINE, convertToStr(entry.getLine())));
		childElements.add(new SimpleElement(XML_ELEMENT_PART, entry.getPart()));		
		childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(entry.isWarranty())));
		
		if (entry.isLotControlled() && !entry.isSerialControlled())
		{
			List<XmlRepresentable> lotEntries = new ArrayList<XmlRepresentable>();
			for (LotEntry lotEntry : entry.getLotNumbers())
			{
				lotEntries.add(LotEntryParser.getInstance().serialize(lotEntry));
			}
			childElements.add(new ComplexElement(XML_ELEMENT_LOT_NUMBERS, lotEntries));
		}
		else if ((!entry.isLotControlled() && entry.isSerialControlled()) ||
				(entry.isLotControlled() && entry.isSerialControlled()))
		{
			List<XmlRepresentable> serialEntries = new ArrayList<XmlRepresentable>();
			for (SerialEntry serialEntry : entry.getSerialNumbers())
			{
				serialEntries.add(SerialEntryParser.getInstance().serialize(serialEntry));
			}			
			childElements.add(new ComplexElement(XML_ELEMENT_SERIAL_NUMBERS, serialEntries));
		}			
		else
		{
			childElements.add(new SimpleElement(XML_ELEMENT_USED_QUANTITY, convertToStr(entry.getUsedQuantity())));
		}
		
		return new ComplexElement(XML_ELEMENT, childElements);				
	 }	
}
