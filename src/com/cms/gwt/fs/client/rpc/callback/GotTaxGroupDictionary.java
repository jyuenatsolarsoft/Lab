package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.TaxGroupDictionary;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetTaxGroupDictionaryResponse;

public abstract class GotTaxGroupDictionary extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetTaxGroupDictionaryResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetTaxGroupDictionaryResponse result) {
		got(result.getTaxGroupDictionary());
	}
	
	/**
	 * Process the tax code and descriptions retrieved from backend.
	 * 
	 * @param codeDictionary Dictionary containing the tax code and descriptions.
	 */
	public abstract void got(TaxGroupDictionary codeDictionary);
}	
