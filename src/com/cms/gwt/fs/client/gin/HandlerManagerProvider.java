package com.cms.gwt.fs.client.gin;

import com.google.gwt.event.shared.HandlerManager;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class HandlerManagerProvider implements Provider<HandlerManager> {

	@Inject
	public HandlerManagerProvider() {
	}
	
	public HandlerManager get() {
		return new HandlerManager(null);
	}

}
