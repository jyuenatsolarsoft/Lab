package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.material.ServiceDetailMaterialList;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceDetailMaterialListResponse;

public abstract class GotServiceDetailMaterialList extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess (ActionResponse response)
	{	
		onSuccess((GetServiceDetailMaterialListResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetServiceDetailMaterialListResponse result) {
		got(result.getTicketNumber(), result.getMaterialList());
	}
	
	/**
	 * Process the material list retrieved from the backend.
	 * 
	 * @param materialList  The material list requested in the Action.
	 */
	public abstract void got(String ticketNumber, ServiceDetailMaterialList materialList);

}
