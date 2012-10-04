package com.cms.gwt.fs.client.model.skill;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Contains name of the skills required for a service ticket.
 *
 */
public class RequiredSkillsList extends FSModel {

	private List<RequiredSkillsListEntry> skillsList = new ArrayList<RequiredSkillsListEntry>(); 
	
	public void addRequiredSkillsListEntry(RequiredSkillsListEntry entry)
	{
		skillsList.add(entry);
	}
	
	public List<RequiredSkillsListEntry> getRequiredSkillsListEntries()
	{
		return skillsList;
	}
}
