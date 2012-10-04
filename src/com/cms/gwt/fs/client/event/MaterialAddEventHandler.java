package com.cms.gwt.fs.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface MaterialAddEventHandler extends EventHandler {

	void showMaterial(String ticketNumber, String panelNumber, String eventId,
			String tabNumber, boolean isEdit);	
	
}