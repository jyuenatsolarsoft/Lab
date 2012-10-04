package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.item.ServiceTicketItem;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetServiceTicketItemResponse;

/**
 * Asynchronous Callback class for the action GetServiceTicketItem.
 * 
 * @see ActionAsyncCallback
 */
public abstract class GotServiceTicketItem extends BaseActionCallback implements ActionAsyncCallback {

    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{
		onSuccess((GetServiceTicketItemResponse)response);
	}
	
	protected void onSuccess(GetServiceTicketItemResponse result) {
		got(result.getTicketItem());
	}
	
	/**
	 * Process the ticket item retrieved from the backend.
	 * 
	 * @param ticketItem The ticket item requested in the Action.
	 */	
	public abstract void got(ServiceTicketItem ticketItem);
	
}
