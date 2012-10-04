package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.AddRecordedMaterialsResponse;

public class AddedRecordedMaterials extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{				
		onSuccess((AddRecordedMaterialsResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(AddRecordedMaterialsResponse result) {
		// do nothing for now.
		// this is needed for the child class to overload this.
	}	
}
