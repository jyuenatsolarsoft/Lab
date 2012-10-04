package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;

public interface TimeEntryEditPresenter extends BasePresenter {

	interface View extends BaseView {
		void setType(boolean isAdd);
		
		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		HasText getTxtCounterReading();

		HasText getTxtTechnician();

		HasText getTxtTechnicianDescription();

		HasText getTxtArrivalDate();

		HasText getTxtArrivalTime();

		HasText getTxtSequence();

		HasText getTxtDescription();

		HasText getTxtEstimate();

		HasText getTxtEntered();

		HasText getTxtActual();

		HasText getTxtLine();

		HasText getTxtTime();

		HasName getChkOvertime();

		HasName getChkWarranty();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnCancel();
	}

	void showTimeEntry(String ticketNumber, String panelNumber, String eventId,
			String tabNumber, String sequence, String line, boolean isAdd);
	
}
