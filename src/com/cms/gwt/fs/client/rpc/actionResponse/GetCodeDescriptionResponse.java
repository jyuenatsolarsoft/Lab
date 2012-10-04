package com.cms.gwt.fs.client.rpc.actionResponse;

import com.cms.gwt.fs.client.model.CodeDictionary;

public class GetCodeDescriptionResponse extends ActionResponse {

	/** The code dictionary returned from the backend. */
	private CodeDictionary dictionary;
		
	public GetCodeDescriptionResponse(CodeDictionary dictionary)
	{
		this.dictionary = dictionary; 
	}	
				
	public CodeDictionary getCodeDictionary() {
		return dictionary;
	}

	public void throwEvents() {
		// Auto-generated method stub
		
	}

}
