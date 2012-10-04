package com.cms.gwt.fs.client.view.skills;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * Skill tab within Skills Panel
 * 
 */
public class SkillTab extends Composite implements BaseView {

	private final FlexTable panel;
	private final TextConstants txtConsts;
	
	private final HasHTML lblSkill;
	private final HasName lstSkill;
	private final HasText txtSkill1;
	private final HasText txtSkill2;
	private final HasText txtSkill3;
	private final HasHTML lblLevelRequired;
	private final HasText txtLevelRequired;
	
	/**
	 * Constructor.
	 */
	public SkillTab() {
		panel = new FlexTable();
		initWidget(panel);

		txtConsts = (TextConstants) GWT.create(TextConstants.class);
		
		lblSkill = new HTML(txtConsts.SkillsSkill());
		lstSkill = new ListBox();
		txtSkill1 = new TextBox();
		txtSkill2 = new TextBox();
		txtSkill3 = new TextBox();
		lblLevelRequired = new HTML(txtConsts.SkillsLevelRequired());
		txtLevelRequired = new TextBox();
		
		layout();
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
		((ListBox) lstSkill).clear();
		((TextBox) txtSkill1).setText("");
		((TextBox) txtSkill2).setText("");
		((TextBox) txtSkill3).setText("");
		((TextBox) txtLevelRequired).setText("");			
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((ListBox) lstSkill).setEnabled(enabled);
		((TextBox) txtSkill1).setReadOnly(readOnly);
		((TextBox) txtSkill2).setReadOnly(readOnly);
		((TextBox) txtSkill3).setReadOnly(readOnly);
		((TextBox) txtLevelRequired).setReadOnly(readOnly);
	}

	public HasName getLstSkill() {
		return lstSkill;
	}
	
	public HasText getTxtSkill1() {
		return txtSkill1;
	}

	public HasText getTxtSkill2() {
		return txtSkill2;
	}

	public HasText getTxtSkill3() {
		return txtSkill3;
	}

	public HasText getTxtLevelRequired() {
		return txtLevelRequired;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		panel.addStyleName("SkillsPanelSkillsTab");
		panel.getColumnFormatter().setWidth(0, "150px");
		panel.getColumnFormatter().setWidth(1, "180px");

		int row = 0, column = 0;
		panel.setWidget(row, column, (Widget) lblSkill);
		column++;
		panel.setWidget(row, column, (Widget) lstSkill);
		row++;
		panel.setWidget(row, column, (Widget) txtSkill1);
		row++;
		panel.setWidget(row, column, (Widget) txtSkill2);
		row++;
		panel.setWidget(row, column, (Widget) txtSkill3);
		column = 0;
		row++;
		panel.setWidget(row, column, (Widget) lblLevelRequired);
		column++;
		panel.setWidget(row, column, (Widget) txtLevelRequired);			
	}
}
