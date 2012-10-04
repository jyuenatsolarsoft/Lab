package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.form.DateField;

public interface WorkHistoryArrivalInfoPresenter extends BasePresenter {

	interface View extends BaseView {
		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		DateField getDfArrivalDate();

		HasText getTxtArrivalTimeHour();

		HasText getTxtArrivalTimeMinute();

		HasClickHandlers getBtnSave();

		HasClickHandlers getBtnCancel();
	}
	
	void showWorkHistoryArrivalInfo(String ticketNumber, String panelNumber,
			String eventId, String tabNumber);
}
