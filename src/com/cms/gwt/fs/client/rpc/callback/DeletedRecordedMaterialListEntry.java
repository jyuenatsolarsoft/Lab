package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.DeleteRecordedMaterialListEntryResponse;

public class DeletedRecordedMaterialListEntry extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((DeleteRecordedMaterialListEntryResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(DeleteRecordedMaterialListEntryResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
