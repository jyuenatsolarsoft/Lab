package com.cms.gwt.fs.client.event;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.presenter.MaterialEditPresenter.MaterialEditType;
import com.google.gwt.event.shared.GwtEvent;

public class MaterialEditEvent extends GwtEvent<MaterialEditEventHandler> {

	public static final GwtEvent.Type<MaterialEditEventHandler> TYPE = new GwtEvent.Type<MaterialEditEventHandler>();
	private final String ticketNumber;
	private final String panelNumber;
	private final String eventId;
	private final String tabNumber;
	private final String sequence;
	private final String line;
	private final MaterialEditType materialEditType;
	private final WorkHistory workHistory;
	private final RecordedMaterialEntriesEntry recordedMaterialEntry;

	public MaterialEditEvent(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType) {
		this(ticketNumber, panelNumber, eventId, tabNumber, sequence, line,
				materialEditType, null, null);
	}

	public MaterialEditEvent(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry) {
		this.ticketNumber = ticketNumber;
		this.panelNumber = panelNumber;
		this.eventId = eventId;
		this.tabNumber = tabNumber;
		this.sequence = sequence;
		this.line = line;
		this.materialEditType = materialEditType;
		this.workHistory = workHistory;
		this.recordedMaterialEntry = recordedMaterialEntry;
	}

	@Override
	protected void dispatch(MaterialEditEventHandler handler) {
		if (recordedMaterialEntry == null) {
			handler.showMaterialEdit(ticketNumber, panelNumber, eventId,
					tabNumber, sequence, line, materialEditType);

		} else {
			handler.showMaterialAddEdit(ticketNumber, panelNumber, eventId,
					tabNumber, sequence, line, materialEditType, workHistory,
					recordedMaterialEntry);
		}
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MaterialEditEventHandler> getAssociatedType() {
		return TYPE;
	}

}
