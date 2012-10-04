package com.cms.gwt.fs.client.model.material;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;

/**
 * The material recorded in the detail of the service ticket.
 *
 */
public class ServiceDetailMaterial extends FSModel {
	
	/** key field of Material. */
    private String ticketNumber;
    
    /** key field of Material. */
    private int taskSequence;
    
    private int lineNumber;
    private String part;
    private String partDescription;
    private double quantityRequired;
    private String quantityRequiredUOM;
    private int probability;
    
    // allocate is only used on the material list.
    private boolean allocate;    
    
    /**
     * quantity used is only present on the list.
     */
    private double quantityUsed;

    private Messages messages;

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getTaskSequence() {
		return taskSequence;
	}

	public void setTaskSequence(int taskSequence) {
		this.taskSequence = taskSequence;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
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

	public double getQuantityRequired() {
		return quantityRequired;
	}

	public void setQuantityRequired(double quantityRequired) {
		this.quantityRequired = quantityRequired;
	}

	public String getQuantityRequiredUOM() {
		return quantityRequiredUOM;
	}

	public void setQuantityRequiredUOM(String quantityRequiredUOM) {
		this.quantityRequiredUOM = quantityRequiredUOM;
	}

	public int getProbability() {
		return probability;
	}

	public void setProbability(int probability) {
		this.probability = probability;
	}

	public boolean isAllocate() {
		return allocate;
	}

	public void setAllocate(boolean allocate) {
		this.allocate = allocate;
	}

	public double getQuantityUsed() {
		return quantityUsed;
	}

	public void setQuantityUsed(double quantityUsed) {
		this.quantityUsed = quantityUsed;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
    

    
}
