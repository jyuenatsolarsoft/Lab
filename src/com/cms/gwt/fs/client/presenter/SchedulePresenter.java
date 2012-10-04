package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.grid.GridPanel;

public interface SchedulePresenter extends BasePresenter {

	public enum SchedulePanelType {
		VIEW(0), ADD(1), UPDATE(2), MAIN(3);

		public final int value;

		SchedulePanelType(int value) {
			this.value = value;
		}

		public static SchedulePanelType get(int panelType) {
			for (SchedulePanelType type : SchedulePanelType.values()) {
				if (type.value == panelType) {
					return type;
				}
			}
			return VIEW;
		}
	}

	public interface GridView extends BaseView {
		GridPanel getScheduleGrid();

		String initScheduleGrid(Object[][] schedules);

		void clearScheduleGrid();

		HasClickHandlers getBtnAdd();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnDelete();

		HasClickHandlers getBtnView();

		HasClickHandlers getBtnAccept();

		HasClickHandlers getBtnDecline();

		HasClickHandlers getBtnWorkHistory();
	}

	public interface PanelView extends BaseView {
		void setPanelType(SchedulePanelType schedulePanelType);

		HasText getTxtEventId();

		HasName getLstScheduleType();

		HasText getTxtParentEventId();

		HasName getLstParentSplitType();

		HasText getTxtManPower();

		HasText getTxtTimeEstimate();

		HasName getLstSpecialEventCode();

		HasName getLstTechnician();

		DateField getDatScheduledTimeDate();

		HasText getTxtScheduledTimeHour();

		HasText getTxtScheduledTimeMinute();

		HasText getTxtTravelTimeEstimate();

		HasName getLstServiceCategory();

		HasName getChkOverrideRates();

		HasText getTxtLabourRate();

		HasText getTxtOvertimeRate();

		HasName getLstStatus();

		HasClickHandlers getBtnAdd();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnCancel();

		HasClickHandlers getBtnBack();
	}

	void showScheduleGrid(String ticketNumber, String panelNumber);

	void showSchedulePanel(String ticketNumber, String panelNumber,
			String scheduleId, SchedulePanelType schedulePanelType);

}
