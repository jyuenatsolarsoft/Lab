package com.cms.gwt.fs.client.model.skill;


/**
 * Contains the information of a skill required for the service ticket.
 *
 */
public class RequiredSkillsListEntry {

	private String skill;
	private String description;
	private String levelRequired;
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLevelRequired() {
		return levelRequired;
	}
	public void setLevelRequired(String levelRequired) {
		this.levelRequired = levelRequired;
	}
	
	
}
