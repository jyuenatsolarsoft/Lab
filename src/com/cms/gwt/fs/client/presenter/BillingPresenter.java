package com.cms.gwt.fs.client.presenter;

import com.cms.gwt.fs.client.view.BaseView;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasHTML;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasText;
import com.gwtext.client.widgets.grid.GridPanel;

public interface BillingPresenter extends BasePresenter {

	public enum BillingPanelType {
		VIEW(0), ADD(1), UPDATE(2);

		public final int value;

		BillingPanelType(int value) {
			this.value = value;
		}

		public static BillingPanelType get(int panelType) {
			for (BillingPanelType type : BillingPanelType.values()) {
				if (type.value == panelType) {
					return type;
				}
			}
			return VIEW;
		}
	}

	// these ChargeTypes must match ChargeTypes returned from back-end
	public enum ChargeType {
		MISCELLANEUOS("4"), OVERTIME_LABOUR("2"), MATERIAL("3");

		public final String value;

		ChargeType(String value) {
			this.value = value;
		}

		public static ChargeType get(String chargeType) {
			for (ChargeType type : ChargeType.values()) {
				if (type.value.equals(chargeType)) {
					return type;
				}
			}
			return MISCELLANEUOS;
		}
	}

	interface GridView extends BaseView {
		void clearBillingGrid();

		GridPanel getBillingGrid();

		String initBillingGrid(Object[][] billings);

		HasClickHandlers getBtnAdd();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnDelete();

		HasClickHandlers getBtnView();

		HasHTML getLblLabourCompleted();

		HasHTML getLblLabourUnCompleted();

		HasHTML getLblPartsCompleted();

		HasHTML getLblPartsUncompleted();

		HasHTML getLblMiscellaneousCompleted();

		HasHTML getLblMiscellaneousUncompleted();

		HasHTML getLblSubtotalCompleted();

		HasHTML getLblSubtotalUncompleted();

		HasHTML getLblTaxesCompleted();

		HasHTML getLblTaxesUncompleted();

		HasHTML getLblToPayCompleted();

		HasHTML getLblToPayUncompleted();

		HasHTML getLblTotalToPayCompleted();

		HasHTML getLblTotalToPayUncompleted();

	}

	interface PanelView extends BaseView {

		void setPanelType(BillingPanelType billingPanelType, ChargeType chargeType);

		HasText getTxtLineNumber();

		HasName getLstChargeType();

		HasText getTxtFromEventId();

		HasName getLstMiscellaneousChargeCode();

		HasText getTxtDescription();

		HasName getLstPartNumber();

		HasText getTxtLotNumber();

		HasText getTxtSerialNumber();

		HasText getTxtQuantity();

		HasText getTxtUnitPrice();

		HasText getTxtSubtotal();

		HasName getLstTaxGroup();

		HasName getLstTaxRate();

		HasText getTxtTaxes();

		HasText getTxtTotal();

		HasName getChkWarranty();

		HasClickHandlers getBtnCalculate();

		HasClickHandlers getBtnAdd();

		HasClickHandlers getBtnUpdate();

		HasClickHandlers getBtnCancel();

		HasClickHandlers getBtnBack();

	}

	void showBillingGrid(String ticketNumber, String panelNumber);

	void showBillingPanel(String ticketNumber, String panelNumber,
			String billingId, BillingPanelType billingPanelType);

}
