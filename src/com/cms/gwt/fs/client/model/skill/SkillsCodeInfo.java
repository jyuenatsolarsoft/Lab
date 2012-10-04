package com.cms.gwt.fs.client.model.skill;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * SkillsCodeInfo contains the 3 line description of the skill code.
 * 
 *
 */
public class SkillsCodeInfo extends FSModel {

	private String skillsCode;
	private String description1; 
	private String description2;
	private String description3;
	
	public String getSkillsCode() {
		return skillsCode;
	}
	public void setSkillsCode(String skillsCode) {
		this.skillsCode = skillsCode;
	}
	public String getDescription1() {
		return description1;
	}
	public void setDescription1(String description1) {
		this.description1 = description1;
	}
	public String getDescription2() {
		return description2;
	}
	public void setDescription2(String description2) {
		this.description2 = description2;
	}
	public String getDescription3() {
		return description3;
	}
	public void setDescription3(String description3) {
		this.description3 = description3;
	}
	
	
}
