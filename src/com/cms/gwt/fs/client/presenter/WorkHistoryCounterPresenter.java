package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface WorkHistoryCounterPresenter extends BasePresenter {

	interface View extends BaseView {
		HasText getTxtTicketNumber();

		HasText getTxtCounterDescription();

		HasText getTxtCounterReading();

		HasClickHandlers getBtnSave();

		HasClickHandlers getBtnCancel();
	}

	void showWorkHistoryCounter(String ticketNumber, String panelNumber,
			String eventId, String tabNumber);

}
