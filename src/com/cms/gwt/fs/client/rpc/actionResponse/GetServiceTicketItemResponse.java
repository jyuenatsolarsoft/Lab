package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.item.ServiceTicketItem;

/**
 * Response for the action GetServiceTicketItem.
 *
 */
public class GetServiceTicketItemResponse  extends ActionResponse {
	
	private ServiceTicketItem item;
	
	public GetServiceTicketItemResponse(ServiceTicketItem item)
	{
		this.item = item;
	}
				
	public ServiceTicketItem getTicketItem() {
		return item;
	}	
	
	@Override
	public void throwEvents() {
		// Auto-generated method stub
		
	}	
}