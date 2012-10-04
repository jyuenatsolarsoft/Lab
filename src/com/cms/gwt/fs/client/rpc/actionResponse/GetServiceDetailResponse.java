package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.details.ServiceDetail;

public class GetServiceDetailResponse extends ActionResponse {

	/** The ticket list returned from the backend. */
	private ServiceDetail detail;
		
	public GetServiceDetailResponse(ServiceDetail detail)
	{
		this.detail = detail; 
	}	
				
	public ServiceDetail getServiceDetail() {
		return detail;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}
