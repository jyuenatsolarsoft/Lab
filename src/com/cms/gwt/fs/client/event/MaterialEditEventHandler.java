package com.cms.gwt.fs.client.event;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.google.gwt.event.shared.EventHandler;

public interface MaterialEditEventHandler extends EventHandler {

	void showMaterialEdit(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType);

	void showMaterialAddEdit(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry);
}
