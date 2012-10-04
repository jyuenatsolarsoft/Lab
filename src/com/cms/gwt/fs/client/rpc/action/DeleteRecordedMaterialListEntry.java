package com.cms.gwt.fs.client.rpc.action;

public class DeleteRecordedMaterialListEntry implements Action {

	private String ticketNumber;
	private String eventId;
	private String sequence;
	private String line;
		
	protected DeleteRecordedMaterialListEntry()
	{
		// empty constructor for child classes.
	}
	
	protected DeleteRecordedMaterialListEntry(String ticketNumber, String eventId, String sequence, String line)
	{
		this.ticketNumber = ticketNumber;
		this.eventId = eventId;
		this.sequence = sequence;
		this.line = line;
	}
	
	public String getEventId() 
	{
		return eventId;
	}	
	
	public String getTicketNumber() 
	{
		return ticketNumber;
	}
	
	public String getSequence()
	{
		return sequence;	
	}
	
	public String getLine()
	{
		return line;
	}
	
	public static DeleteRecordedMaterialListEntry newInstance(String ticketNumber, String eventId, String sequence, String line)
	{
		return new DeleteRecordedMaterialListEntry(ticketNumber, eventId, sequence, line);
	}	
}
