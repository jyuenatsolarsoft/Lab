package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.gwtext.client.widgets.grid.GridPanel;

/**
 * Skills presenter interface.
 * 
 */
public interface SkillsPresenter extends BasePresenter {

	interface GridView extends BaseView {
		GridPanel getSkillsGrid();

		String initSkillsGrid(Object[][] skills);

		void clearSkillsGrid();
	}

	interface PanelView extends BaseView {
		// skills tab
		HasName getLstSkill();

		HasText getTxtSkill1();

		HasText getTxtSkill2();

		HasText getTxtSkill3();

		HasText getTxtLevelRequired();

		// common
		HasClickHandlers getBtnBack();

		TabPanel getTabPanel();
	}

	void showSkillsGrid(String ticketNumber, String panelNumber);

	void showSkillsPanel(String ticketNumber, String panelNumber, String skillId);

}
