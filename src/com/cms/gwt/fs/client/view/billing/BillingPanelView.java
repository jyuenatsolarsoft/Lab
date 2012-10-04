package com.cms.gwt.fs.client.view.billing;

import com.cms.gwt.common.client.CmsListBox;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.presenter.BillingPresenter;
import com.cms.gwt.fs.client.presenter.BillingPresenter.BillingPanelType;
import com.cms.gwt.fs.client.presenter.BillingPresenter.ChargeType;
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

public class BillingPanelView extends Composite implements
		BillingPresenter.PanelView {

	private final VerticalPanel panel;
	private final DecoratorPanel decoratorPanel;
	private final TextConstants txtConsts;

	private final HasHTML lblLineNumber;
	private final HasText txtLineNumber;
	private final HasHTML lblChargeType;
	private final HasName lstChargeType;
	private final HasHTML lblFromEventId;
	private final HasText txtFromEventId;
	private final HasHTML lblMiscellaneousChargeCode;
	private final HasName lstMiscellaneousChargeCode;
	private final HasHTML lblDescription;
	private final HasText txtDescription;
	private final HasHTML lblPartNumber;
	private final HasName lstPartNumber;
	private final HasHTML lblLotNumber;
	private final HasText txtLotNumber;
	private final HasHTML lblSerialNumber;
	private final HasText txtSerialNumber;
	private final HasHTML lblQuantity;
	private final HasText txtQuantity;
	private final HasHTML lblUnitPrice;
	private final HasText txtUnitPrice;
	private final HasHTML lblSubtotal;
	private final HasText txtSubtotal;
	private final HasHTML lblTaxGroup;
	private final HasName lstTaxGroup;
	private final HasHTML lblTaxRate;
	private final HasName lstTaxRate;
	private final HasHTML lblTaxes;
	private final HasText txtTaxes;
	private final HasHTML lblTotal;
	private final HasText txtTotal;
	private final HasHTML lblWarranty;
	private final HasName chkWarranty;

	private final HasClickHandlers btnCalculate;
	private final HasClickHandlers btnAdd;
	private final HasClickHandlers btnUpdate;
	private final HasClickHandlers btnCancel;
	private final HasClickHandlers btnBack;

	/**
	 * Constructor.
	 */
	public BillingPanelView() {
		panel = new VerticalPanel();
		panel.setStyleName("BillingPanel");
		initWidget(panel);

		decoratorPanel = new DecoratorPanel();
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		lblLineNumber = new HTML(txtConsts.BillingLineNumber());
		txtLineNumber = new TextBox();
		lblChargeType = new HTML(txtConsts.BillingChargeType());
		lstChargeType = new ListBox();
		lblFromEventId = new HTML(txtConsts.BillingFromEventId());
		txtFromEventId = new TextBox();
		lblMiscellaneousChargeCode = new HTML(txtConsts
				.BillingMiscellaneousChargeCode());
		lstMiscellaneousChargeCode = new CmsListBox(false);
		lblDescription = new HTML(txtConsts.BillingDescription());
		txtDescription = new TextBox();
		lblPartNumber = new HTML(txtConsts.BillingPartNumber());
		lstPartNumber = new ListBox();
		lblLotNumber = new HTML(txtConsts.BillingLotNumber());
		txtLotNumber = new TextBox();
		lblSerialNumber = new HTML(txtConsts.BillingSerialNumber());
		txtSerialNumber = new TextBox();
		lblQuantity = new HTML(txtConsts.BillingQuantity());
		txtQuantity = new TextBox();
		lblUnitPrice = new HTML(txtConsts.BillingUnitPrice());
		txtUnitPrice = new TextBox();
		lblSubtotal = new HTML(txtConsts.BillingSubtotal());
		txtSubtotal = new TextBox();
		lblTaxGroup = new HTML(txtConsts.BillingTaxGroup());
		lstTaxGroup = new CmsListBox(false);
		lblTaxRate = new HTML(txtConsts.BillingTaxRate());
		lstTaxRate = new CmsListBox(false);
		lblTaxes = new HTML(txtConsts.BillingTaxes());
		txtTaxes = new TextBox();
		lblTotal = new HTML(txtConsts.BillingTotal());
		txtTotal = new TextBox();
		lblWarranty = new HTML(txtConsts.BillingWarranty());
		chkWarranty = new CheckBox();

		btnCalculate = new Button(txtConsts.BillingCalculate());
		btnAdd = new Button(txtConsts.Add());
		btnUpdate = new Button(txtConsts.Update());
		btnCancel = new Button(txtConsts.Cancel());
		btnBack = new Button(txtConsts.Back());

		hideButtons();
		layout();

		// by-default panel type VIEW
		setPanelType(BillingPanelType.VIEW, ChargeType.MISCELLANEUOS);
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
		((TextBox) txtLineNumber).setText("");
		((ListBox) lstChargeType).clear();
		((TextBox) txtFromEventId).setText("");
		((ListBox) lstMiscellaneousChargeCode).clear();
		((TextBox) txtDescription).setText("");
		((ListBox) lstPartNumber).clear();
		((TextBox) txtLotNumber).setText("");
		((TextBox) txtSerialNumber).setText("");
		((TextBox) txtQuantity).setText("");
		((TextBox) txtUnitPrice).setText("");
		((TextBox) txtSubtotal).setText("");
		((ListBox) lstTaxGroup).clear();
		((ListBox) lstTaxRate).clear();
		((TextBox) txtTaxes).setText("");
		((TextBox) txtTotal).setText("");
		((CheckBox) chkWarranty).setValue(false);

		hideButtons();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((TextBox) txtLineNumber).setReadOnly(readOnly);
		((ListBox) lstChargeType).setEnabled(enabled);
		((TextBox) txtFromEventId).setReadOnly(readOnly);
		((ListBox) lstMiscellaneousChargeCode).setEnabled(enabled);
		((TextBox) txtDescription).setReadOnly(readOnly);
		((ListBox) lstPartNumber).setEnabled(enabled);
		((TextBox) txtLotNumber).setReadOnly(readOnly);
		((TextBox) txtSerialNumber).setReadOnly(readOnly);
		((TextBox) txtQuantity).setReadOnly(readOnly);
		((TextBox) txtUnitPrice).setReadOnly(readOnly);
		((TextBox) txtSubtotal).setReadOnly(readOnly);
		((ListBox) lstTaxGroup).setEnabled(enabled);
		((ListBox) lstTaxRate).setEnabled(enabled);
		((TextBox) txtTaxes).setReadOnly(readOnly);
		((TextBox) txtTotal).setReadOnly(readOnly);
		((CheckBox) chkWarranty).setEnabled(enabled);

		hideButtons();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setPanelType(BillingPanelType billingPanelType,
			ChargeType chargeType) {
		setReadOnly(true);

		boolean isAdd = false;
		boolean isUpdate = false;
		boolean isView = false;

		boolean isMiscellaneous = false;
		boolean isOvertime = false;
		boolean isMaterial = false;

		if (BillingPanelType.ADD.equals(billingPanelType)) {
			isAdd = true;
		} else if (BillingPanelType.UPDATE.equals(billingPanelType)) {
			isUpdate = true;
		} else if (BillingPanelType.VIEW.equals(billingPanelType)) {
			isView = true;
		}

		if (ChargeType.MISCELLANEUOS.equals(chargeType)) {
			isMiscellaneous = true;
		} else if (ChargeType.OVERTIME_LABOUR.equals(chargeType)) {
			isOvertime = true;
		} else if (ChargeType.MATERIAL.equals(chargeType)) {
			isMaterial = true;
		}

		((HTML) lblFromEventId).setVisible(isOvertime || isMaterial);
		((TextBox) txtFromEventId).setVisible(isOvertime || isMaterial);
		
		((HTML) lblMiscellaneousChargeCode).setVisible(isMiscellaneous);
		((ListBox) lstMiscellaneousChargeCode).setVisible(isMiscellaneous);
		((ListBox) lstMiscellaneousChargeCode).setEnabled(isAdd || isUpdate);
				
		((TextBox) txtDescription).setReadOnly(isView);
				
		((HTML) lblPartNumber).setVisible(isMaterial);
		((ListBox) lstPartNumber).setVisible(isMaterial);
		((HTML) lblLotNumber).setVisible(isMaterial);
		((TextBox) txtLotNumber).setVisible(isMaterial);
		((HTML) lblSerialNumber).setVisible(isMaterial);
		((TextBox) txtSerialNumber).setVisible(isMaterial);
		
		((TextBox) txtQuantity).setReadOnly(isView);
		
		((TextBox) txtUnitPrice).setReadOnly(isView);
		
		((ListBox) lstTaxGroup).setEnabled(isAdd || isUpdate);
		
		((ListBox) lstTaxRate).setEnabled(isAdd || isUpdate);
		
		((HTML) lblWarranty).setVisible(isOvertime || isMaterial);
		((CheckBox) chkWarranty).setVisible(isOvertime || isMaterial);
		((CheckBox) chkWarranty).setEnabled(isUpdate);
		
		((Button) btnCalculate).setVisible(isAdd || isUpdate);
		((Button) btnAdd).setVisible(isAdd);
		((Button) btnUpdate).setVisible(isUpdate);
		((Button) btnCancel).setVisible(isAdd || isUpdate);
		((Button) btnBack).setVisible(isView);

	}

	public HasText getTxtLineNumber() {
		return txtLineNumber;
	}

	public HasName getLstChargeType() {
		return lstChargeType;
	}

	public HasText getTxtFromEventId() {
		return txtFromEventId;
	}

	public HasName getLstMiscellaneousChargeCode() {
		return lstMiscellaneousChargeCode;
	}

	public HasText getTxtDescription() {
		return txtDescription;
	}

	public HasName getLstPartNumber() {
		return lstPartNumber;
	}

	public HasText getTxtLotNumber() {
		return txtLotNumber;
	}

	public HasText getTxtSerialNumber() {
		return txtSerialNumber;
	}

	public HasText getTxtQuantity() {
		return txtQuantity;
	}

	public HasText getTxtUnitPrice() {
		return txtUnitPrice;
	}

	public HasText getTxtSubtotal() {
		return txtSubtotal;
	}

	public HasName getLstTaxGroup() {
		return lstTaxGroup;
	}

	public HasName getLstTaxRate() {
		return lstTaxRate;
	}

	public HasText getTxtTaxes() {
		return txtTaxes;
	}

	public HasText getTxtTotal() {
		return txtTotal;
	}

	public HasName getChkWarranty() {
		return chkWarranty;
	}

	public HasClickHandlers getBtnCalculate() {
		return btnCalculate;
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

	/**
	 * Arrange widgets in nice layout
	 */
	private void layout() {
		panel.add(decoratorPanel);

		FlexTable table = new FlexTable();
		decoratorPanel.add(table);
		table.getColumnFormatter().setWidth(0, "200px");
		table.getColumnFormatter().setWidth(1, "300px");

		int row = 0, column = 0;
		table.setWidget(row, column, (Widget) lblLineNumber);
		column++;
		table.setWidget(row, column, (Widget) txtLineNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblChargeType);
		column++;
		table.setWidget(row, column, (Widget) lstChargeType);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblFromEventId);
		column++;
		table.setWidget(row, column, (Widget) txtFromEventId);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblMiscellaneousChargeCode);
		column++;
		table.setWidget(row, column, (Widget) lstMiscellaneousChargeCode);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblDescription);
		column++;
		table.setWidget(row, column, (Widget) txtDescription);
		column = 0;
		row++;
		table.setWidget(row, column, new HTML("&nbsp;"));
		column++;
		table.setWidget(row, column, new HTML("&nbsp;"));
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblPartNumber);
		column++;
		table.setWidget(row, column, (Widget) lstPartNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblLotNumber);
		column++;
		table.setWidget(row, column, (Widget) txtLotNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblSerialNumber);
		column++;
		table.setWidget(row, column, (Widget) txtSerialNumber);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblQuantity);
		column++;
		table.setWidget(row, column, (Widget) txtQuantity);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblUnitPrice);
		column++;
		table.setWidget(row, column, (Widget) txtUnitPrice);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblSubtotal);
		column++;
		table.setWidget(row, column, (Widget) txtSubtotal);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTaxGroup);
		column++;
		table.setWidget(row, column, (Widget) lstTaxGroup);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTaxRate);
		column++;
		table.setWidget(row, column, (Widget) lstTaxRate);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTaxes);
		column++;
		table.setWidget(row, column, (Widget) txtTaxes);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblTotal);
		column++;
		HorizontalPanel totalWidget = new HorizontalPanel();
		totalWidget.add((Widget) txtTotal);
		totalWidget.add(new HTML("&nbsp;"));
		((Button) btnCalculate).addStyleName("fsButton-Normal");
		totalWidget.add((Widget) btnCalculate);
		table.setWidget(row, column, totalWidget);
		column = 0;
		row++;
		table.setWidget(row, column, (Widget) lblWarranty);
		column++;
		table.setWidget(row, column, (Widget) chkWarranty);

		HorizontalPanel btnPanel = new HorizontalPanel();
		btnPanel.setStyleName("BtnPanel");
		panel.add(btnPanel);
		btnPanel.add((Widget) btnAdd);
		btnPanel.add((Widget) btnUpdate);
		btnPanel.add((Widget) btnCancel);
		btnPanel.add((Widget) btnBack);
	}

	private void hideButtons() {
		((Button) btnCalculate).setVisible(false);
		((Button) btnAdd).setVisible(false);
		((Button) btnUpdate).setVisible(false);
		((Button) btnCancel).setVisible(false);
	}

}
