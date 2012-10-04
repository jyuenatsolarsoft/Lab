package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.model.material.RecordedMaterialEntriesEntry;
import com.cms.gwt.fs.client.model.workHistory.WorkHistory;
import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.grid.GridPanel;

public interface MaterialEditPresenter extends BasePresenter {

	public enum MaterialEditType {
		UPDATE(0), EDIT_LOT(1), EDIT_SERIAL(2), EDIT_NONE(3);

		public final int value;

		MaterialEditType(int value) {
			this.value = value;
		}

		public static MaterialEditType get(int editType) {
			for (MaterialEditType type : MaterialEditType.values()) {
				if (type.value == editType) {
					return type;
				}
			}
			return UPDATE;
		}
	}

	interface View extends BaseView {
		void setType(MaterialEditType materialEditType);

		GridPanel getGrid();

		String initGrid(Object[][] materials);

		void clearGrid();

		HasText getTxtTicketNumber();

		HasText getTxtEventId();

		HasText getTxtCounterReading();

		HasText getTxtCounterReadingDescription();

		HasText getTxtTechnician();

		HasText getTxtTechnicianDescription();

		HasText getTxtArrivalDate();

		HasText getTxtArrivalTime();

		HasText getTxtSequence();

		HasText getTxtSequenceDescription();

		HasText getTxtLine();

		HasText getTxtPartNumber();

		HasText getTxtPartNumberDescription();

		HasText getTxtQuantity();

		HasText getTxtUnitOfMeasure();

		HasName getChkCoveredByWarranty();

		HasName getLstStockLocation();

		HasName getLstBinLocation();

		HasText getTxtRequired();

		HasText getTxtRecorded();

		HasClickHandlers getBtnOK();

		HasClickHandlers getBtnSave();

		HasClickHandlers getBtnCancel();

	}

	void showMaterial(String ticketNumber, String panelNumber, String eventId,
			String tabNumber, String sequence, String line,
			MaterialEditType materialEditType);

	void showMaterial(String ticketNumber, String panelNumber, String eventId,
			String tabNumber, String sequence, String line,
			MaterialEditType materialEditType, WorkHistory workHistory,
			RecordedMaterialEntriesEntry recordedMaterialEntry);
}
