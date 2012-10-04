package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.AddRecordedLabourListResponse;

public class AddedRecordedLabourList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((AddRecordedLabourListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(AddRecordedLabourListResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
