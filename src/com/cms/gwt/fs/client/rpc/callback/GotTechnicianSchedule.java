package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.scheduledEvent.TechnicianSchedule;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTechnicianScheduleResponse;

public abstract class GotTechnicianSchedule extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetTechnicianScheduleResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetTechnicianScheduleResponse result) {
		got(result.getTechnicianSchedule());
	}
	
	/**
	 * Process the technician schedule retrieved from the backend.
	 * 
	 * @param schedule
	 */
	public abstract void got(TechnicianSchedule schedule);

}
