package com.cms.gwt.fs.client.presenter;

import java.sql.Date;
import java.util.Set;

import com.cms.gwt.common.client.CmsListBox;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.FieldServices;
import com.cms.gwt.fs.client.HistoryConstants;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.factory.CodeDictionaryFactory;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.ListBox;
import com.gwtext.client.util.Format;
import com.gwtext.client.widgets.form.DateField;

/**
 * Base presenter implementation.
 * 
 */
public class BasePresenterImpl {

	protected final TextConstants txtConsts = (TextConstants) GWT
			.create(TextConstants.class);

	/**
	 * Populate the ListBox with the descriptions in the specified
	 * CodeDictionary.
	 * 
	 * The previously selected code will be selected and inserted to the list if
	 * it does not exist in the list.
	 * 
	 * @param listBox
	 *            The list box to be populated.
	 * @param dictionaryName
	 *            The name of the CodeDictionary.
	 * @param selectedCode
	 *            The previously selected code.
	 */
	protected void populateListBox(ListBox listBox, String dictionaryName,
			String selectedCode) {
		populateListBox(listBox, dictionaryName, selectedCode, false);
	}

	protected void populateListBox(ListBox listBox, String dictionaryName,
			String selectedCode, boolean sort) {
		CodeDictionary dictionary = CodeDictionaryFactory
				.getCodeDictionary(dictionaryName);

		populateListBox(listBox, dictionary, selectedCode, sort);
	}

	/**
	 * Populate the ListBox with the descriptions in the specified
	 * CodeDictionary.
	 * 
	 * The previously selected code will be selected and inserted to the list if
	 * it does not exist in the list.
	 * 
	 * @param listBox
	 *            The list box to be populated.
	 * @param dictionary
	 *            The CodeDictionary.
	 * @param selectedCode
	 *            The previously selected code.
	 */
	protected void populateListBox(ListBox listBox, CodeDictionary dictionary,
			String selectedCode) {
		populateListBox(listBox, dictionary, selectedCode, false);
	}

	protected void populateListBox(ListBox listBox, CodeDictionary dictionary,
			String selectedCode, boolean sort) {
		if (sort) {
			populateListBoxSort((CmsListBox) listBox, dictionary, selectedCode);
		} else {
			populateListBoxUnSort(listBox, dictionary, selectedCode);
		}
	}

	protected void populateListBoxUnSort(ListBox listBox,
			CodeDictionary dictionary, String selectedCode) {
		if (dictionary == null) {
			throw new IllegalArgumentException("dictionary cannot be null.");
		}

		Set<String> codes = dictionary.getCodes();

		int selectedCodeIndex = -1;
		int currentIndex = 1;

		listBox.clear();

		// Insert an empty entry for the user to reset the field.
		listBox.addItem("", "");

		for (String code : codes) {
			// Do not insert the selected code.
			if (selectedCode != null && !"".equals(selectedCode.trim())
					&& selectedCode.equals(code)) {
				selectedCodeIndex = currentIndex;
			}

			listBox.addItem(dictionary.get(code), code);

			currentIndex++;
		}

		if (selectedCodeIndex != -1) {
			// Select the previously selected value.
			listBox.setSelectedIndex(selectedCodeIndex);
		} else if (selectedCode != null && !"".equals(selectedCode.trim())) {
			// Selected code not found in the description table.
			listBox.addItem(selectedCode + " " + txtConsts.DescNotAvail(),
					selectedCode);
			listBox.setSelectedIndex(listBox.getItemCount() - 1);
		}
	}

	protected void populateListBoxSort(CmsListBox listBox,
			CodeDictionary dictionary, String selectedCode) {
		if (dictionary == null) {
			throw new IllegalArgumentException("dictionary cannot be null.");
		}

		Set<String> codes = dictionary.getCodes();
		listBox.clear();

		// Insert an empty entry for the user to reset the field.
		listBox.addItemSort("", "");
		for (String code : codes) {
			listBox.addItemSort(dictionary.get(code), code);
		}

		// now select the index
		if (selectedCode != null && !"".equals(selectedCode.trim())) {
			int selectedIndex;
			if ((selectedIndex = listBox.getValueIndex(selectedCode)) != -1) {
				listBox.setSelectedIndex(selectedIndex);

			} else {
				// Selected code not found in the description table.
				listBox.addItemSort(selectedCode + " "
						+ txtConsts.DescNotAvail(), selectedCode);
				listBox.setSelectedIndex(listBox.getValueIndex(selectedCode));
			}
		}
	}

	/**
	 * Selected the specified value in the ListBox provided.
	 * 
	 * The specified value will be inserted into th ListBox if it does not
	 * exist.
	 * 
	 * @param listBox
	 *            The ListBox.
	 * @param selectedCode
	 *            The value to be selected.
	 */
	protected void setSelectedCode(ListBox listBox, String selectedCode) {
		if (selectedCode == null || "".equals(selectedCode.trim())) {
			// Do not select anything.
			return;
		}

		String currentCode;
		for (int i = 0; i < listBox.getItemCount(); i++) {
			currentCode = listBox.getValue(i);
			if (currentCode != null && !"".equals(currentCode)
					&& currentCode.equals(selectedCode)) {
				listBox.setSelectedIndex(i);
				return;
			}
		}

		// Insert the selected code into the list.
		if (selectedCode != null && !"".equals(selectedCode.trim())) {
			// Selected code not found in the description table.
			listBox.addItem(selectedCode + " " + txtConsts.DescNotAvail(),
					selectedCode);
			listBox.setSelectedIndex(listBox.getItemCount() - 1);
		}
	}

	/**
	 * Helper method to convert String into Double
	 * 
	 * @param doubleStr
	 *            The double String.
	 * @return the corresponding double value or 0 if the double String cannot
	 *         be parsed.
	 */
	protected double parseDouble(String doubleStr) {
		try {
			return Double.parseDouble(doubleStr);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Helper method to convert String into int.
	 * 
	 * @param doubleStr
	 *            The int String.
	 * @return the corresponding int value or 0 if the int String cannot be
	 *         parsed..
	 */
	protected int parseInt(String intStr) {
		try {
			return Integer.parseInt(intStr);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	/**
	 * Helper method to get the selected value from the listbox.
	 * 
	 * @param listBox
	 * 
	 * @return The selected value in the provided ListBox. Returns empty string
	 *         if no entry is selected in the provided ListBox.
	 */
	protected String getSelectedValue(ListBox listBox) {
		if (listBox == null) {
			throw new IllegalArgumentException("listBox cannot be null.");
		} else {
			int selectedIndex = listBox.getSelectedIndex();
			if (selectedIndex >= 0) {
				return listBox.getValue(selectedIndex);
			} else {
				return "";
			}
		}
	}

	/**
	 * Format the double value for the presenter.
	 * 
	 * @param value
	 *            double value.
	 * @return The String value to be displayed to user.
	 */
	protected String formatDouble(double value) {
		// Anchor point to format the double value and display to user.
		return Double.toString(value);
	}

	/**
	 * Format the int value for the presenter.
	 * 
	 * @param value
	 *            int value.
	 * @return The String value to be displayed to user.
	 */
	protected String formatInt(int value) {
		// Anchor point to format the double value and display to user.
		return Integer.toString(value);
	}

	/**
	 * Format the SQL date for the presenter.
	 * 
	 * @param value
	 *            sql Date.
	 * @return The String value to be displayed to user. Empty String if sql
	 *         Date is null.
	 */
	protected String formatSqlDate(Date value) {
		// Anchor point to format the double value and display to user.
		if (value != null) {
			// Anchor point to format the sql Date.
			return value.toString();
		}

		return "";
	}

	/**
	 * Retrieve the date from DateField and return it as a sql Date object.
	 * 
	 * @param dateField
	 *            The dateField which the data will be retrieved from.
	 * @return The sql Date with the same date value stored in the DateField.
	 */
	protected Date getSqlDate(DateField dateField) {
		if (dateField != null && dateField.getValue() != null) {
			return new Date(dateField.getValue().getTime());
		} else {
			return null;
		}
	}

	/**
	 * Returns the id of the current technician.
	 * 
	 * @return The id of the currently logged-in technician.
	 */
	protected String getUserId() {
		return FieldServices.getInstance().getLoginDetail().getUserDetails()
				.getUserId();
	}

	/**
	 * Returns the id of the current technician.
	 * 
	 * @return The id of the currently logged-in technician.
	 */
	protected String getCurrPlant() {
		return FieldServices.getInstance().getPlant();
	}

	/**
	 * Validate the input value and display a pop up message if the input value
	 * is not an integer.
	 * 
	 * @param value
	 *            The integer value in String format.
	 * @param fieldName
	 *            The field name of the input value.
	 * @param canEmpty
	 *            Indicate whether this field can be empty or not.
	 * 
	 * @return true if the string is an integer, false otherwise.
	 * 
	 */
	protected boolean validateIntField(String value, String fieldName,
			boolean canEmpty) {
		if ((value == null || (value != null && "".equals(value.trim())))
				&& canEmpty) {
			// allowing null or empty string
			return true;
		} else if ((value == null || (value != null && "".equals(value.trim())))
				&& !canEmpty) {
			// not allowing null or empty string
			new ExtCmsMessageBox().alert(txtConsts.InvalidIntInput(fieldName));
			return false;
		}
		// Parse the non-empty string.
		else {
			try {
				Integer.parseInt(value.trim());
				return true;
			} catch (NumberFormatException e) {
				new ExtCmsMessageBox().alert(txtConsts
						.InvalidIntInput(fieldName));
				return false;
			}
		}
	}

	protected void updateTicketAdvanceToken(String flag) {
		String value = Format.format(
				HistoryConstants.SERVICE_TICKET_ADVANCE_VALUE, flag);
		String token = History.getToken();
		if (token.indexOf(HistoryConstants.SERVICE_TICKET_ADVANCE_KEY
				+ HistoryConstants.VALUE_SPLITTER) > 0) {
			token = token.replaceAll(
					HistoryConstants.SERVICE_TICKET_ADVANCE_REGEX, value);
		} else {
			token += value;
		}
		// don't fire any events
		History.newItem(token, false);
	}

	public void updateTicketPanelToken(int panelNumber) {
		String value = Format.format(HistoryConstants.TAB_PANEL_VALUE,
				panelNumber);
		String token = History.getToken();
		if (token.indexOf(HistoryConstants.TAB_PANEL_KEY
				+ HistoryConstants.VALUE_SPLITTER) > 0) {
			token = token.replaceAll(HistoryConstants.TAB_PANEL_REGEX, value);
		} else {
			token += value;
		}
		// don't fire any events
		History.newItem(token, false);
	}

	public static void setCookie(String key, String value) {
		setCookie(key, value, 7);
	}

	@SuppressWarnings("deprecation")
	public static void setCookie(String key, String value, int days) {
		java.util.Date expire = new java.util.Date();
		expire.setDate(expire.getDate() + days);
		Cookies.setCookie(key, value, expire);
	}

	public static void setSessionCookie(String key, String value) {
		Cookies.setCookie(key, value);
	}

	public static String getCookie(String key) {
		return Cookies.getCookie(key);
	}

	/**
	 * For TESTING ONLY
	 * 
	 * @param presenter
	 * @return nicely formatted class name
	 */
	public String getClassName(BasePresenter presenter) {
		String rval = "";
		String name = presenter.getClass().getName();
		int index = name.lastIndexOf(".");
		if (index <= 0) {
			return rval;
		}

		try {
			name = name.substring(index + 1);
			name = name.replaceAll("PresenterImpl", "");
			rval = name.replaceAll("Presenter", "");
		} catch (Exception e) {
		}

		return rval;
	}

}
