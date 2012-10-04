package com.cms.gwt.fs.client.rpc.action;

public class GetSkillsCodeInfo implements Action {

	private String skillsCode;

	@SuppressWarnings("unused")
	/**
	 * Disabled the default constructor.
	 */
	private GetSkillsCodeInfo() {
		// Empty default constructor.
	}
		
	/**
	 * Constructor.
	 * 
	 * @param skillsCode 
	 */
	protected GetSkillsCodeInfo(String skillsCode) {
		this.skillsCode = skillsCode;
	}
	
	public String getSkillsCode() {
		return skillsCode;
	}
	
	public static GetSkillsCodeInfo newInstance(String skillsCode)
	{
		return new GetSkillsCodeInfo(skillsCode);
	}
}
