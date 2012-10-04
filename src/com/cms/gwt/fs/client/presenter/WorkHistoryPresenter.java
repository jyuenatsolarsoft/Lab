package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;

public interface WorkHistoryPresenter extends BasePresenter {

	interface View extends BaseView {
		// main panel
		TabPanel getTabPanel();

		HasClickHandlers getBtnCounter();

		HasClickHandlers getBtnTimeEntry();

		HasClickHandlers getBtnMaterial();

		HasClickHandlers getBtnComplete();

		HasClickHandlers getBtnArrivalInfo();

		HasClickHandlers getBtnCancel();

		// summary tab
		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		HasText getTxtCounterReading();

		HasText getTxtTechnician();

		HasText getTxtTechnicianDescription();

		HasText getTxtDate();

		HasText getTxtArrivalTime();

		HasText getTxtHoursReported();

		HasName getChkMaterialReported();

		HasText getTxtNumberOfItems();

		HasName getChkOthersReported();
	}

	void showWorkHistoryPanel(String ticketNumber, String panelNumber,
			String eventId, String tabNumber);
}
