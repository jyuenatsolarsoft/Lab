package com.cms.gwt.fs.client.model;

import java.util.List;

/**
 * Represents a service ticket list.
 * 
 * This list contains a bunch of service ticket entries.
 * 
 */
public class ServiceTicketList extends FSModel {

	private List<ServiceTicketListEntry> ticketListEntries;

	public ServiceTicketList(List<ServiceTicketListEntry> entries) {	
		ticketListEntries = entries; 
	}

	public ServiceTicketListEntry getTicketListEntry(int index) {
		if (index >= 0 && index < ticketListEntries.size()) {
			return ticketListEntries.get(index);
		} else {
			return null;
		}
	}

	public List<ServiceTicketListEntry> getTicketListEntries() {
		return ticketListEntries;
	}

	/**
	 * used by gwt-ext grid
	 * 
	 * @return
	 */
	public Object[][] getTicketsObject() {
		Object[][] ticketsListObj = new Object[ticketListEntries.size()][ServiceTicketListEntry._SIZE];

		for (int i = 0; i < ticketListEntries.size(); i++) {
			ServiceTicketListEntry entry = getTicketListEntry(i);
			ticketsListObj[i] = new Object[] { entry.getTicketNumber(),
					entry.getTechnician(), entry.getCustomerId(),
					entry.getOpenDate(), entry.getStatus() };
		}

		return ticketsListObj;
	}

}
