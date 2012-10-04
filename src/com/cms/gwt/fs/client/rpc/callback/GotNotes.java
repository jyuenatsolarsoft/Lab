package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.notes.Notes;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetNotesResponse;

public abstract class GotNotes extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetNotesResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetNotesResponse result) {
		got(result.getNotes());
	}
	
	/**
	 * Process the notes retrieved from the backend.
	 * 
	 * @param notes 
	 */
	public abstract void got(Notes notes);

}
