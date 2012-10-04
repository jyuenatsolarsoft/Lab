package com.cms.gwt.fs.client.model;

import java.util.Date;

/**
 * This model class represents the service ticket.
 * 
 * Ticket details can be found as properties of this class.
 * 
 */
public class ServiceTicket extends FSModel 
{
	
	public static final String SERVICE_TYPE_CUSTOMER = "1"; 
	
	private String ticketNumber;
	private Date dateOpened;
	private String timeOpened;
	private String serviceType;
	private String serviceTypeDescription;
	private String serviceItemType;
	private String serviceItem;
	private String serviceItemDescription;
	private String mainContact;
	private String mainContactNumber;
	private String siteContact;
	private String siteContactNumber;
	private String secondNumber;
	private String contactEmail;
	private String complaint;
	private boolean repeatIssue;
	private String priorityCode;
	private String serviceProcedure;
	private String subject;
	private Date scheduledDate;
	private String startTime;
	private double effort;
	private boolean confirmationRequired;
	private boolean confirmed;
	private String assignedTo;
	private Date respondByDate;
	private String respondByTime;
	private String status;
	private double estimatedCost;
	private String estimatedCostUOM;
	private String customerPO;
	private String previousTicket;
	private String customer;
	
	/** An error message from the backend. */ 
	private Messages messages;
	
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
	public Date getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(Date dateOpened) {
		this.dateOpened = dateOpened;
	}
	public String getTimeOpened() {
		return timeOpened;
	}
	public void setTimeOpened(String timeOpened) {
		this.timeOpened = timeOpened;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getServiceItemType() {
		return serviceItemType;
	}
	public void setServiceItemType(String serviceItemType) {
		this.serviceItemType = serviceItemType;
	}
	public String getServiceItem() {
		return serviceItem;
	}
	public void setServiceItem(String serviceItem) {
		this.serviceItem = serviceItem;
	}
	public String getServiceItemDescription() {
		return serviceItemDescription;
	}
	public void setServiceItemDescription(String serviceItemDescription) {
		this.serviceItemDescription = serviceItemDescription;
	}
	public String getMainContact() {
		return mainContact;
	}
	public void setMainContact(String mainContact) {
		this.mainContact = mainContact;
	}
	public String getMainContactNumber() {
		return mainContactNumber;
	}
	public void setMainContactNumber(String mainContactNumber) {
		this.mainContactNumber = mainContactNumber;
	}
	public String getSiteContact() {
		return siteContact;
	}
	public void setSiteContact(String siteContact) {
		this.siteContact = siteContact;
	}
	public String getSiteContactNumber() {
		return siteContactNumber;
	}
	public void setSiteContactNumber(String siteContactNumber) {
		this.siteContactNumber = siteContactNumber;
	}
	public String getSecondNumber() {
		return secondNumber;
	}
	public void setSecondNumber(String secondNumber) {
		this.secondNumber = secondNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	public boolean isRepeatIssue() {
		return repeatIssue;
	}
	public void setRepeatIssue(boolean repeatIssue) {
		this.repeatIssue = repeatIssue;
	}
	public String getPriorityCode() {
		return priorityCode;
	}
	public void setPriorityCode(String priorityCode) {
		this.priorityCode = priorityCode;
	}
	public String getServiceProcedure() {
		return serviceProcedure;
	}
	public void setServiceProcedure(String serviceProcedure) {
		this.serviceProcedure = serviceProcedure;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public double getEffort() {
		return effort;
	}
	public void setEffort(double effort) {
		this.effort = effort;
	}
	public boolean isConfirmationRequired() {
		return confirmationRequired;
	}
	public void setConfirmationRequired(boolean confirmationRequired) {
		this.confirmationRequired = confirmationRequired;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	public String getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getRespondByDate() {
		return respondByDate;
	}
	public void setRespondByDate(Date respondByDate) {
		this.respondByDate = respondByDate;
	}
	public String getRespondByTime() {
		return respondByTime;
	}
	public void setRespondByTime(String respondByTime) {
		this.respondByTime = respondByTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(double estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	public String getEstimatedCostUOM() {
		return estimatedCostUOM;
	}
	public void setEstimatedCostUOM(String estimatedCostUOM) {
		this.estimatedCostUOM = estimatedCostUOM;
	}
	public String getCustomerPO() {
		return customerPO;
	}
	public void setCustomerPO(String customerPO) {
		this.customerPO = customerPO;
	}
	public String getPreviousTicket() {
		return previousTicket;
	}
	public void setPreviousTicket(String previousTicket) {
		this.previousTicket = previousTicket;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getServiceTypeDescription() {
		return serviceTypeDescription;
	}	
	public void setServiceTypeDescription(String serviceTypeDescription) {
		this.serviceTypeDescription = serviceTypeDescription;
	}		

}
