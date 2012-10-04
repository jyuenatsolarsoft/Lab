package com.cms.gwt.fs.client.view.location;

import com.cms.gwt.fs.client.TextConstants;
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
 * View for Plant Location
 *
 */
public class Plant extends Composite implements BaseView {

	private final TextConstants txtConsts;
	private final FlexTable location;

	private final HasHTML lblInHousePlant;
	private final HasText txtInHousePlant;
	private HasText txtAddressLine1;
	private HasText txtAddressLine2;
	private HasText txtAddressLine3;
	private HasText txtAddressLine4;
	private HasText txtAddressLine5;
	private HasText txtAddressLine6;

	/**
	 * Constructor.
	 */
	public Plant() {
		txtConsts = (TextConstants) GWT.create(TextConstants.class);

		location = new FlexTable();
		initWidget(location);
		
		lblInHousePlant = new HTML(txtConsts.LocationInHousePlant());
		txtInHousePlant = new TextBox();
		txtAddressLine1 = new TextBox();
		txtAddressLine2 = new TextBox();
		txtAddressLine3 = new TextBox();
		txtAddressLine4 = new TextBox();
		txtAddressLine5 = new TextBox();
		txtAddressLine6 = new TextBox();
		
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
		((TextBox) txtInHousePlant).setText("");
		((TextBox) txtAddressLine1).setText("");
		((TextBox) txtAddressLine2).setText("");
		((TextBox) txtAddressLine3).setText("");
		((TextBox) txtAddressLine4).setText("");
		((TextBox) txtAddressLine5).setText("");
		((TextBox) txtAddressLine6).setText("");
	}

	/**
	 * {@inheritDoc}
	 */
	public void setReadOnly(boolean readOnly) {
		((TextBox) txtInHousePlant).setReadOnly(readOnly);
		((TextBox) txtAddressLine1).setReadOnly(readOnly);
		((TextBox) txtAddressLine2).setReadOnly(readOnly);
		((TextBox) txtAddressLine3).setReadOnly(readOnly);
		((TextBox) txtAddressLine4).setReadOnly(readOnly);
		((TextBox) txtAddressLine5).setReadOnly(readOnly);
		((TextBox) txtAddressLine6).setReadOnly(readOnly);
	}
	
	public HasText getTxtAddressLine1() {
		return txtAddressLine1;
	}

	public HasText getTxtAddressLine2() {
		return txtAddressLine2;
	}

	public HasText getTxtAddressLine3() {
		return txtAddressLine3;
	}

	public HasText getTxtAddressLine4() {
		return txtAddressLine4;
	}

	public HasText getTxtAddressLine5() {
		return txtAddressLine5;
	}

	public HasText getTxtAddressLine6() {
		return txtAddressLine6;
	}

	public HasText getTxtInHousePlant() {
		return txtInHousePlant;
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
		location.setWidget(row, column, (Widget) lblInHousePlant);
		column++;
		location.setWidget(row, column, (Widget) txtInHousePlant);
		column++;
		location.setWidget(row, column, (Widget) txtAddressLine1);
		row++;
		location.setWidget(row, column, (Widget) txtAddressLine2);
		row++;
		location.setWidget(row, column, (Widget) txtAddressLine3);
		row++;
		location.setWidget(row, column, (Widget) txtAddressLine4);
		row++;
		location.setWidget(row, column, (Widget) txtAddressLine5);
		row++;
		location.setWidget(row, column, (Widget) txtAddressLine6);
	}

}
