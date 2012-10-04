package com.cms.gwt.fs.client.model.billing;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;

public class BillingDetail extends FSModel {
	    
	private String ticketNumber;
    private int line;
    private String chargeType;
    private String chargeTypeDescription;
    private int eventID;
    private String miscChargeCode;
    private String description;
    private String partNumber;
    private String partNumberDescription;
    private String lotNumber;
    private int serialNumber;
    private double quantity;
    private String quantityUOM;
    private double unitPrice;
    private double subtotal;
    private String  taxGroup;
    private String taxRate;
    private double taxes;
    private double total;
    private boolean warranty;
    private int postedToInvoice;
    private int postedToInvoiceSeq;
    private int postedToClaim;
    private int postedToClaimSeq;
    
    private Messages messages;
    
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getChargeType() {
		return chargeType;
	}
	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	public String getChargeTypeDescription() {
		return chargeTypeDescription;
	}
	public void setChargeTypeDescription(String chargeTypeDescription) {
		this.chargeTypeDescription = chargeTypeDescription;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public String getMiscChargeCode() {
		return miscChargeCode;
	}
	public void setMiscChargeCode(String miscChargeCode) {
		this.miscChargeCode = miscChargeCode;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getPartNumberDescription() {
		return partNumberDescription;
	}
	public void setPartNumberDescription(String partNumberDescription) {
		this.partNumberDescription = partNumberDescription;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public int getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
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
	public String getTaxGroup() {
		return taxGroup;
	}
	public void setTaxGroup(String taxGroup) {
		this.taxGroup = taxGroup;
	}
	public String getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
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
	public int getPostedToInvoice() {
		return postedToInvoice;
	}
	public void setPostedToInvoice(int postedToInvoice) {
		this.postedToInvoice = postedToInvoice;
	}
	public int getPostedToInvoiceSeq() {
		return postedToInvoiceSeq;
	}
	public void setPostedToInvoiceSeq(int postedToInvoiceSeq) {
		this.postedToInvoiceSeq = postedToInvoiceSeq;
	}
	public int getPostedToClaim() {
		return postedToClaim;
	}
	public void setPostedToClaim(int postedToClaim) {
		this.postedToClaim = postedToClaim;
	}
	public int getPostedToClaimSeq() {
		return postedToClaimSeq;
	}
	public void setPostedToClaimSeq(int postedToClaimSeq) {
		this.postedToClaimSeq = postedToClaimSeq;
	}
	public Messages getMessages() {
		return messages;
	}
	public void setMessages(Messages messages) {
		this.messages = messages;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}		
}
