package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;


/**
 * A callback interface to handle the result of the Action requested by the UI presenter.
 *
 * After the Action is executed by the ActionServices, the corresponding
 * ActionAsyncCallback will handle the result of the Action and react accordingly.  
 *
 */
public interface ActionAsyncCallback {

	/**
	 * Handle the case when the action is failed.
	 * 
	 * @param caught
	 */
	void onFailure(Throwable caught);

	/**
	 * Handle the result of the Action performed successfully.
	 * 
	 * @param response The response of the Action performed.
	 */
	void onSuccess(ActionResponse response);

}
