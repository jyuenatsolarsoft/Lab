/**
 * Work History:
 * 
 * AffanJ		2009-10-06		Change from TextBox to HTML (labels).
 */

package com.cms.gwt.fs.client.view.accesshours;

import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.AccessHoursPresenter;
import com.cms.gwt.fs.client.util.DateUtil;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

/**
 * View for Access Hours.
 * 
 */
public class AccessHoursView extends Composite implements
		AccessHoursPresenter.View {

	private final TextConstants txtConsts;
	private final FlexTable accessHours;

	private final HasHTML lblTimeZone;
	private final HasText txtTimeZone;
	private final HasHTML lblDay;
	private final HasHTML lblStart1;
	private final HasHTML lblEnd1;
	private final HasHTML lblStart2;
	private final HasHTML lblEnd2;
	private final AccessHoursRow[] accessHoursRows;

	private final static int NUM_ACCESS_HOURS_ROW = 7;

	/**
	 * Constructor.
	 */
	public AccessHoursView() {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		accessHours = new FlexTable();
		initWidget(accessHours);

		lblTimeZone = new HTML(txtConsts.AccessHoursTimeZone());
		txtTimeZone = new TextBox();
		lblDay = new HTML(txtConsts.AccessHoursDay());
		lblStart1 = new HTML(txtConsts.AccessHoursStart1());
		lblEnd1 = new HTML(txtConsts.AccessHoursEnd1());
		lblStart2 = new HTML(txtConsts.AccessHoursStart2());
		lblEnd2 = new HTML(txtConsts.AccessHoursEnd2());
		accessHoursRows = new AccessHoursRow[NUM_ACCESS_HOURS_ROW];

		// make default access hours row to avoid NullPointerException
		String[] weekdays = new String[] { txtConsts.Monday(),
				txtConsts.Tuesday(), txtConsts.Wednesday(),
				txtConsts.Thursday(), txtConsts.Friday(), txtConsts.Saturday(),
				txtConsts.Sunday() };
		for (int i = 0; i < NUM_ACCESS_HOURS_ROW; i++) {
			accessHoursRows[i] = new AccessHoursRow(weekdays[i]);
		}

		layout();

		// by-default read only
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
		txtTimeZone.setText("");
		for (AccessHoursRow accessHoursRow : accessHoursRows) {
			accessHoursRow.reset();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtTimeZone).setReadOnly(readOnly);
		for (AccessHoursRow accessHoursRow : accessHoursRows) {
			accessHoursRow.setReadOnly(readOnly);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public HasText getTxtTimeZone() {
		return txtTimeZone;
	}

	/**
	 * {@inheritDoc}
	 */
	public AccessHoursRow getAccessHoursRows(int index) {
		if (index >= 0 && index < NUM_ACCESS_HOURS_ROW) {
			return accessHoursRows[index];
		} else {
			return null;
		}
	}

	/**
	 * Arrange all the widgets in nice layout.
	 */
	private void layout() {
		accessHours.addStyleName("AccessHours");
		accessHours.setCellPadding(0);
		accessHours.setCellSpacing(0);
		accessHours.getColumnFormatter().setWidth(0, "120px");
		accessHours.getColumnFormatter().setWidth(1, "100px");
		accessHours.getColumnFormatter().setWidth(2, "100px");
		accessHours.getColumnFormatter().setWidth(3, "100px");
		accessHours.getColumnFormatter().setWidth(4, "100px");

		int row = 0, column = 0;
		accessHours.setWidget(row, column, (Widget) lblTimeZone);
		column++;
		accessHours.setWidget(row, column, (Widget) txtTimeZone);
		column = 0;
		row++;
		accessHours.setWidget(row, column, (Widget) lblDay);
		column++;
		accessHours.setWidget(row, column, (Widget) lblStart1);
		column++;
		accessHours.setWidget(row, column, (Widget) lblEnd1);
		column++;
		accessHours.setWidget(row, column, (Widget) lblStart2);
		column++;
		accessHours.setWidget(row, column, (Widget) lblEnd2);
		column = 0;
		row++;
		for (AccessHoursRow accessHoursRow : accessHoursRows) {
			accessHours.getFlexCellFormatter().setColSpan(row, column, 5);
			accessHours.setWidget(row, column, (Widget) accessHoursRow);
			row++;
		}
	}

	/**
	 * Sub-class for individual access hours row.
	 * 
	 */
	public class AccessHoursRow extends Composite implements BaseView {

		private final FlexTable accessHoursRow;

		private final HasHTML lblDayName;
		// private final HasText txtStartHour1;
		// private final HasText txtStartMin1;
		// private final HasText txtEndHour1;
		// private final HasText txtEndMin1;
		// private final HasText txtStartHour2;
		// private final HasText txtStartMin2;
		// private final HasText txtEndHour2;
		// private final HasText txtEndMin2;
		private final HasHTML lblStart1;
		private final HasHTML lblEnd1;
		private final HasHTML lblStart2;
		private final HasHTML lblEnd2;

		/**
		 * Constructor.
		 * 
		 * @param day
		 *            Day of the week.
		 */
		public AccessHoursRow(String day) {

			accessHoursRow = new FlexTable();
			initWidget(accessHoursRow);

			lblDayName = new HTML(day);
			// txtStartHour1 = new TextBox();
			// txtStartMin1 = new TextBox();
			// txtEndHour1 = new TextBox();
			// txtEndMin1 = new TextBox();
			// txtStartHour2 = new TextBox();
			// txtStartMin2 = new TextBox();
			// txtEndHour2 = new TextBox();
			// txtEndMin2 = new TextBox();
			lblStart1 = new HTML();
			lblEnd1 = new HTML();
			lblStart2 = new HTML();
			lblEnd2 = new HTML();

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
			// txtStartHour1.setText("00");
			// txtStartMin1.setText("00");
			// txtEndHour1.setText("00");
			// txtEndMin1.setText("00");
			// txtStartHour2.setText("00");
			// txtStartMin2.setText("00");
			// txtEndHour2.setText("00");
			// txtEndMin2.setText("00");
			lblStart1.setText("00:00");
			lblEnd1.setText("00:00");
			lblStart2.setText("00:00");
			lblEnd2.setText("00:00");
		}

		/**
		 * {@inheritDoc}
		 */
		public void setReadOnly(boolean readOnly) {
			// ((TextBox) txtStartHour1).setReadOnly(readOnly);
			// ((TextBox) txtStartMin1).setReadOnly(readOnly);
			// ((TextBox) txtEndHour1).setReadOnly(readOnly);
			// ((TextBox) txtEndMin1).setReadOnly(readOnly);
			// ((TextBox) txtStartHour2).setReadOnly(readOnly);
			// ((TextBox) txtStartMin2).setReadOnly(readOnly);
			// ((TextBox) txtEndHour2).setReadOnly(readOnly);
			// ((TextBox) txtEndMin2).setReadOnly(readOnly);
		}

		/**
		 * {@inheritDoc}
		 */
		public void setData(String start1, String end1, String start2,
				String end2) {

			// txtStartHour1.setText(arrStart1[0]);
			// txtStartMin1.setText(arrStart1[1]);
			lblStart1.setText(DateUtil.formatTime(start1));

			// txtEndHour1.setText(arrEnd1[0]);
			// txtEndMin1.setText(arrEnd1[1]);
			lblEnd1.setText(DateUtil.formatTime(end1));

			// txtStartHour2.setText(arrStart2[0]);
			// txtStartMin2.setText(arrStart2[1]);
			lblStart2.setText(DateUtil.formatTime(start2));

			// txtEndHour2.setText(arrEnd2[0]);
			// txtEndMin2.setText(arrEnd2[1]);
			lblEnd2.setText(DateUtil.formatTime(end2));
		}

		/**
		 * Arrange all the widgets in nice layout.
		 */
		private void layout() {
			accessHoursRow.addStyleName("AccessHoursRow");
			accessHoursRow.setCellPadding(0);
			accessHoursRow.setCellSpacing(0);
			accessHoursRow.getColumnFormatter().setWidth(0, "124px");
			accessHoursRow.getColumnFormatter().setWidth(1, "96px");
			accessHoursRow.getColumnFormatter().setWidth(2, "100px");
			accessHoursRow.getColumnFormatter().setWidth(3, "100px");
			accessHoursRow.getColumnFormatter().setWidth(4, "100px");

			int row = 0, column = 0;
			accessHoursRow.setWidget(row, column, (Widget) lblDayName);
			column++;
			// HorizontalPanel start1 = new HorizontalPanel();
			// start1.add((Widget) txtStartHour1);
			// start1.add(new HTML(":"));
			// start1.add((Widget) txtStartMin1);
			// accessHoursRow.setWidget(row, column, (Widget) start1);
			accessHoursRow.setWidget(row, column, (Widget) lblStart1);
			column++;
			// HorizontalPanel end1 = new HorizontalPanel();
			// end1.add((Widget) txtEndHour1);
			// end1.add(new HTML(":"));
			// end1.add((Widget) txtEndMin1);
			// accessHoursRow.setWidget(row, column, (Widget) end1);
			accessHoursRow.setWidget(row, column, (Widget) lblEnd1);
			column++;
			// HorizontalPanel start2 = new HorizontalPanel();
			// start2.add((Widget) txtStartHour2);
			// start2.add(new HTML(":"));
			// start2.add((Widget) txtStartMin2);
			// accessHoursRow.setWidget(row, column, (Widget) start2);
			accessHoursRow.setWidget(row, column, (Widget) lblStart2);
			column++;
			// HorizontalPanel end2 = new HorizontalPanel();
			// end2.add((Widget) txtEndHour2);
			// end2.add(new HTML(":"));
			// end2.add((Widget) txtEndMin2);
			// accessHoursRow.setWidget(row, column, (Widget) end2);
			accessHoursRow.setWidget(row, column, (Widget) lblEnd2);
		}

	}
}
