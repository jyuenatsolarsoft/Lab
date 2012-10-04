package com.cms.gwt.fs.client.rpc.action;

public class GetRecordedMaterial extends GetServiceTicketProperties 
{
	private String eventId;
	private String sequence;
	private String line;
	
 	protected GetRecordedMaterial(String ticketNumber, String eventId, String sequence, String line)
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
 	 	
 	
 	public String getSequence() 
 	{
		return sequence;
	}

	public String getLine() 
	{
		return line;
	}
	
	public static GetRecordedMaterial newInstance(String ticketNumber, String eventId, String sequence, String line)
 	{
 		return new GetRecordedMaterial(ticketNumber, eventId, sequence, line);
 	} 		
}
