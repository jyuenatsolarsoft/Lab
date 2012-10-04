package com.cms.gwt.fs.client.rpc.xml.message.parser;

import com.cms.gwt.fs.client.exception.FSParseException;
import com.cms.gwt.fs.client.model.item.ProductInformation;
import com.google.gwt.xml.client.Element;

public class ProductInformationParser extends FSModelParser {

	public static final String XML_ELEMENT = "productInformation";	
	
	private static final String XML_ELEMENT_PRODUCT = "product";
	private static final String XML_ELEMENT_PRODUCT_DESCRIPTION = "productDescription";
	private static final String XML_ELEMENT_PRODUCT_ID = "productId";
	private static final String XML_ELEMENT_PRODUCT_ID_DESCRIPTION = "productIdDescription";
	private static final String XML_ELEMENT_SHIPPED_DATE = "shippedDate";
	private static final String XML_ELEMENT_EXPIRY_DATE = "expiryDate";
	private static final String XML_ELEMENT_CONTRACT_TYPE = "contractType";
	private static final String XML_ELEMENT_CONTRACT_TYPE_DESCRIPTION = "contractTypeDescription";
    private static final String XML_ELEMENT_LINE1 = "line1";
    private static final String XML_ELEMENT_LINE2 = "line2";
    private static final String XML_ELEMENT_LINE3 = "line3";	
	
	private static  ProductInformationParser myself;
	
	        	            	 	
	 public ProductInformation parse(Element element) throws FSParseException
	 {
		 try
		 {
			 ProductInformation result = new ProductInformation();
			 
			 result.setProduct(getElementValue(element, XML_ELEMENT_PRODUCT));
			 result.setProductDescription(getElementValue(element, XML_ELEMENT_PRODUCT_DESCRIPTION));
			 result.setProductId(getElementValue(element, XML_ELEMENT_PRODUCT_ID));
			 result.setProductIdDescription(getElementValue(element, XML_ELEMENT_PRODUCT_ID_DESCRIPTION)); 
			 result.setShippedDate(createSqlDate(getElementValue(element, XML_ELEMENT_SHIPPED_DATE)));
			 
			 // On JGORich, the expiry date field has the same date as shipped date. However, no data is present in the 
			 // xml data.
			 result.setExpiryDate(createSqlDate(getElementValue(element, XML_ELEMENT_EXPIRY_DATE)));
			 
			 result.setContractType(getElementValue(element, XML_ELEMENT_CONTRACT_TYPE));
			 
			 Element contractTypeDescElement = getSingleElementByTagName(element, XML_ELEMENT_CONTRACT_TYPE_DESCRIPTION);
			 
			 result.setContractDescription1(getElementValue(contractTypeDescElement, XML_ELEMENT_LINE1));
			 result.setContractDescription2(getElementValue(contractTypeDescElement, XML_ELEMENT_LINE2));
			 result.setContractDescription3(getElementValue(contractTypeDescElement, XML_ELEMENT_LINE3));
	 
		 	return result;		
		}
		catch (Exception e)
		{
			throw new FSParseException(e);
		}
	 }	

	 public static ProductInformationParser getInstance()
	 {
	 	if (myself == null)
	 	{
	 		myself = new ProductInformationParser();
	 	}
	 	
	 	return myself;
	 }

	 @Override
	 public String getXmlElement() {

	 	return XML_ELEMENT;
	 }
}
	

