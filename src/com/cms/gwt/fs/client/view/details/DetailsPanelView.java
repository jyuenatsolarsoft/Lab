package com.cms.gwt.fs.client.view.details;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.DetailsPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratedTabPanel;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Panel view of the Services Detail
 * 
 */
public class DetailsPanelView extends Composite implements
		DetailsPresenter.PanelView {

	private final VerticalPanel panel;
	private final DecoratorPanel decoratorPanel;
	private final FlexTable flexTable;
	private final DecoratedTabPanel tabPanel;
	private final TextConstants txtConsts;

	private final MaterialTab materialTab;

	private final HasHTML lblSequence;
	private final HasText txtSequence;
	private final HasHTML lblFromProcedure;
	private final HasText txtFromProcedure;
	private final HasHTML lblWorkCode;
	private final HasName lstWorkCode;
	private final HasHTML lblDescription;
	private final HasText txtDescription1;
	private final HasText txtDescription2;
	private final HasText txtDescription3;
	private final HasHTML lblManPower;
	private final HasText txtManPower;
	private final HasHTML lblTimeEstimate;
	private final HasText txtTimeEstimate;
	private final HasHTML lblWarrantyTask;
	private final HasName chkWarrantyTask;
	private final HasHTML lblStatus;
	private final HasName lstStatus;
	private final HasClickHandlers btnBack;

	/**
	 * Constructor.
	 */
	@Inject
	public DetailsPanelView(MaterialTab materialTab) {
		panel = new VerticalPanel();
		panel.addStyleName("DetailsPanel");
		initWidget(panel);

		decoratorPanel = new DecoratorPanel();
		flexTable = new FlexTable();
		tabPanel = new DecoratedTabPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		this.materialTab = materialTab;

		lblSequence = new HTML(txtConsts.DetailsSequence());
		txtSequence = new TextBox();
		lblFromProcedure = new HTML(txtConsts.DetailsFromProcedure());
		txtFromProcedure = new TextBox();
		lblWorkCode = new HTML(txtConsts.DetailsWorkCode());
		lstWorkCode = new ListBox();
		lblDescription = new HTML(txtConsts.DetailsDescription());
		txtDescription1 = new TextBox();
		txtDescription2 = new TextBox();
		txtDescription3 = new TextBox();
		lblManPower = new HTML(txtConsts.DetailsManPower());
		txtManPower = new TextBox();
		lblTimeEstimate = new HTML(txtConsts.DetailsTimeEstimate());
		txtTimeEstimate = new TextBox();
		lblWarrantyTask = new HTML(txtConsts.DetailsWarrantyTask());
		chkWarrantyTask = new CheckBox();
		lblStatus = new HTML(txtConsts.DetailsStatus());
		lstStatus = new ListBox();
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
		((TextBox) txtSequence).setText("");
		((TextBox) txtFromProcedure).setText("");
		((ListBox) lstWorkCode).clear();
		((TextBox) txtDescription1).setText("");
		((TextBox) txtDescription2).setText("");
		((TextBox) txtDescription3).setText("");
		((TextBox) txtManPower).setText("");
		((TextBox) txtTimeEstimate).setText("");
		((CheckBox) chkWarrantyTask).setValue(false);
		((ListBox) lstStatus).clear();

		materialTab.reset();
		tabPanel.selectTab(0);
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;

		((TextBox) txtSequence).setReadOnly(readOnly);
		((TextBox) txtFromProcedure).setReadOnly(readOnly);
		((ListBox) lstWorkCode).setEnabled(enabled);
		((TextBox) txtDescription1).setReadOnly(readOnly);
		((TextBox) txtDescription2).setReadOnly(readOnly);
		((TextBox) txtDescription3).setReadOnly(readOnly);
		((TextBox) txtManPower).setReadOnly(readOnly);
		((TextBox) txtTimeEstimate).setReadOnly(readOnly);
		((CheckBox) chkWarrantyTask).setEnabled(enabled);
		((ListBox) lstStatus).setEnabled(enabled);

		materialTab.setReadOnly(readOnly);
	}

	public HasText getTxtSequence() {
		return txtSequence;
	}

	public HasText getTxtFromProcedure() {
		return txtFromProcedure;
	}

	public HasName getLstWorkCode() {
		return lstWorkCode;
	}

	public HasText getTxtDescription1() {
		return txtDescription1;
	}

	public HasText getTxtDescription2() {
		return txtDescription2;
	}

	public HasText getTxtDescription3() {
		return txtDescription3;
	}

	public HasText getTxtManPower() {
		return txtManPower;
	}

	public HasText getTxtTimeEstimate() {
		return txtTimeEstimate;
	}

	public HasName getChkWarrantyTask() {
		return chkWarrantyTask;
	}

	public HasName getLstStatus() {
		return lstStatus;
	}

	public String initMaterialGrid(Object[][] materials) {
		return materialTab.initMaterialGrid(materials);
	}

	public HasClickHandlers getBtnBack() {
		return btnBack;
	}

	public TabPanel getTabPanel() {
		return tabPanel;
	}

	/**
	 * Arrange widgets in a nice layout
	 */
	private void layout() {
		panel.add(decoratorPanel);
		panel.add(tabPanel);
		panel.add((Widget) btnBack);

		decoratorPanel.add(flexTable);

		flexTable.addStyleName("DetailsPanelHeader");
		flexTable.getColumnFormatter().setWidth(0, "150px");
		flexTable.getColumnFormatter().setWidth(1, "180px");
		flexTable.getColumnFormatter().setWidth(2, "150px");
		flexTable.getColumnFormatter().setWidth(3, "180px");

		int row = 0, column = 0;
		flexTable.setWidget(row, column, (Widget) lblSequence);
		column++;
		flexTable.setWidget(row, column, (Widget) txtSequence);
		column++;
		flexTable.setWidget(row, column, (Widget) lblFromProcedure);
		column++;
		flexTable.setWidget(row, column, (Widget) txtFromProcedure);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblWorkCode);
		column++;
		flexTable.setWidget(row, column, (Widget) lstWorkCode);
		column++;
		flexTable.setWidget(row, column, (Widget) lblDescription);
		column++;
		flexTable.setWidget(row, column, (Widget) txtDescription1);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblManPower);
		column++;
		flexTable.setWidget(row, column, (Widget) txtManPower);
		column += 2;
		flexTable.setWidget(row, column, (Widget) txtDescription2);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblTimeEstimate);
		column++;
		flexTable.setWidget(row, column, (Widget) txtTimeEstimate);
		column += 2;
		flexTable.setWidget(row, column, (Widget) txtDescription3);
		column = 0;
		row++;
		flexTable.setWidget(row, column, (Widget) lblWarrantyTask);
		column++;
		flexTable.setWidget(row, column, (Widget) chkWarrantyTask);
		column++;
		flexTable.setWidget(row, column, (Widget) lblStatus);
		column++;
		flexTable.setWidget(row, column, (Widget) lstStatus);

		tabPanel.add(materialTab.getWidget(), txtConsts.DetailsMaterial());
	}
}
