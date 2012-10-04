package com.cms.gwt.fs.client.rpc.action;

/**
 * Action to retrieve the service ticket item.
 *
 */
public class GetServiceTicketItem extends GetServiceTicketProperties 
{	
	@SuppressWarnings("unused")
	private GetServiceTicketItem() {
		// Disable the default constructor.
		
	}
	
	protected GetServiceTicketItem(String ticketNumber) {
		super(ticketNumber);
	}
	
	/**
	 * Method to retrieve an instance of GetServiceTicketItem.
	 * 
	 * @param ticketNumber The key of the service ticket which the requested ticket item belongs to.
	 * 
	 * @return The GetServiceTicketItem containing the specified ticket number.
	 */
	public static GetServiceTicketItem newInstance(String ticketNumber) {
		return new GetServiceTicketItem(ticketNumber);
	}
	
}
