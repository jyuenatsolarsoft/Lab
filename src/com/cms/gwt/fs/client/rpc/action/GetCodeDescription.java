package com.cms.gwt.fs.client.rpc.action;

public class GetCodeDescription implements Action {
		
	protected String codeName;
	protected String type;
		
	protected GetCodeDescription(String codeName)
	{
		this.codeName = codeName;
	}
	
	protected GetCodeDescription(String codeName, String type)
	{
		this.codeName = codeName;
		this.type = type;
	}	
	
	public String getCodeName() {
		return codeName;
	}	

	public String getType() {
		return type;
	}
	
	public static GetCodeDescription newInstance(String dictionaryName)
	{
		return new GetCodeDescription(dictionaryName);
	}
	
	public static GetCodeDescription newInstance(String dictionaryName, String type)
	{
		return new GetCodeDescription(dictionaryName, type);
	}	
}
