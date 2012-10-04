package com.cms.gwt.fs.client.model.location;

import com.cms.gwt.fs.client.model.FSModel;
import com.cms.gwt.fs.client.model.Messages;



public class ServiceTicketLocation extends FSModel
{
    private static final int MAX_SITE_LINES = 7;
    
    private static final int MAX_ADDRESS_LINES = 6;
	
	/** Only when service ticket type is for a "customer" */
	private String customer;
	
	/** Only when the ticket type is for "Internal Asset" */
	private String inHousePlant;
	
	private String[] addressLine = new String[MAX_ADDRESS_LINES]; 		 	     
 
    // only send the customerSite, territory and siteLine1-7 if the service ticket type is for a "Customer" 
    // customerSite, territory and siteLine1-7 are the only elements reqired for the saveServiceTicketLocation request 
    
	private String customerSite;
	private String territory;
    
	private String[] siteLine = new String[MAX_SITE_LINES];;


	// will only occur when responding to a save request
	private Messages messages;
	
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getInHousePlant() {
		return inHousePlant;
	}

	public void setInHousePlant(String inHousePlant) {
		this.inHousePlant = inHousePlant;
	}

	public String[] getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String[] addressLine) {
		this.addressLine = addressLine;
	}

	public String getCustomerSite() {
		return customerSite;
	}

	public void setCustomerSite(String customerSite) {
		this.customerSite = customerSite;
	}

	public String getTerritory() {
		return territory;
	}

	public void setTerritory(String territory) {
		this.territory = territory;
	}

	public String[] getSiteLine() {
		return siteLine;
	}

	public void setSiteLine(String[] siteLine) {
		this.siteLine = siteLine;
	}

	public Messages getMessages() {
		return messages;
	}

	public void setMessages(Messages messages) {
		this.messages = messages;
	}
		    
}
