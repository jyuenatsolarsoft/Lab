package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.grid.GridPanel;

public interface MaterialPresenter extends BasePresenter {

	interface View extends BaseView {
		GridPanel getMaterialGrid();

		String initMaterialGrid(Object[][] materials);

		void clearMaterialGrid();

		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		HasText getTxtCounterReading();
		
		HasText getTxtCounterReadingDescription();

		HasText getTxtTechnician();

		HasText getTxtTechnicianDescription();

		HasText getTxtArrivalDate();

		HasText getTxtArrivalTime();

		HasClickHandlers getBtnAdd();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnDelete();

		HasClickHandlers getBtnCancel();
	}

	void showMaterial(String ticketNumber, String panelNumber, String eventId,
			String tabNumber);

}
