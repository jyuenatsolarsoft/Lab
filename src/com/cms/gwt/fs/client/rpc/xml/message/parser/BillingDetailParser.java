package com.cms.gwt.fs.client.rpc.xml.message.parser;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.billing.BillingDetail;
import com.cms.gwt.fs.client.rpc.xml.message.handler.ComplexElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.SimpleElement;
import com.cms.gwt.fs.client.rpc.xml.message.handler.XmlRepresentable;
import com.google.gwt.xml.client.Element;

public class BillingDetailParser extends FSModelParser 
{	 	
	static private BillingDetailParser myself;
	
	public static final String XML_ELEMENT = "serviceTicketBillingDetail";
	
    // XML Element Names         
    private static final String XML_ELEMENT_LINE = "line";
    private static final String XML_ELEMENT_CHARGE_TYPE = "chargeType";
    private static final String XML_ELEMENT_CHARGE_TYPE_DESCRIPTION = "chargeTypeDescription";
    private static final String XML_ELEMENT_EVENT_ID = "eventID";
    private static final String XML_ELEMENT_MISC_CHARGE_CODE= "miscellaneousChargeCode";
    private static final String XML_ELEMENT_DESCRIPTION = "description";
    private static final String XML_ELEMENT_PART_NUMBER = "partNumber";
    private static final String XML_ELEMENT_PART_DESCRIPTION = "partNumberDescription";
    private static final String XML_ELEMENT_LOT_NUMBER = "lotNumber";
    private static final String XML_ELEMENT_SERIAL_NUMBER = "serialNumber";
    private static final String XML_ELEMENT_QUANTITY = "quantity";
    private static final String XML_ELEMENT_QUANTITY_UOM = "quantityUOM";
    private static final String XML_ELEMENT_UNIT_PRICE = "unitPrice";
    private static final String XML_ELEMENT_SUBTOTAL = "subtotal";
    private static final String XML_ELEMENT_TAX_GROUP = "taxGroup";
    private static final String XML_ELEMENT_TAX_RATE = "taxRate";
    private static final String XML_ELEMENT_TAXES = "taxes";
    private static final String XML_ELEMENT_TOTAL = "total";
    private static final String XML_ELEMENT_WARRANTY = "warranty";
    private static final String XML_ELEMENT_POSTED_TO_INVOICE = "postedToInvoice";
    private static final String XML_ELEMENT_POSTED_TO_INVOICE_SEQ = "postedToInvoiceSequence";
    private static final String XML_ELEMENT_POSTED_TO_CLAIM = "postedToClaim";
    private static final String XML_ELEMENT_POSTED_TO_CLAIM_SEQ = "postedToClaimSequence";
            	            	 	
	 public BillingDetail parse(Element element) throws FSParseException
	 {
		 try
		 {
		 	BillingDetail result = new BillingDetail();
		 	
		 	result.setLine(parseInt(getElementValue(element, XML_ELEMENT_LINE)));
		 	result.setChargeType(getElementValue(element, XML_ELEMENT_CHARGE_TYPE));
		 	result.setChargeTypeDescription(getElementValue(element, XML_ELEMENT_CHARGE_TYPE_DESCRIPTION));
		 	result.setEventID(parseInt(getElementValue(element, XML_ELEMENT_EVENT_ID)));
		 	result.setMiscChargeCode(getElementValue(element, XML_ELEMENT_MISC_CHARGE_CODE));
		 	result.setDescription(getElementValue(element, XML_ELEMENT_DESCRIPTION));
		 	result.setPartNumber(getElementValue(element, XML_ELEMENT_PART_NUMBER));
		 	result.setPartNumberDescription(getElementValue(element, XML_ELEMENT_PART_DESCRIPTION));
		 	result.setLotNumber(getElementValue(element, XML_ELEMENT_LOT_NUMBER));
		 	result.setSerialNumber(parseInt(getElementValue(element, XML_ELEMENT_SERIAL_NUMBER)));
		 	result.setQuantity(parseDouble(getElementValue(element, XML_ELEMENT_QUANTITY)));
		 	result.setQuantityUOM(getElementValue(element, XML_ELEMENT_QUANTITY_UOM));
		 	result.setUnitPrice(parseDouble(getElementValue(element, XML_ELEMENT_UNIT_PRICE)));
		 	result.setSubtotal(parseDouble(getElementValue(element, XML_ELEMENT_SUBTOTAL)));
		 	result.setTaxGroup(getElementValue(element, XML_ELEMENT_TAX_GROUP));
		 	result.setTaxRate(getElementValue(element, XML_ELEMENT_TAX_RATE));
		 	result.setTaxes(parseDouble(getElementValue(element, XML_ELEMENT_TAXES)));
		 	result.setTotal(parseDouble(getElementValue(element, XML_ELEMENT_TOTAL)));
		 	result.setWarranty(determineBoolValue(getElementValue(element, XML_ELEMENT_WARRANTY)));
		 	result.setPostedToInvoice(parseInt(getElementValue(element, XML_ELEMENT_POSTED_TO_INVOICE)));
		 	result.setPostedToInvoiceSeq(parseInt(getElementValue(element, XML_ELEMENT_POSTED_TO_INVOICE_SEQ)));
		 	result.setPostedToClaim(parseInt(getElementValue(element, XML_ELEMENT_POSTED_TO_CLAIM)));
		 	result.setPostedToClaimSeq(parseInt(getElementValue(element, XML_ELEMENT_POSTED_TO_CLAIM_SEQ)));
		 	
		 	result.setMessages(
		 			MessagesParser.getInstance().parse(getSingleElementByTagName(element, MessagesParser.XML_ELEMENT)));		 
			 	
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

	 public static BillingDetailParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new BillingDetailParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }


	 /**
	  * Serialize the BillingDetail.
	  * 
	  * @param event
	  * @return
	  */
	 public XmlRepresentable serialize(BillingDetail detail)
	 {		
		 List<XmlRepresentable> childElements = new ArrayList<XmlRepresentable>();
		 
		 childElements.add(new SimpleElement(XML_ELEMENT_LINE, convertToStr(detail.getLine())));
		 childElements.add(new SimpleElement(XML_ELEMENT_CHARGE_TYPE, detail.getChargeType()));
		 childElements.add(new SimpleElement(XML_ELEMENT_MISC_CHARGE_CODE, detail.getMiscChargeCode()));
		 childElements.add(new SimpleElement(XML_ELEMENT_DESCRIPTION, detail.getDescription()));
		 childElements.add(new SimpleElement(XML_ELEMENT_SERIAL_NUMBER, convertToStr(detail.getSerialNumber())));
		 childElements.add(new SimpleElement(XML_ELEMENT_QUANTITY, convertToStr(detail.getQuantity())));
		 childElements.add(new SimpleElement(XML_ELEMENT_UNIT_PRICE, convertToStr(detail.getUnitPrice())));
		 childElements.add(new SimpleElement(XML_ELEMENT_TAX_GROUP, detail.getTaxGroup()));
		 childElements.add(new SimpleElement(XML_ELEMENT_TAX_RATE, detail.getTaxRate()));
		 childElements.add(new SimpleElement(XML_ELEMENT_WARRANTY, convertToStr(detail.isWarranty())));
		 
		 return new ComplexElement(XML_ELEMENT, childElements);
	 }
}