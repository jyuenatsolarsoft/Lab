package com.cms.gwt.fs.client.model.material;

import com.cms.gwt.fs.client.model.FSModel;



public class RecordedMaterialListEntry extends FSModel
{

	/** key field of Material. */
	private String ticketNumber;
	
	private int eventId;
	
	/** key field of Material. */
	private int sequence;
	
	private int line;	
	private String description;
	private String partNumber;
	private String partDescription;
	private double quantity;
	private String lotNumber;
	private String serialNumber;
	private boolean isWarranty;
	private boolean isPosted;
	
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
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
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public boolean isWarranty() {
		return isWarranty;
	}
	public void setWarranty(boolean isWarranty) {
		this.isWarranty = isWarranty;
	}
	public boolean isPosted() {
		return isPosted;
	}
	public void setPosted(boolean isPosted) {
		this.isPosted = isPosted;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
}

