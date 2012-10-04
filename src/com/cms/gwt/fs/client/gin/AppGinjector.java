package com.cms.gwt.fs.client.gin;

import com.cms.gwt.fs.client.presenter.MainPresenter;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules(AppModule.class)
public interface AppGinjector extends Ginjector {

	MainPresenter getMainPresenter();
	
	ActionServices getActionServices();

}
