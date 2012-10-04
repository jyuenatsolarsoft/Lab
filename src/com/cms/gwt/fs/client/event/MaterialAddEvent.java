package com.cms.gwt.fs.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class MaterialAddEvent extends GwtEvent<MaterialAddEventHandler> {

	public static final GwtEvent.Type<MaterialAddEventHandler> TYPE = new GwtEvent.Type<MaterialAddEventHandler>();
	private final String ticketNumber;
	private final String panelNumber;
	private final String eventId;
	private final String tabNumber;
	private final boolean isEdit;

	public MaterialAddEvent(String ticketNumber, String panelNumber,
			String eventId, String tabNumber, boolean isEdit) {
		this.ticketNumber = ticketNumber;
		this.panelNumber = panelNumber;
		this.eventId = eventId;
		this.tabNumber = tabNumber;
		this.isEdit = isEdit;
	}

	@Override
	protected void dispatch(MaterialAddEventHandler handler) {
		handler.showMaterial(ticketNumber, panelNumber, eventId, tabNumber, isEdit);
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<MaterialAddEventHandler> getAssociatedType() {
		return TYPE;
	}

}