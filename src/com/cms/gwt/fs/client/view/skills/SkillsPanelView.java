package com.cms.gwt.fs.client.view.skills;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.SkillsPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class SkillsPanelView extends Composite implements
		SkillsPresenter.PanelView {

	private final VerticalPanel panel;
	private final DecoratedTabPanel tabPanel;
	private final TextConstants txtConsts;

	private final SkillTab skillTab;
	private final HasClickHandlers btnBack;

	/**
	 * Constructor.
	 */
	@Inject
	public SkillsPanelView(SkillTab skillTab) {
		panel = new VerticalPanel();
		panel.addStyleName("SkillsPanel");
		initWidget(panel);

		tabPanel = new DecoratedTabPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.skillTab = skillTab;

		btnBack = new Button(txtConsts.Back());

		layout();

		// select first tab by-default
		tabPanel.selectTab(0);

		// read-only be default
		setReadOnly(true);
	}

	/**
	 * {@inheritDoc}
	 */
	public Widget getWidget() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset() {
		skillTab.reset();

		tabPanel.selectTab(0);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		skillTab.setReadOnly(readOnly);
	}

	public HasName getLstSkill() {
		return skillTab.getLstSkill();
	}

	public HasText getTxtSkill1() {
		return skillTab.getTxtSkill1();
	}

	public HasText getTxtSkill2() {
		return skillTab.getTxtSkill2();
	}

	public HasText getTxtSkill3() {
		return skillTab.getTxtSkill3();
	}

	public HasText getTxtLevelRequired() {
		return skillTab.getTxtLevelRequired();
	}

	public HasClickHandlers getBtnBack() {
		return btnBack;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		panel.add(tabPanel);
		panel.add((Widget) btnBack);

		tabPanel.add(skillTab.getWidget(), txtConsts.SkillsSkill());
	}
}
