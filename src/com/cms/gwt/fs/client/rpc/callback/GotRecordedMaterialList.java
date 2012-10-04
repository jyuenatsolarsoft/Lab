package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.material.RecordedMaterialList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialListResponse;

public abstract class GotRecordedMaterialList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetRecordedMaterialListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetRecordedMaterialListResponse result) {
		got(result.getMaterialList(), result.getWorkHistory());
	}
	
	/**
	 * Process the recorded material list retrieved from the backend.
	 * 
	 * @param materialList 	
	 * @param workHistory the work history. 
	 */
	public abstract void got(RecordedMaterialList materialList,  WorkHistory workHistory);

}
