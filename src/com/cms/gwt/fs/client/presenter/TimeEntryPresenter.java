package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.ToolbarButton;
import com.gwtext.client.widgets.grid.EditorGridPanel;

public interface TimeEntryPresenter extends BasePresenter {

	public enum TimeEntryHeaderType {
		LABOUR_RECORDED(0), TIME_ENTRY(1);

		public final int value;

		TimeEntryHeaderType(int value) {
			this.value = value;
		}

		public static TimeEntryHeaderType get(int headerType) {
			for (TimeEntryHeaderType type : TimeEntryHeaderType.values()) {
				if (type.value == headerType) {
					return type;
				}
			}
			return LABOUR_RECORDED;
		}
	}

	interface View extends BaseView {
		EditorGridPanel getTimeEntryGrid();

		String initTimeEntryGrid(Object[][] timeEntries);

		void clearTimeEntryGrid();

		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		HasText getTxtCounterReading();

		HasText getTxtTechnician();

		HasText getTxtTechnicianDescription();

		HasText getTxtArrivalDate();

		HasText getTxtArrivalTime();

		HasText getTxtTotalTimeRecorded();

		HasText getTxtTotalHoursWorked();

		HasClickHandlers getBtnAddTime();

		HasClickHandlers getBtnSave();

		// HasClickHandlers getBtnEdit();

		HasClickHandlers getBtnDelete();

		HasClickHandlers getBtnCancel();

		ToolbarButton getGridResetButton();
	}

	void showTimeEntry(String ticketNumber, String panelNumber, String eventId,
			String tabNumber);

}
