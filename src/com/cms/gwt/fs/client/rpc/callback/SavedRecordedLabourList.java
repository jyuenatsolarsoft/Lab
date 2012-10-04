package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.SaveRecordedLabourListResponse;

public class SavedRecordedLabourList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((SaveRecordedLabourListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(SaveRecordedLabourListResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
