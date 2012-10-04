package com.cms.gwt.fs.client.rpc.action;

/**
 * Retrieve the service ticket from the backend.
 * 
 */
public class GetServiceTicket implements Action {
	
	/**
	 * The ticket to be retrieved will have the same ticket number as specified.
	 */
	private String ticketNumber;

	@SuppressWarnings("unused")
	/**
	 * Disabled the default constructor.
	 */
	private GetServiceTicket() {
		// Empty default constructor.
	}
	
	
	/**
	 * Constructor.
	 * 
	 * @param ticketNumber The ticket to be retrieved will have the same ticket number as specified.
	 */
	public GetServiceTicket(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	
}
