package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.user.client.ui.DecoratedTabPanel;

public interface NotesPresenter extends BasePresenter {

	interface View extends BaseView {
		DecoratedTabPanel getPanel();		
	}

	void showNotes(String ticketNumber, String panelNumber);
}
