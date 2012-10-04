package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.StockLocationDictionary;


public class GetStockLocationDictionaryResponse extends ActionResponse {

	/** The stock location dictionary returned from the backend. */
	private StockLocationDictionary dictionary;
		
	public GetStockLocationDictionaryResponse(StockLocationDictionary dictionary)
	{
		this.dictionary = dictionary; 
	}	
				
	public StockLocationDictionary getStockDictionary() {
		return dictionary;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
}
