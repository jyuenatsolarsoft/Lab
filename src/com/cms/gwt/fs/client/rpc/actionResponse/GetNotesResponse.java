package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.notes.Notes;

public class GetNotesResponse extends ActionResponse 
{	
	private Notes notes;		
		
	public GetNotesResponse(Notes notes)
	{
		this.notes = notes; 
	}	
	
	public Notes getNotes() 
	{
		return notes;
	}	
	
	public void throwEvents() {
		// Auto-generated method stub
		
	}
}