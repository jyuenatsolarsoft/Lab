package com.cms.gwt.fs.client.model.billing;

public class BillingDetailsListEntry 
{    
    private int line;    
    private String chargeTypeDescription;    
    private String description;    
    private double quantity;
    private String quantityUOM;
    private double unitPrice;
    private double subtotal;           
    private double taxes;
    private double total;
    private boolean warranty;
    
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getChargeTypeDescription() {
		return chargeTypeDescription;
	}
	public void setChargeTypeDescription(String chargeTypeDescription) {
		this.chargeTypeDescription = chargeTypeDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getQuantityUOM() {
		return quantityUOM;
	}
	public void setQuantityUOM(String quantityUOM) {
		this.quantityUOM = quantityUOM;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getTaxes() {
		return taxes;
	}
	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public boolean isWarranty() {
		return warranty;
	}
	public void setWarranty(boolean warranty) {
		this.warranty = warranty;
	}
    
    
    
}
