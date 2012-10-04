package com.cms.gwt.fs.client.presenter;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.accessHours.AccessDay;
import com.cms.gwt.fs.client.model.accessHours.AccessHours;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetAccessHours;
import com.cms.gwt.fs.client.rpc.callback.GotAccessHours;
import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.accesshours.AccessHoursView.AccessHoursRow;
import com.google.inject.Inject;

/**
 * Access Hours presenter's implementation. 
 *
 */
public class AccessHoursPresenterImpl extends BasePresenterImpl implements AccessHoursPresenter {

	private final View view;
	
	@Inject
	public AccessHoursPresenterImpl(View view) {
		this.view = view;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void showAccessHours(String ticketNumber) {
		getAccessHours(ticketNumber);
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
	public void reset() {
		resetView();		
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void resetPanel() {
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
	 * Make RPC call here to retrieve access hours.
	 */
	private void getAccessHours(String ticketNumber) {
		getAccessHours(GetAccessHours.newInstance(ticketNumber));		
	}
	
	/**
	 * Overload method to make RPC call here to retrieve access hours.
	 * 
	 * @param action
	 */
	private void getAccessHours(GetAccessHours action)
	{
		try
		{
			ActionServices.App.getInstance().execute(action,
				new GetAccessHoursCallback());
		}
		catch (ActionNotSupportedException e)
		{			
			final String ERR_MSG = "Unable to get the access hours.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());			
		}		
	}
	
	/**
	 * Display access hours data on-screen.
	 */
	private void render(AccessHours accessHours) {
		
		view.getTxtTimeZone().setText(accessHours.getTimeZone());
		
		renderHours(accessHours);
	}


	/**
	 * Helper method to populate the hour boxes.
	 * 
	 * @param accessHours hours to be displayed.
	 */
	private void renderHours(AccessHours accessHours)
	{
		int rowNumber = 0;		
		AccessHoursRow rowWidget;
		AccessDay accessDay;
		
		for (String day : AccessHours.DAYS)
		{
			rowWidget = view.getAccessHoursRows(rowNumber++);
			
			accessDay = accessHours.getAccessDay(day);
				
			rowWidget.setData(accessDay.getRange1().getStartTime(), 
					accessDay.getRange1().getEndTime(),
					accessDay.getRange2().getStartTime(),
					accessDay.getRange2().getEndTime());			
		}		
	}
	
	
	/**
	 * Reset the access hours view
	 */
	private void resetView() {
		view.reset();
	}
	
	/**
	 * Call-Back class to get the access hours and render them.
	 * 
	 */
	class GetAccessHoursCallback extends GotAccessHours {
		public void got(AccessHours accessHours) {
			render(accessHours);
		}
	}		
}
