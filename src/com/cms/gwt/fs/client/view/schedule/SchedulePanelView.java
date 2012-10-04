/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Schedule Type will be always read-only and "Normal".
 */

package com.cms.gwt.fs.client.view.schedule;

import com.cms.gwt.common.client.CmsListBox;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.SchedulePresenter;
import com.cms.gwt.fs.client.presenter.SchedulePresenter.SchedulePanelType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.widgets.form.DateField;

public class SchedulePanelView extends Composite implements
		SchedulePresenter.PanelView {

	private final VerticalPanel panel;
	private final DecoratorPanel decoratorPanel;
	private final TextConstants txtConsts;

	private final HasHTML lblEventId;
	private final HasText txtEventId;
	private final HasHTML lblScheduleType;
	private final HasName lstScheduleType;
	private final HasHTML lblParentEventId;
	private final HasText txtParentEventId;
	private final HasHTML lblParentSplitType;
	private final HasName lstParentSplitType;
	private final HasHTML lblManPower;
	private final HasText txtManPower;
	private final HasHTML lblTimeEstimate;
	private final HasText txtTimeEstimate;
	// private final HasHTML lblSpecialEventCode;
	private final HasName lstSpecialEventCode;
	private final HasHTML lblTechnician;
	private final HasName lstTechnician;
	private final HasHTML lblScheduledStart;
	private final DateField dfScheduledStart;
	private final HasText txtScheduledTimeHour;
	private final HasText txtScheduledTimeMinute;
	private final HasHTML lblTravelTimeEstimate;
	private final HasText txtTravelTimeEstimate;
	private final HasHTML lblServiceCategory;
	private final HasName lstServiceCategory;
	private final HasHTML lblOverrideRates;
	private final HasName chkOverrideRates;
	private final HasHTML lblLabourRate;
	private final HasText txtLabourRate;
	private final HasHTML lblOvertimeRate;
	private final HasText txtOvertimeRate;
	private final HasHTML lblStatus;
	private final HasName lstStatus;

	private final HasClickHandlers btnAdd;
	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnCancel;
	private final HasClickHandlers btnBack;

	/**
	 * Constructor.
	 */
	public SchedulePanelView() {
		panel = new VerticalPanel();
		panel.setStyleName("SchedulePanel");
		initWidget(panel);

		decoratorPanel = new DecoratorPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblEventId = new HTML(txtConsts.ScheduleEventId());
		txtEventId = new TextBox();
		lblScheduleType = new HTML(txtConsts.ScheduleScheduleType());
		lstScheduleType = new ListBox();
		lblParentEventId = new HTML(txtConsts.ScheduleParentEventId());
		txtParentEventId = new TextBox();
		lblParentSplitType = new HTML(txtConsts.ScheduleParentSplitType());
		lstParentSplitType = new ListBox();
		lblManPower = new HTML(txtConsts.ScheduleManPower());
		txtManPower = new TextBox();
		lblTimeEstimate = new HTML(txtConsts.ScheduleTimeEstimate());
		txtTimeEstimate = new TextBox();
		// lblSpecialEventCode = new HTML(txtConsts.ScheduleSpecialEventCode());
		lstSpecialEventCode = new CmsListBox(false);
		lblTechnician = new HTML(txtConsts.ScheduleTechnician());
		lstTechnician = new ListBox();
		lblScheduledStart = new HTML(txtConsts.ScheduleScheduledStart());
		dfScheduledStart = new DateField("", "Y-m-d");
		txtScheduledTimeHour = new TextBox();
		txtScheduledTimeMinute = new TextBox();
		lblTravelTimeEstimate = new HTML(txtConsts.ScheduleTravelTimeEstimate());
		txtTravelTimeEstimate = new TextBox();
		lblServiceCategory = new HTML(txtConsts.ScheduleServiceCategory());
		lstServiceCategory = new ListBox();
		lblOverrideRates = new HTML(txtConsts.ScheduleOverrideRates());
		chkOverrideRates = new CheckBox();
		lblLabourRate = new HTML(txtConsts.ScheduleLabourRate());
		txtLabourRate = new TextBox();
		lblOvertimeRate = new HTML(txtConsts.ScheduleOvertimeRate());
		txtOvertimeRate = new TextBox();
		lblStatus = new HTML(txtConsts.ScheduleStatus());
		lstStatus = new ListBox();

		btnAdd = new Button(txtConsts.Add());
		btnUpdate = new Button(txtConsts.Update());
		btnCancel = new Button(txtConsts.Cancel());
		btnBack = new Button(txtConsts.Back());

		hideButtons();
		layout();

		// read-only by default
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
		((TextBox) txtEventId).setText("");
		((ListBox) lstScheduleType).clear();
		((TextBox) txtParentEventId).setText("");
		((ListBox) lstParentSplitType).clear();
		((TextBox) txtManPower).setText("");
		((TextBox) txtTimeEstimate).setText("");
		((ListBox) lstSpecialEventCode).clear();
		((ListBox) lstTechnician).clear();
		dfScheduledStart.setValue("");
		((TextBox) txtScheduledTimeHour).setText("");
		((TextBox) txtScheduledTimeMinute).setText("");
		((TextBox) txtTravelTimeEstimate).setText("");
		((ListBox) lstServiceCategory).clear();
		((CheckBox) chkOverrideRates).setValue(false);
		((TextBox) txtLabourRate).setText("");
		((TextBox) txtOvertimeRate).setText("");
		((ListBox) lstStatus).clear();

		hideButtons();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((TextBox) txtEventId).setReadOnly(readOnly);
		((ListBox) lstScheduleType).setEnabled(enabled);
		((TextBox) txtParentEventId).setReadOnly(readOnly);
		((ListBox) lstParentSplitType).setEnabled(enabled);
		((TextBox) txtManPower).setReadOnly(readOnly);
		((TextBox) txtTimeEstimate).setReadOnly(readOnly);
		((ListBox) lstSpecialEventCode).setEnabled(enabled);
		((ListBox) lstTechnician).setEnabled(enabled);
		dfScheduledStart.setReadOnly(readOnly);
		dfScheduledStart.setDisabled(readOnly);
		((TextBox) txtScheduledTimeHour).setReadOnly(readOnly);
		((TextBox) txtScheduledTimeHour).setEnabled(enabled);
		((TextBox) txtScheduledTimeMinute).setReadOnly(readOnly);
		((TextBox) txtScheduledTimeMinute).setEnabled(enabled);
		((TextBox) txtTravelTimeEstimate).setReadOnly(readOnly);
		((ListBox) lstServiceCategory).setEnabled(enabled);
		((CheckBox) chkOverrideRates).setEnabled(enabled);
		((TextBox) txtLabourRate).setReadOnly(readOnly);
		((TextBox) txtOvertimeRate).setReadOnly(readOnly);
		((ListBox) lstStatus).setEnabled(enabled);

		hideButtons();
	}

	public HasText getTxtEventId() {
		return txtEventId;
	}

	public HasName getLstScheduleType() {
		return lstScheduleType;
	}

	public HasText getTxtParentEventId() {
		return txtParentEventId;
	}

	public HasName getLstParentSplitType() {
		return lstParentSplitType;
	}

	public HasText getTxtManPower() {
		return txtManPower;
	}

	public HasText getTxtTimeEstimate() {
		return txtTimeEstimate;
	}

	public HasName getLstSpecialEventCode() {
		return lstSpecialEventCode;
	}

	public HasName getLstTechnician() {
		return lstTechnician;
	}

	public HasText getTxtScheduledTimeHour() {
		return txtScheduledTimeHour;
	}

	public HasText getTxtScheduledTimeMinute() {
		return txtScheduledTimeMinute;
	}

	public HasText getTxtTravelTimeEstimate() {
		return txtTravelTimeEstimate;
	}

	public HasName getLstServiceCategory() {
		return lstServiceCategory;
	}

	public HasName getChkOverrideRates() {
		return chkOverrideRates;
	}

	public HasText getTxtLabourRate() {
		return txtLabourRate;
	}

	public HasText getTxtOvertimeRate() {
		return txtOvertimeRate;
	}

	public HasName getLstStatus() {
		return lstStatus;
	}

	public HasClickHandlers getBtnAdd() {
		return btnAdd;
	}

	public HasClickHandlers getBtnUpdate() {
		return btnUpdate;
	}

	public HasClickHandlers getBtnCancel() {
		return btnCancel;
	}

	public HasClickHandlers getBtnBack() {
		return btnBack;
	}

	public DateField getDatScheduledTimeDate() {
		return dfScheduledStart;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelType(SchedulePanelType schedulePanelType) {
		setReadOnly(true);

		boolean view = false;
		boolean add = false;
		boolean update = false;

		if (SchedulePanelType.ADD.equals(schedulePanelType)) {
			add = true;
		} else if (SchedulePanelType.UPDATE.equals(schedulePanelType)) {
			update = true;
		} else {
			view = true;
		}

		boolean addUpdate = (add || update);

		if (addUpdate) {
			boolean enabled = true;
			boolean readOnly = false;
			((TextBox) txtTimeEstimate).setReadOnly(readOnly);
			((ListBox) lstSpecialEventCode).setEnabled(enabled);
			// ((ListBox) lstTechnician).setEnabled(enabled);
			dfScheduledStart.setReadOnly(readOnly);
			dfScheduledStart.setDisabled(readOnly);
			((TextBox) txtScheduledTimeHour).setReadOnly(readOnly);
			((TextBox) txtScheduledTimeHour).setEnabled(enabled);
			((TextBox) txtScheduledTimeMinute).setReadOnly(readOnly);
			((TextBox) txtScheduledTimeMinute).setEnabled(enabled);
			((TextBox) txtTravelTimeEstimate).setReadOnly(readOnly);
			((CheckBox) chkOverrideRates).setEnabled(enabled);
			((TextBox) txtLabourRate).setReadOnly(readOnly);
			((TextBox) txtOvertimeRate).setReadOnly(readOnly);
			// ((ListBox) lstStatus).setEnabled(enabled);
		}

		((Button) btnBack).setVisible(view);
		((Button) btnAdd).setVisible(add);
		((Button) btnUpdate).setVisible(update);
		((Button) btnCancel).setVisible(addUpdate);

	}

	/**
	 * Arrange widgets in nice layout
	 */
	private void layout() {
		panel.add(decoratorPanel);

		FlexTable table = new FlexTable();
		decoratorPanel.add(table);
		table.getColumnFormatter().setWidth(0, "150px");
		table.getColumnFormatter().setWidth(1, "250px");

		int row = 0, column = 0;
		table.setWidget(row, column, (Widget) lblEventId);
		column++;
		table.setWidget(row, column, (Widget) txtEventId);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblScheduleType);
		column++;
		table.setWidget(row, column, (Widget) lstScheduleType);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblParentEventId);
		column++;
		table.setWidget(row, column, (Widget) txtParentEventId);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblParentSplitType);
		column++;
		table.setWidget(row, column, (Widget) lstParentSplitType);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblManPower);
		column++;
		table.setWidget(row, column, (Widget) txtManPower);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTimeEstimate);
		column++;
		table.setWidget(row, column, (Widget) txtTimeEstimate);
		column = 0;
		row++;
		// table.setWidget(row, column, (Widget) lblSpecialEventCode);
		// column++;
		// table.setWidget(row, column, (Widget) lstSpecialEventCode);
		column = 0;
		// row++;
		table.setWidget(row, column, (Widget) lblTechnician);
		column++;
		table.setWidget(row, column, (Widget) lstTechnician);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblScheduledStart);
		column++;
		HorizontalPanel hpScheduledStart = new HorizontalPanel();
		hpScheduledStart.add(dfScheduledStart);
		hpScheduledStart.add(new HTML("&nbsp;&nbsp;&nbsp;&nbsp;"));
		((TextBox) txtScheduledTimeHour).setStyleName("fsInput-Small");
		hpScheduledStart.add((Widget) txtScheduledTimeHour);
		hpScheduledStart.add(new HTML(":"));
		((TextBox) txtScheduledTimeMinute).setStyleName("fsInput-Small");
		hpScheduledStart.add((Widget) txtScheduledTimeMinute);
		table.setWidget(row, column, (Widget) hpScheduledStart);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTravelTimeEstimate);
		column++;
		table.setWidget(row, column, (Widget) txtTravelTimeEstimate);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblServiceCategory);
		column++;
		table.setWidget(row, column, (Widget) lstServiceCategory);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblOverrideRates);
		column++;
		table.setWidget(row, column, (Widget) chkOverrideRates);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblLabourRate);
		column++;
		table.setWidget(row, column, (Widget) txtLabourRate);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblOvertimeRate);
		column++;
		table.setWidget(row, column, (Widget) txtOvertimeRate);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblStatus);
		column++;
		table.setWidget(row, column, (Widget) lstStatus);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		panel.add(btnPanel);
		btnPanel.add((Widget) btnAdd);
		btnPanel.add((Widget) btnUpdate);
		btnPanel.add((Widget) btnCancel);
		btnPanel.add((Widget) btnBack);

	}

	private void hideButtons() {
		((Button) btnAdd).setVisible(false);
		((Button) btnUpdate).setVisible(false);
		((Button) btnCancel).setVisible(false);
	}

}
