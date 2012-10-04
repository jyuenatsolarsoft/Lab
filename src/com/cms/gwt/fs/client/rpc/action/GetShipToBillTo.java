package com.cms.gwt.fs.client.rpc.action;


public class GetShipToBillTo implements Action {

	private String customerCode;
	private String plant;

	@SuppressWarnings("unused")
	/**
	 * Disabled the default constructor.
	 */
	private GetShipToBillTo() {
		// Empty default constructor.
	}
		
	/**
	 * Constructor.
	 * 
	 * @param skillsCode 
	 */
	protected GetShipToBillTo(String customerCode, String plant) {
		this.customerCode = customerCode;
		this.plant = plant;
	}
	
	public String getCustomerCode()
	{
		return customerCode;
	}

	public String getPlant()
	{
		return plant;
	}
	
	public static GetShipToBillTo newInstance(String customerCode, String plant)
	{
		return new GetShipToBillTo(customerCode, plant);
	}
}
