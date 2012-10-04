package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetWorkHistoryResponse;

public abstract class GotWorkHistory extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetWorkHistoryResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetWorkHistoryResponse result) 
	{
		got(result.getWorkHistory());
	}
	
	/**
	 * Process the work history retrieved from the backend.
	 * 
	 * @param workHistory 
	 */
	public abstract void got(WorkHistory workHistory);

}
