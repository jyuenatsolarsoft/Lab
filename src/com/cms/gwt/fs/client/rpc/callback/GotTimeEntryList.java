package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.workHistory.TimeEntryList;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTimeEntryListResponse;

public abstract class GotTimeEntryList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetTimeEntryListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetTimeEntryListResponse result) {
		got(result.getTimeEntryList(), result.getWorkHistory());
	}
	
	/**
	 * Process the time entry list retrieved from the backend.
	 * 
	 * @param event with the same eventId in the	
	 * @param workHistory the work history. 
	 */
	public abstract void got(TimeEntryList timeEntryList,  WorkHistory workHistory);

}
