package com.cms.gwt.fs.client.model.material;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Entries contained in this model are on the Material "Add" list.
 * 
 *
 */
public class RecordedMaterialEntries extends FSModel 
{

	private String ticketNumber;
	private int eventId;
	
	private List<RecordedMaterialEntriesEntry> materials = new ArrayList<RecordedMaterialEntriesEntry>();
	
	public void addEntry(RecordedMaterialEntriesEntry material) {
		
		materials.add(material);		
	}
	
	public List<RecordedMaterialEntriesEntry> getEntries()
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
