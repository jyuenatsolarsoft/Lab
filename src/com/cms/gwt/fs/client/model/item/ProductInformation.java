package com.cms.gwt.fs.client.model.item;

import java.sql.Date;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Production information included in the Service Ticket Item.
 *
 */
public class ProductInformation extends FSModel {
	
    private String product;
    private String productDescription;
    private String productId;
    private String productIdDescription;
    private Date shippedDate;
    private Date expiryDate;
    private String contractType;
    private String contractDescription1;
    private String contractDescription2;
    private String contractDescription3;
    
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductIdDescription() {
		return productIdDescription;
	}
	public void setProductIdDescription(String productIdDescription) {
		this.productIdDescription = productIdDescription;
	}
	public Date getShippedDate() {
		return shippedDate;
	}
	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public String getContractDescription1() {
		return contractDescription1;
	}
	public void setContractDescription1(String contractDescription1) {
		this.contractDescription1 = contractDescription1;
	}
	public String getContractDescription2() {
		return contractDescription2;
	}
	public void setContractDescription2(String contractDescription2) {
		this.contractDescription2 = contractDescription2;
	}
	public String getContractDescription3() {
		return contractDescription3;
	}
	public void setContractDescription3(String contractDescription3) {
		this.contractDescription3 = contractDescription3;
	}        	
       
}
