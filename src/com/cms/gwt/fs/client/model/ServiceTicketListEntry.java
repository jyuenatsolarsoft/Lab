package com.cms.gwt.fs.client.model;

import java.util.Date;

/**
 * Represents a service ticket list entry which contains brief information of
 * a service ticket.
 * 
 * The ServiceTicketListEntry will be found in the ServiceTicketList.
 * 
 */
public class ServiceTicketListEntry extends FSModel {

	public static final int _SIZE = 6;	
	
    private String ticketNumber;
    private String technician;
    private String status;
    private Date openDate;
    private String customerId;
    private String subject;
    
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public String getTechnician() {
		return technician;
	}
	
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getOpenDate() {
		return openDate;
	}
	
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}        
    
}
