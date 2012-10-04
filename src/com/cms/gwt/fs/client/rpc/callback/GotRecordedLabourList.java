package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.workHistory.RecordedLabourList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetRecordedLabourListResponse;

public abstract class GotRecordedLabourList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetRecordedLabourListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetRecordedLabourListResponse result) {
		got(result.getLabourList(), result.getWorkHistory());
	}
	
	/**
	 * Process the labour list retrieved from the backend.
	 * 
	 * @param labourList 	
	 * @param workHistory the work history. 
	 */
	public abstract void got(RecordedLabourList labourList,  WorkHistory workHistory);

}
