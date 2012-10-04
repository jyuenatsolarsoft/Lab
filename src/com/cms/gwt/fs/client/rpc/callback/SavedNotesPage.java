package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveNotesPageResponse;

public class SavedNotesPage extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((SaveNotesPageResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(SaveNotesPageResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
