package com.cms.gwt.fs.client.view.location;

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
 * View for Customer Location. 
 *
 */
public class Customer extends Composite implements BaseView {

	private final TextConstants txtConsts;
	private final FlexTable location;
	
	private final HasHTML lblCustomer;
	private final HasText txtCustomerNumber;
	private final HasText txtCustomerAddress1;
	private final HasText txtCustomerAddress2;
	private final HasText txtCustomerAddress3;
	private final HasText txtCustomerAddress4;
	private final HasText txtCustomerAddress5;
	private final HasText txtCustomerAddress6;
	private final HasHTML lblOr;
	private final HasHTML lblCustomerSite;
	private final HasText txtCustomerSite;
	private final HasHTML lblCustomerTerritory;
	private final HasName lstCustomerTerritory;
	private final HasText txtCustomerTerritory1;
	private final HasText txtCustomerTerritory2;
	private final HasText txtCustomerTerritory3;
	private final HasText txtCustomerTerritory4;
	private final HasText txtCustomerTerritory5;
	private final HasText txtCustomerTerritory6;
	
	/**
	 * Constructor.
	 */
	public Customer() {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		location = new FlexTable();
		initWidget(location);

		lblCustomer = new HTML(txtConsts.LocationCustomer());
		txtCustomerNumber = new TextBox();
		txtCustomerAddress1 = new TextBox();
		txtCustomerAddress2 = new TextBox();
		txtCustomerAddress3 = new TextBox();
		txtCustomerAddress4 = new TextBox();
		txtCustomerAddress5 = new TextBox();
		txtCustomerAddress6 = new TextBox();
		lblOr = new HTML(txtConsts.LocationOr());
		lblCustomerSite = new HTML(txtConsts.LocationCustomerSite());
		txtCustomerSite = new TextBox();
		lblCustomerTerritory = new HTML(txtConsts.LocationTerritory());
		lstCustomerTerritory = new ListBox();
		txtCustomerTerritory1 = new TextBox();
		txtCustomerTerritory2 = new TextBox();
		txtCustomerTerritory3 = new TextBox();
		txtCustomerTerritory4 = new TextBox();
		txtCustomerTerritory5 = new TextBox();
		txtCustomerTerritory6 = new TextBox();

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
		((TextBox) txtCustomerNumber).setText("");
		((TextBox) txtCustomerAddress1).setText("");
		((TextBox) txtCustomerAddress2).setText("");
		((TextBox) txtCustomerAddress3).setText("");
		((TextBox) txtCustomerAddress4).setText("");
		((TextBox) txtCustomerAddress5).setText("");
		((TextBox) txtCustomerAddress6).setText("");
		((TextBox) txtCustomerSite).setText("");
		((ListBox) lstCustomerTerritory).clear();
		((TextBox) txtCustomerTerritory1).setText("");
		((TextBox) txtCustomerTerritory2).setText("");
		((TextBox) txtCustomerTerritory3).setText("");
		((TextBox) txtCustomerTerritory4).setText("");
		((TextBox) txtCustomerTerritory5).setText("");
		((TextBox) txtCustomerTerritory6).setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		boolean enabled = !readOnly;
		((TextBox) txtCustomerNumber).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress1).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress2).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress3).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress4).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress5).setReadOnly(readOnly);
		((TextBox) txtCustomerAddress6).setReadOnly(readOnly);
		((TextBox) txtCustomerSite).setReadOnly(readOnly);
		((ListBox) lstCustomerTerritory).setEnabled(enabled);
		((TextBox) txtCustomerTerritory1).setReadOnly(readOnly);
		((TextBox) txtCustomerTerritory2).setReadOnly(readOnly);
		((TextBox) txtCustomerTerritory3).setReadOnly(readOnly);
		((TextBox) txtCustomerTerritory4).setReadOnly(readOnly);
		((TextBox) txtCustomerTerritory5).setReadOnly(readOnly);
		((TextBox) txtCustomerTerritory6).setReadOnly(readOnly);
	}
	
	public HasName getLstCustomerTerritory() {
		return lstCustomerTerritory;
	}

	public HasText getTxtCustomerAddress1() {
		return txtCustomerAddress1;
	}

	public HasText getTxtCustomerAddress2() {
		return txtCustomerAddress2;
	}

	public HasText getTxtCustomerAddress3() {
		return txtCustomerAddress3;
	}

	public HasText getTxtCustomerAddress4() {
		return txtCustomerAddress4;
	}

	public HasText getTxtCustomerAddress5() {
		return txtCustomerAddress5;
	}

	public HasText getTxtCustomerAddress6() {
		return txtCustomerAddress6;
	}

	public HasText getTxtCustomerNumber() {
		return txtCustomerNumber;
	}

	public HasText getTxtCustomerSite() {
		return txtCustomerSite;
	}

	public HasText getTxtCustomerTerritory1() {
		return txtCustomerTerritory1;
	}

	public HasText getTxtCustomerTerritory2() {
		return txtCustomerTerritory2;
	}

	public HasText getTxtCustomerTerritory3() {
		return txtCustomerTerritory3;
	}

	public HasText getTxtCustomerTerritory4() {
		return txtCustomerTerritory4;
	}

	public HasText getTxtCustomerTerritory5() {
		return txtCustomerTerritory5;
	}

	public HasText getTxtCustomerTerritory6() {
		return txtCustomerTerritory6;
	}
	
	/**
	 * Arrange widgets in a nice layout.
	 */
	private void layout() {
		location.addStyleName("Location");
		location.getColumnFormatter().setWidth(0, "150px");
		location.getColumnFormatter().setWidth(1, "300px");
		location.getColumnFormatter().setWidth(2, "300px");

		int row = 0, column = 0;
		location.setWidget(row, column, (Widget) lblCustomer);
		column++;
		location.setWidget(row, column, (Widget) txtCustomerNumber);
		column++;
		location.setWidget(row, column, (Widget) txtCustomerAddress1);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerAddress2);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerAddress3);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerAddress4);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerAddress5);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerAddress6);
		column = 0;
		row++;
		location.setWidget(row, column, (Widget) lblOr);
		location.getRowFormatter().setStyleName(row, "Location_Or");
		location.getFlexCellFormatter().setColSpan(row, column, 3);
		column = 0;
		row++;
		location.setWidget(row, column, (Widget) lblCustomerSite);
		column++;
		location.setWidget(row, column, (Widget) txtCustomerSite);
		column = 0;
		row++;
		location.setWidget(row, column, (Widget) lblCustomerTerritory);
		column++;
		location.setWidget(row, column, (Widget) lstCustomerTerritory);		
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory1);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory2);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory3);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory4);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory5);
		row++;
		location.setWidget(row, column, (Widget) txtCustomerTerritory6);
	}

}
