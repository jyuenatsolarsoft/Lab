package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.UpdateRecordedMaterialResponse;

public abstract class UpdatedRecordedMaterial extends BaseActionCallback implements ActionAsyncCallback
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{
		onSuccess((UpdateRecordedMaterialResponse)response);
	}
	
	protected void onSuccess(UpdateRecordedMaterialResponse result) {
		got(result.getRecordedMaterial());
	}
	
	/**
	 * Got the new/updated scheduled event.
	 * 
	 * @param event The new/updated event.
	 */	
	public abstract void got(RecordedMaterial material);
}
