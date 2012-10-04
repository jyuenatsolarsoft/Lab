package com.cms.gwt.fs.client.rpc.action;

import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.notes.NotesPage;

public class SaveNotesPage implements Action 
{
	private Notes.NotesType notesType;
	private NotesPage notesPage;
	private String ticketNumber;
	
	protected SaveNotesPage()
	{
		// empty constructor for child classes.
	}
	
	protected SaveNotesPage(String ticketNumber, Notes.NotesType type, NotesPage page)
	{
		this.notesPage = page;
		this.notesType = type;
		this.ticketNumber = ticketNumber;
	}
	
	public Notes.NotesType getNotesType()
	{
		return notesType;
	}
	
	public NotesPage getNotesPage()
	{
		return notesPage;
	}
	
	public String getTicketNumber()
	{
		return ticketNumber;
	}
		
	public static SaveNotesPage newInstance(String ticketNumber, Notes.NotesType type, NotesPage page)
	{
		return new SaveNotesPage(ticketNumber, type, page);
	}
}
