package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class RecordedMaterialEntryParser extends RecordedMaterialListEntryParser 
{ 
	private static RecordedMaterialEntryParser myself;
	
	final public static String XML_ELEMENT = "recordedMaterialEntry";
	
	final private static String XML_ELEMENT_PART = "part";
	final private static String XML_ELEMENT_OPERATION = "operation";	
	final private static String XML_ELEMENT_STOCK_LOCATION = "stockLocation";
	final private static String XML_ELEMENT_BIN_LOCATION = "binLocation";
	final private static String XML_ELEMENT_QUANTITY_UOM = "quantityUOM";
	
	final private static String XML_ELEMENT_LOT_NUMBER = "lotNumber";
	final private static String XML_ELEMENT_SERIAL_NUMBER = "serialNumber";
           			
	        
	public RecordedMaterial parse(Element element) throws FSParseException
	{
		try
		{
			RecordedMaterial result = new RecordedMaterial();
						
			parse(element, result);
					
			result.setOperation(getElementValue(element, XML_ELEMENT_OPERATION));
			result.setStockLocation(getElementValue(element, XML_ELEMENT_STOCK_LOCATION));
			result.setBinLocation(getElementValue(element, XML_ELEMENT_BIN_LOCATION));
			result.setQuantityUOM(getElementValue(element, XML_ELEMENT_QUANTITY_UOM));
			result.setPartNumber(getElementValue(element, XML_ELEMENT_PART));
			
			// override the value in the parent class.
			result.setLotNumber(getElementValue(element, XML_ELEMENT_LOT_NUMBER));
			result.setSerialNumber(getElementValue(element, XML_ELEMENT_SERIAL_NUMBER));
			
			result.setMessages(MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));
									
			return result;
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
	
	public static RecordedMaterialEntryParser getInstance()
	{
		if (myself == null)
		{
			myself = new RecordedMaterialEntryParser();
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
	 public XmlRepresentable serialize(RecordedMaterial material)
	 {		
		List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		
		childElements.add(new SimpleElement(XML_ELEMENT_SEQUENCE, convertToStr(material.getSequence()))); 
		childElements.add(new SimpleElement(XML_ELEMENT_LINE, convertToStr(material.getLine())));
		childElements.add(new SimpleElement(XML_ELEMENT_LOT_NUMBER, material.getLotNumber()));
		childElements.add(new SimpleElement(XML_ELEMENT_SERIAL_NUMBER, material.getSerialNumber()));
		childElements.add(new SimpleElement(XML_ELEMENT_QUANTITY, convertToStr(material.getQuantity())));
		childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(material.isWarranty())));
		childElements.add(new SimpleElement(XML_ELEMENT_STOCK_LOCATION, material.getStockLocation()));
		childElements.add(new SimpleElement(XML_ELEMENT_BIN_LOCATION, material.getBinLocation()));
				
		return new ComplexElement(XML_ELEMENT, childElements);		
	 }			
}
