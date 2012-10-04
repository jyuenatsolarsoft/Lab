package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.cms.gwt.fs.client.view.accesshours.AccessHoursView.AccessHoursRow;
import com.google.gwt.user.client.ui.HasText;

/**
 * Access Hours presenter interface
 * 
 */
public interface AccessHoursPresenter extends BasePresenter {

	interface View extends BaseView {
		HasText getTxtTimeZone();

		AccessHoursRow getAccessHoursRows(int index);
	}

	/**
	 * Show access hours.
	 * 
	 * @param ticketNumber The ticket number which the access hours are stored under.
	 */
	void showAccessHours(String ticketNumber);

}
