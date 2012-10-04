package com.cms.gwt.fs.client.rpc.actionResponse;


public class GetShipToBillToResponse extends ActionResponse 
{	
	private String customerName;
		
	public GetShipToBillToResponse(String customerName)
	{
		this.customerName = customerName; 
	}	
				
	public String getCustomerName() {
		return customerName;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
}