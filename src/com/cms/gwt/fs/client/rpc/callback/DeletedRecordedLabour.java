package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteRecordedLabourResponse;

public class DeletedRecordedLabour extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((DeleteRecordedLabourResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(DeleteRecordedLabourResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
