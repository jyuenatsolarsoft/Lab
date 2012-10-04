package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 * Service Ticket List presenter interface.
 * 
 */
public interface ServiceTicketListPresenter extends BasePresenter {

	interface View extends BaseView {
		GridPanel getTicketListGrid();

		String initTicketListGrid(Object[][] tickets);

		void clearTicketListGrid();
		
		HTML getOptionsLink();
		
		Label getMapFrameInfo();
		
		Frame getMapFrame();
	}

	void showServiceTicketList();

}
