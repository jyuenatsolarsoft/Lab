package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetCodeDescriptionResponse;

public abstract class GotCodeDescription extends BaseActionCallback implements ActionAsyncCallback 
{

    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetCodeDescriptionResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetCodeDescriptionResponse result) {
		got(result.getCodeDictionary());
	}
	
	/**
	 * Process the code and descriptions retrieved from backend.
	 * 
	 * @param eventList  The service ticket detail list requested in the Action.
	 */
	public abstract void got(CodeDictionary codeDictionary);
}	
