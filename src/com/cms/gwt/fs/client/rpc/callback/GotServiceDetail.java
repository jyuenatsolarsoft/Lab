package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.details.ServiceDetail;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailResponse;

public abstract class GotServiceDetail  extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceDetailResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceDetailResponse result) {
		got(result.getServiceDetail());
	}
	
	/**
	 * Process the ticket details retrieved from the backend.
	 * 
	 * @param detail detail requested in the Action.
	 */
	public abstract void got(ServiceDetail detail);

}
