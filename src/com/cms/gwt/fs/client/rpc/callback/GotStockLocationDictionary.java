package com.cms.gwt.fs.client.rpc.callback;

import com.cms.gwt.fs.client.model.StockLocationDictionary;
import com.cms.gwt.fs.client.rpc.actionResponse.ActionResponse;
import com.cms.gwt.fs.client.rpc.actionResponse.GetStockLocationDictionaryResponse;

public abstract class GotStockLocationDictionary extends BaseActionCallback implements ActionAsyncCallback 
{
    /**
     * {@inheritDoc}
     */
	public void onSuccess(ActionResponse response)
	{	
		onSuccess((GetStockLocationDictionaryResponse)response);
	}
		
	/**
	 * 
	 * @param result
	 */
	protected void onSuccess(GetStockLocationDictionaryResponse result) {
		got(result.getStockDictionary());
	}
	
	/**
	 * Process the Stock code and descriptions retrieved from backend.
	 * 
	 * @param codeDictionary Dictionary containing the stock code and descriptions.
	 */
	public abstract void got(StockLocationDictionary codeDictionary);
}	
