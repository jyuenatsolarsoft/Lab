package com.cms.gwt.fs.client.model.material;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * The material recorded in the detail of the service ticket.
 *
 */
public class RecordedMaterialEntriesEntry extends FSModel {
		
    private String ticketNumber;        
    private int sequence;    
    private String description;
    private int line;
    private String part;
    private String partDescription;
    private double requiredQuantity;
    private double recordedQuantity;
    private String quantityUOM;
    private boolean isSerialControlled;
    private boolean isLotControlled;
    private double usedQuantity;
    private boolean isWarranty;
    
    List<SerialEntry> serialNumbers = new ArrayList<SerialEntry>();
    List<LotEntry> lotNumbers = new ArrayList<LotEntry>();

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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getLine() {
		return line;
	}
	public void setLine(int line) {
		this.line = line;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	public String getPartDescription() {
		return partDescription;
	}
	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}
	public double getRequiredQuantity() {
		return requiredQuantity;
	}
	public void setRequiredQuantity(double requiredQuantity) {
		this.requiredQuantity = requiredQuantity;
	}
	public double getRecordedQuantity() {
		return recordedQuantity;
	}
	public void setRecordedQuantity(double recordedQuantity) {
		this.recordedQuantity = recordedQuantity;
	}
	public String getQuantityUOM() {
		return quantityUOM;
	}
	public void setQuantityUOM(String quantityUOM) {
		this.quantityUOM = quantityUOM;
	}
	public boolean isSerialControlled() {
		return isSerialControlled;
	}
	public void setSerialControlled(boolean isSerialControlled) {
		this.isSerialControlled = isSerialControlled;
	}
	public boolean isLotControlled() {
		return isLotControlled;
	}
	public void setLotControlled(boolean isLotControlled) {
		this.isLotControlled = isLotControlled;
	}
	public double getUsedQuantity() {
		return usedQuantity;
	}
	public void setUsedQuantity(double usedQuantity) {
		this.usedQuantity = usedQuantity;
	}
	public boolean isWarranty() {
		return isWarranty;
	}
	public void setWarranty(boolean isWarranty) {
		this.isWarranty = isWarranty;
	}
	
	public void addSerialNumber(SerialEntry serialNumber)
	{
		serialNumbers.add(serialNumber);
	}
	
	public void addLotNumber(LotEntry lotNumber)
	{
		lotNumbers.add(lotNumber);
	}	
		
	public List<SerialEntry> getSerialNumbers()
	{
		return serialNumbers;
	}
	
	public List<LotEntry> getLotNumbers()
	{
		return lotNumbers;
	}
	
	public void setSerialNumbers(List<SerialEntry> serialNumbers) {
		this.serialNumbers = serialNumbers;
	}
	
	public void setLotNumbers(List<LotEntry> lotNumbers) {
		this.lotNumbers = lotNumbers;
	}
}
