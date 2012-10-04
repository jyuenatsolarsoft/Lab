package com.cms.gwt.fs.client.presenter;

import com.google.gwt.event.dom.client.HasClickHandlers;

public interface MaterialAddPresenter extends MaterialPresenter {

	interface View extends MaterialPresenter.View {
		HasClickHandlers getBtnSave();

		HasClickHandlers getBtnAllMaterial();

		HasClickHandlers getBtnEdit();
	}
	
	void showMaterial(String ticketNumber, String panelNumber, String eventId,
			String tabNumber, boolean isEdit);

}
