package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 * Service Detail's presenter interface.
 * 
 */
public interface DetailsPresenter extends BasePresenter {

	interface GridView extends BaseView {
		GridPanel getDetailsGrid();

		String initDetailsGrid(Object[][] details);

		void clearDetailsGrid();
	}

	interface PanelView extends BaseView {
		// Materials Tab
		HasText getTxtSequence();

		HasText getTxtFromProcedure();

		HasName getLstWorkCode();

		HasText getTxtDescription1();

		HasText getTxtDescription2();

		HasText getTxtDescription3();

		HasText getTxtManPower();

		HasText getTxtTimeEstimate();

		HasName getChkWarrantyTask();

		HasName getLstStatus();

		String initMaterialGrid(Object[][] materials);

		// Common
		HasClickHandlers getBtnBack();

		TabPanel getTabPanel();
	}

	void showDetailsGrid(String ticketNumber, String panelNumber);

	void showDetailsPanel(String ticketNumber, String panelNumber,
			String detailId);

}
