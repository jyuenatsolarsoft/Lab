package com.cms.gwt.fs.client.rpc.action;

import java.util.Map;

import com.cms.gwt.fs.client.notes.Notes;

/**
 * This Action retrieves various types of notes from the backend.
 *
 */
public class GetNotes implements Action 
{		
	/** The Map key is the key field name and the corresponding Map value is the key field value of the Notes. */
	private Map<String, String> keys;
	
	/** Type of the notes. */	
	private Notes.NotesType type;
	
 	
	protected GetNotes()
	{
		// empty constructor for child classes.
	}
	
	protected GetNotes(Notes.NotesType type, Map<String, String> keys)
	{
		this.type = type;
		this.keys = keys;
	}
	
	public Notes.NotesType getType() 
	{
		return type;
	}	
	
	public Map<String, String> getKeys()
	{
		return keys;
	}
	
	public static GetNotes newInstance(Notes.NotesType type, Map<String, String> keys)
	{
		return new GetNotes(type, keys);
	}	
}
		
	

