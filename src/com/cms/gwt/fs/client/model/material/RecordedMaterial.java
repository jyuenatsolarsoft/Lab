package com.cms.gwt.fs.client.model.material;

import com.cms.gwt.fs.client.model.Messages;

/**
 * 
 * Contains the detailed information of the recorded material. This is used for view and edit only.
 * 
 * Please @see RecordedMaterialListEntry and RecordedMaterialEntriesEntry class for 
 * the recorded materials shown on the lists.
 *
 */
public class RecordedMaterial extends RecordedMaterialListEntry 
{
	private String operation;
	private String stockLocation;
	private String binLocation;
	private String quantityUOM;
	
	private Messages messages;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getStockLocation() {
		return stockLocation;
	}
	public void setStockLocation(String stockLocation) {
		this.stockLocation = stockLocation;
	}
	public String getQuantityUOM() {
		return quantityUOM;
	}
	public void setQuantityUOM(String quantityUOM) {
		this.quantityUOM = quantityUOM;
	}
	public String getBinLocation() {
		return binLocation;
	}
	public void setBinLocation(String binLocation) {
		this.binLocation = binLocation;
	}
	public Messages getMessages() {
		return messages;
	}
	public void setMessages(Messages messages) {
		this.messages = messages;
	}			
}
