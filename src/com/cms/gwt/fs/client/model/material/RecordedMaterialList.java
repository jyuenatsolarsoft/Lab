package com.cms.gwt.fs.client.model.material;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Entries contained in this model are on the Recorded Material list (The first list).
 */
public class RecordedMaterialList extends FSModel {

	private String ticketNumber;
	private int eventId;
	
	private List<RecordedMaterialListEntry> materials = new ArrayList<RecordedMaterialListEntry>();
	
	public void addEntry(RecordedMaterialListEntry material) {
		
		materials.add(material);		
	}
	
	public List<RecordedMaterialListEntry> getEntries()
	{
		return materials;
	}		

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}				
}	
