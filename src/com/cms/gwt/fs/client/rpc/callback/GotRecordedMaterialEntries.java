package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntries;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedMaterialEntriesResponse;

public abstract class GotRecordedMaterialEntries extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetRecordedMaterialEntriesResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetRecordedMaterialEntriesResponse result) {
		got(result.getRecordedMaterialEntries(), result.getWorkHistory());
	}
	
	/**
	 * Process the material entry list retrieved from the backend.
	 * 
	 * @param materialEntries	
	 * @param workHistory the work history. 
	 */
	public abstract void got(RecordedMaterialEntries materialEntries,  WorkHistory workHistory);

}
