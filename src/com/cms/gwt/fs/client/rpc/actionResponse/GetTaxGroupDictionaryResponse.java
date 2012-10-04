package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.TaxGroupDictionary;


public class GetTaxGroupDictionaryResponse extends ActionResponse {

	/** The code dictionary returned from the backend. */
	private TaxGroupDictionary dictionary;
		
	public GetTaxGroupDictionaryResponse(TaxGroupDictionary dictionary)
	{
		this.dictionary = dictionary; 
	}	
				
	public TaxGroupDictionary getTaxGroupDictionary() {
		return dictionary;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}
	
}
