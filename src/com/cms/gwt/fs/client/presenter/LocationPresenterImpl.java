package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.location.ServiceTicketLocation;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetServiceTicketLocation;
import com.cms.gwt.fs.client.rpc.action.GetShipToBillTo;
import com.cms.gwt.fs.client.rpc.callback.GotServiceTicketLocation;
import com.cms.gwt.fs.client.rpc.callback.GotShipToBillTo;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.ListBox;
import com.google.inject.Inject;

/**
 * 
 * Location presenter's implementation
 */
public class LocationPresenterImpl extends BasePresenterImpl implements LocationPresenter {

	private final View view;	

	/**
	 * Constructor.
	 * @param view
	 */
	@Inject
	public LocationPresenterImpl(View view) {
		this.view = view;
	}

	/**
	 * {@inheritDoc}
	 */
	public void showLocation(String ticketNumber) {		
		getLocation(ticketNumber);
	}
	
	/**
	 * {@inheritDoc}
	 */	
	public void setLocationType(boolean isInternalAsset)
	{
		setViewType(isInternalAsset);
	}
	
	/**
	 * Set the view type. It should either be CUSTOMER type or INTERNAL_ASSET type.
	 * 
	 * 
	 * @param isInternalAsset
	 */
	protected void setViewType(boolean isInternalAsset)
	{
		if (isInternalAsset)
		{
			view.showType(View.INTERNAL_ASSET_TYPE);
		}
		else
		{
			view.showType(View.CUSTOMER_TYPE);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BaseView getView() {
		return view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public BaseView getPanelView() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setVisible(boolean visible) {
		view.getWidget().setVisible(visible);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setPanelVisible(boolean visible) {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		resetView();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
	}
	
	/**
	 * Make RPC to retrieve location's data.
	 */
	private void getLocation(String ticketNumber) 
	{
		getLocation(new GetServiceTicketLocation(ticketNumber));
	}

	/**
	 * Overloaded call to make RPC to get ticket data. Assign a call-back
	 * function.
	 */
	private void getLocation(GetServiceTicketLocation action) {
		
		try
		{
			ActionServices.App.getInstance().execute(action,
				new GetTicketLocationCallback());
		}
		catch (ActionNotSupportedException e)
		{			
			final String ERR_MSG = "Unable to retrieve the ticket location.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
			
	}
	
	
	/**
	 * Get the customer site name for the specified customer site code.
	 * @param ticket
	 */
	private void getCustomerSiteDesc(ServiceTicketLocation location)
	{				
		try
		{
			ActionServices.App.getInstance().execute(GetShipToBillTo.newInstance(location.getCustomerSite(), getCurrPlant()),
					new GetCustomerSiteDescCallback());
		}
		catch (ActionNotSupportedException e)
		{
			final String ERR_MSG = "Unable to get the customer site description.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}
	

	/**
	 * Display location data on screen.
	 */
	private void render(ServiceTicketLocation location) {		
				
		view.getTxtCustomerNumber().setText(location.getCustomer());
		
		view.getTxtInHousePlant().setText(location.getInHousePlant());
		view.getTxtAddressLine1().setText(location.getAddressLine()[0]);
		view.getTxtAddressLine2().setText(location.getAddressLine()[1]);
		view.getTxtAddressLine3().setText(location.getAddressLine()[2]);
		view.getTxtAddressLine4().setText(location.getAddressLine()[3]);
		view.getTxtAddressLine5().setText(location.getAddressLine()[4]);
		view.getTxtAddressLine6().setText(location.getAddressLine()[5]);
				
		view.getTxtCustomerAddress1().setText(location.getAddressLine()[0]);
		view.getTxtCustomerAddress2().setText(location.getAddressLine()[1]);
		view.getTxtCustomerAddress3().setText(location.getAddressLine()[2]);
		view.getTxtCustomerAddress4().setText(location.getAddressLine()[3]);
		view.getTxtCustomerAddress5().setText(location.getAddressLine()[4]);
		view.getTxtCustomerAddress6().setText(location.getAddressLine()[5]);
			
		populateListBox(((ListBox) view.getLstCustomerTerritory()), CodeDictionaryFactory.TERRITORY_CODE, location.getTerritory());		
		
		view.getTxtCustomerTerritory1().setText(location.getSiteLine()[0]);
		view.getTxtCustomerTerritory2().setText(location.getSiteLine()[1]);
		view.getTxtCustomerTerritory3().setText(location.getSiteLine()[2]);
		view.getTxtCustomerTerritory4().setText(location.getSiteLine()[3]);
		view.getTxtCustomerTerritory5().setText(location.getSiteLine()[4]);
		view.getTxtCustomerTerritory6().setText(location.getSiteLine()[5]);
		
	}
	
	/**
	 * Display location data on screen.
	 */
	private void renderCustomerSite(String customerSiteDesc) 
	{		
		view.getTxtCustomerSite().setText(customerSiteDesc);				
	}
	

	/**
	 * Reset the location view.
	 */
	private void resetView() {
		view.reset();
	}		
	

	/**
	 * Call-Back class to get location data
	 * 
	 */
	class GetTicketLocationCallback extends GotServiceTicketLocation {
		public void got(ServiceTicketLocation location) {
			render(location);
			
			getCustomerSiteDesc(location);
		}
	}
	
	/**
	 * Call-Back class to get location data
	 * 
	 */
	class GetCustomerSiteDescCallback extends GotShipToBillTo {
		public void got(String customerSiteDesc) {
			renderCustomerSite(customerSiteDesc);
		}
	}		

}
