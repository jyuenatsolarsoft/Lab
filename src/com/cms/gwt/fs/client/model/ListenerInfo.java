package com.cms.gwt.fs.client.model;

public class ListenerInfo {
	private String ticketNumber;
	private String panelNumber;
	private String uniqueId;
	private String eventId;
	private String tabNumber;

	public ListenerInfo() {
		ticketNumber = "";
		panelNumber = "";
		uniqueId = "";
		eventId = "";
		tabNumber = "";
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public String getPanelNumber() {
		return panelNumber;
	}

	public void setPanelNumber(String panelNumber) {
		this.panelNumber = panelNumber;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getTabNumber() {
		return tabNumber;
	}

	public void setTabNumber(String tabNumber) {
		this.tabNumber = tabNumber;
	}
}
