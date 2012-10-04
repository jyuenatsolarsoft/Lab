package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.material.ServiceDetailMaterialList;

public class GetServiceDetailMaterialListResponse extends ActionResponse {

	/** 
	 * The materials list from the backend. It contains the materials for
	 * a specific task/detail.
	 *  
	 */
	private ServiceDetailMaterialList materialList;
	
	private String ticketNumber;
		
	protected GetServiceDetailMaterialListResponse(ServiceDetailMaterialList materialList, String ticketNumber)
	{
		this.materialList = materialList;
		this.ticketNumber = ticketNumber;
	}
				
	public ServiceDetailMaterialList getMaterialList() {
		return materialList;
	}
	
	public String getTicketNumber() {
		return ticketNumber;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
	public static GetServiceDetailMaterialListResponse newInstance(ServiceDetailMaterialList materialList, String ticketNumber)
	{
		return new GetServiceDetailMaterialListResponse(materialList, ticketNumber);
	}
}
