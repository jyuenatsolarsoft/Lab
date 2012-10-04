package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.material.RecordedMaterial;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialResponse;

public abstract class GotRecordedMaterial extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetRecordedMaterialResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetRecordedMaterialResponse result) {
		got(result.getEntry(), result.getWorkHistory());
	}
	
	/**
	 * Process the recorded material retrieved from the backend.
	 * 
	 * @param materialList 	
	 * @param workHistory the work history. 
	 */
	public abstract void got(RecordedMaterial recordedMaterial,  WorkHistory workHistory);

}
