package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.details.ServiceDetailList;

public class GetServiceDetailListResponse extends ActionResponse {

	/** The detail list returned from the backend. */
	private ServiceDetailList detailList;
	private String ticketNumber;
		
	public GetServiceDetailListResponse(String ticketNumber, ServiceDetailList detailList)
	{
		this.ticketNumber = ticketNumber;
		this.detailList = detailList; 
	}	
			
	public ServiceDetailList getDetailList() {
		return detailList;
	}		

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}



