package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.details.ServiceDetailList;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailListResponse;

public abstract class GotServiceDetailList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceDetailListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceDetailListResponse result) {
		got(result.getTicketNumber(), result.getDetailList());
	}
	
	/**
	 * Process the detail list retrieved from the backend.
	 * 
	 * @param detailList  The service ticket detail list requested in the Action.
	 */
	public abstract void got(String ticketNumber, ServiceDetailList detailList);

}
