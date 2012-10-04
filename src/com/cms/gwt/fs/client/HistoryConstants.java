package com.cms.gwt.fs.client;

/**
 * Used to make GWT History, as well as, BreadCrumbs.
 * 
 */
public class HistoryConstants {

	public static String TOKEN_SPLITTER = ";";
	public static String TOKEN_SPLITTER_REGEX = "\\;";
	public static String VALUE_SPLITTER = ":";
	public static String VALUE_SPLITTER_REGEX = "\\:";
	public static String CONCAT_VALUES = "|";
	public static String CONCAT_VALUES_REGEX = "\\|";

	public static String SERVICE_TICKET_LIST_KEY = "stl";
	public static String SERVICE_TICKET_LIST_VALUE = SERVICE_TICKET_LIST_KEY
			+ VALUE_SPLITTER + TOKEN_SPLITTER;
	public static String SERVICE_TICKET_EVENT_LIST = "L";
	public static String SERVICE_TICKET_EVENT_CALENDAR = "C";

	public static String SERVICE_TICKET_KEY = "st";
	public static String SERVICE_TICKET_VALUE = SERVICE_TICKET_KEY
			+ VALUE_SPLITTER + "{0}" + TOKEN_SPLITTER;

	public static String SERVICE_TICKET_ADVANCE_KEY = "stA";
	public static String SERVICE_TICKET_ADVANCE_VALUE = SERVICE_TICKET_ADVANCE_KEY
			+ VALUE_SPLITTER + "{0}" + TOKEN_SPLITTER;
	public static String SERVICE_TICKET_ADVANCE_REGEX = "stA\\:[F|T]{0,1}\\;";
	public static String SERVICE_TICKET_ADVANCE_TRUE = "T";
	public static String SERVICE_TICKET_ADVANCE_FALSE = "F";

	public static String TAB_PANEL_KEY = "stT"; //test
	public static String TAB_PANEL_VALUE = TAB_PANEL_KEY + VALUE_SPLITTER
			+ "{0}" + TOKEN_SPLITTER;
	public static String TAB_PANEL_REGEX = "stT\\:[0-9]{0,2}\\;";

	public static String SKILLS_PANEL_KEY = "stS";
	public static String SKILLS_PANEL_VALUE = SKILLS_PANEL_KEY + VALUE_SPLITTER
			+ "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES + "{2}"
			+ TOKEN_SPLITTER;

	public static String DETAILS_PANEL_KEY = "stD";
	public static String DETAILS_PANEL_VALUE = DETAILS_PANEL_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + TOKEN_SPLITTER;

	public static String SCHEDULE_PANEL_KEY = "stSc";
	public static String SCHEDULE_PANEL_VALUE = SCHEDULE_PANEL_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String WORK_HISTORY_PANEL_KEY = "stWH";
	public static String WORK_HISTORY_PANEL_VALUE = WORK_HISTORY_PANEL_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String WORK_HISTORY_COUNTER_KEY = "stWHC";
	public static String WORK_HISTORY_COUNTER_VALUE = WORK_HISTORY_COUNTER_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String WORK_HISTORY_ARRIVAL_INFO_KEY = "stWHAI";
	public static String WORK_HISTORY_ARRIVAL_INFO_VALUE = WORK_HISTORY_ARRIVAL_INFO_KEY
			+ VALUE_SPLITTER
			+ "{0}"
			+ CONCAT_VALUES
			+ "{1}"
			+ CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String TIME_ENTRY_KEY = "stTE";
	public static String TIME_ENTRY_VALUE = TIME_ENTRY_KEY + VALUE_SPLITTER
			+ "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES + "{2}"
			+ CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String TIME_ENTRY_ADD_KEY = "stTEA";
	public static String TIME_ENTRY_ADD_VALUE = TIME_ENTRY_ADD_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String TIME_ENTRY_EDIT_KEY = "stTEE";
	public static String TIME_ENTRY_EDIT_VALUE = TIME_ENTRY_EDIT_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + CONCAT_VALUES + "{4}"
			+ CONCAT_VALUES + "{5}" + TOKEN_SPLITTER;

	public static String TIME_ENTRY_ADD_EDIT_KEY = "stTEAE";
	public static String TIME_ENTRY_ADD_EDIT_VALUE = TIME_ENTRY_ADD_EDIT_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + CONCAT_VALUES + "{4}"
			+ TOKEN_SPLITTER;

	public static String MATERIAL_KEY = "stM";
	public static String MATERIAL_VALUE = MATERIAL_KEY + VALUE_SPLITTER + "{0}"
			+ CONCAT_VALUES + "{1}" + CONCAT_VALUES + "{2}" + CONCAT_VALUES
			+ "{3}" + TOKEN_SPLITTER;

	public static String MATERIAL_ADD_KEY = "stMA";
	public static String MATERIAL_ADD_VALUE = MATERIAL_ADD_KEY + VALUE_SPLITTER
			+ "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES + "{2}"
			+ CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

	public static String MATERIAL_EDIT_KEY = "stME";
	public static String MATERIAL_EDIT_VALUE = MATERIAL_EDIT_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + CONCAT_VALUES + "{4}"
			+ CONCAT_VALUES + "{5}" + CONCAT_VALUES + "{6}" + TOKEN_SPLITTER;

	public static String MATERIAL_ADD_EDIT_KEY = "stMAE";
	public static String MATERIAL_ADD_EDIT_VALUE = MATERIAL_ADD_EDIT_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + CONCAT_VALUES + "{4}"
			+ CONCAT_VALUES + "{5}" + TOKEN_SPLITTER;

	public static String BILLING_PANEL_KEY = "stB";
	public static String BILLING_PANEL_VALUE = BILLING_PANEL_KEY
			+ VALUE_SPLITTER + "{0}" + CONCAT_VALUES + "{1}" + CONCAT_VALUES
			+ "{2}" + CONCAT_VALUES + "{3}" + TOKEN_SPLITTER;

}
