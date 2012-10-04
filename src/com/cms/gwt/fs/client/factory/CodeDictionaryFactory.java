package com.cms.gwt.fs.client.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.cms.gwt.common.client.ExtCmsMessageBox;
import com.cms.gwt.fs.client.TextConstants;
import com.cms.gwt.fs.client.exception.ActionNotSupportedException;
import com.cms.gwt.fs.client.model.CodeDictionary;
import com.cms.gwt.fs.client.model.StockLocationDictionary;
import com.cms.gwt.fs.client.model.TaxGroupDictionary;
import com.cms.gwt.fs.client.rpc.ActionServices;
import com.cms.gwt.fs.client.rpc.action.GetCodeDescription;
import com.cms.gwt.fs.client.rpc.action.GetStockLocationDictionary;
import com.cms.gwt.fs.client.rpc.action.GetTaxGroupDictionary;
import com.cms.gwt.fs.client.rpc.callback.GotCodeDescription;
import com.cms.gwt.fs.client.rpc.callback.GotStockLocationDictionary;
import com.cms.gwt.fs.client.rpc.callback.GotTaxGroupDictionary;
import com.google.gwt.core.client.GWT;

/**
 * Factory class to retrieve the code and description from the backend.
 * 
 * There are three pairs of XML request/response retrieving the codes and locale sensitive code description.
 * The same kind of XML message can retrieve different types of code from the backend by altering 
 * XML element name while maintaining the same XML structure.
 * 
 * 1. DataSet   i.e. Get_ShipToBillTo, Get_TaxCodes
 * - The code type is specified as the element tag name in the request.
 * - Parameters could be specified as the value of a subelement in the request. 
 *   For example, key may be needed to get the code.
 * - The returned code and description are returned in a code-type specific xml structure  
 *   which is then wrapped in a DataSet xml structure.
 * 
 * 2. CodeList i.e. getComplainCodeList, getPriorityCodeList
 * - The code type is specified as the element tag name in the request.
 * - Parameters could be specified as the value of a subelement in the request.
 * - The code and description are presented in a code/value xml structure.
 * 
 * 3. StatusList with parameters i.e. getStatusList
 * - The code type is specified as the value of a subelement in the request.
 * - The code and description are presented in a code/value xml structure.
 *
 * Ultimately, the data returned are in the form of code and code description.
 * The CodeDictionaryFactory class shields the detail from the UI View and controller 
 * by always returning them an instance of the CodeDictionary through the factory methods.
 * 
 * Note that the DataSet is currently not handled in a generic way due to the inconsistent multi-level data 
 * structure in each of the dataRows. As a result, the ResponseHandler and the parser were coded individually. 
 * 
 *
 */
public class CodeDictionaryFactory {
		
	// Code List
	final static public String COMPLAIN_CODE = "ComplaintCodeList";
	final static public String PRIORITY_CODE = "PriorityCodeList";
	final static public String TECHNICAN_CODE = "TechnicianCodeList";	
	final static public String TERRITORY_CODE = "TerritoryCodeList";
	final static public String SKILL_CODE = "ServiceTicketSkillsCodeList";
	final static public String SCHEDULE_EVENT_TYPE = "ScheduledEventTypeList";
	final static public String PARENT_SPLIT_TYPE = "ParentSplitTypeCodeList";
	final static public String SPECIAL_EVENT_CODE = "ScheduledEventSpecialEventCodeList";
	final static public String SERVICE_CATEGORY_CODE = "ServiceCategoryCodeList";
	final static public String BILLING_CHARGE_TYPE = "ServiceTicketBillingChargeTypeList";
	final static public String MISC_CHARGE_CODE = "ServiceTicketMiscChargeCodeList";
	final static public String WORK_CODE = "WorkCodeCodeList";
		
	// Status List
	final static public String HEADER_STATUS_TYPE = "HEADER";	
	final static public String EVENT_STATUS_TYPE = "SCHEDEVENT";
	final static public String TASK_STATUS_TYPE = "TASK";
	final static protected String STATUS_CODE = "StatusCodeList";
	
	// DataSet
	final static public String TAX_GROUP_CODE = "taxGroupCodeList";
	final static public String STOCK_LOCATION_CODE = "StockLocationList	";
	
	protected final TextConstants txtConsts = (TextConstants) GWT
	.create(TextConstants.class);		
	
	final static public List<String> STATUS_TYPE_LIST = Collections.unmodifiableList(
			Arrays.asList(new String[]{ HEADER_STATUS_TYPE, EVENT_STATUS_TYPE, TASK_STATUS_TYPE }));
	
	final static public List<String> CODE_TYPE_LIST = Collections.unmodifiableList(
            Arrays.asList(new String[]{ COMPLAIN_CODE, PRIORITY_CODE, TECHNICAN_CODE,
            		TERRITORY_CODE, SKILL_CODE,
            		SCHEDULE_EVENT_TYPE, PARENT_SPLIT_TYPE, SPECIAL_EVENT_CODE, 
            		SERVICE_CATEGORY_CODE, BILLING_CHARGE_TYPE, MISC_CHARGE_CODE,
            		WORK_CODE }));
	
	final static public List<String> DATASET_LIST = Collections.unmodifiableList(
            Arrays.asList(new String[]{ })); 
	
		
	/** Cache the description up. */
	private static Map<String, CodeDictionary> dictionaries = new HashMap<String, CodeDictionary>();
	
	/** Cache up the tax group and tax code. */
	private static TaxGroupDictionary taxGroupDictionary;
	
	/** Cache up the stock location and bin location. */
	private static StockLocationDictionary stockLocationDictionary;	

	/**
	 * Returns an instance of CodeDictionary based on the specified dictionary name.
	 * 
	 * @param dictionaryName Please refer to the public constants in this class.
	 * 
	 * @return An instance of CodeDictionary.
	 */
	public static CodeDictionary getCodeDictionary(String dictionaryName)
	{
		CodeDictionary dictionary = dictionaries.get(dictionaryName);
		
		if (dictionary == null)
		{
			// Should have been retrieved from the server. log it.
		}
		
		return dictionary;
	}
	
	/**
	 * Request the code and descriptions from the backend.
	 * 
	 * @param dictionaryName The name of the CodeDictionary.
	 */
	private void retrieveCodeDictionary(String dictionaryName)
	{
		// If only dictionary name is provided, it is assumed that dictionaryName is the suffix 
		// of the request name.		
		try
		{
			ActionServices.App.getInstance().execute(GetCodeDescription.newInstance(dictionaryName), 
					new RetrieveCodeDictionaryCallback(dictionaryName));
		}
		catch (ActionNotSupportedException e)		
		{
			final String ERR_MSG = "Unable to retrieve code dictionary.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}
	
	
	/**
	 * Request the status code and descriptions from the backend 
	 * 
	 * @param type The type of the status code. 
	 */
	private void retrieveStatusCodeDictionary(String type)
	{		
		// The request name will always be the same for all type of status code.
		try
		{
			ActionServices.App.getInstance().execute(GetCodeDescription.newInstance(STATUS_CODE, type), 
					new RetrieveCodeDictionaryCallback(type));
		}
		catch (ActionNotSupportedException e)		
		{
			final String ERR_MSG = "Unable to retrieve status code dictionary.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}
	}	
	
	/**
	 * Helper method to request for the codes and descriptions in "dataSet" form from the backend.
	 */
	private void retrieveDataSetDictionary()
	{
		try
		{
			ActionServices.App.getInstance().execute(GetTaxGroupDictionary.newInstance(), 
					new RetrieveTaxGroupDictionaryCallback());
			
			ActionServices.App.getInstance().execute(GetStockLocationDictionary.newInstance(), 
					new RetrieveStockLocationDictionaryCallback());					
		}
		catch (ActionNotSupportedException e)		
		{
			final String ERR_MSG = "Unable to retrieve dataSet dictionary.<br>";
			Log.fatal(ERR_MSG, e);
			new ExtCmsMessageBox().alert(txtConsts.Error(), 
					txtConsts.InternalErrorMsg() + "<br>"+ ERR_MSG + e.getMessage());
		}		
	}
		
	/**
	 * Retrieve the code dictionaries.
	 */
	public void init()
	{
		for (String dictionaryName : CODE_TYPE_LIST)
		{	
			retrieveCodeDictionary(dictionaryName);
		}	
		
		// Retrieve the status code separately.
		for (String statusType : STATUS_TYPE_LIST)
		{
			retrieveStatusCodeDictionary(statusType);
		}
		
		// Retrieve the codes and descriptions in the form of "DataSet".
		retrieveDataSetDictionary();
	}
	
	/**
	 * Callback class to cache the code and description.
	 * 
	 *
	 */
	protected class RetrieveCodeDictionaryCallback extends GotCodeDescription
	{	
		private String dictionaryName;
		
		public RetrieveCodeDictionaryCallback(String dictionaryName)
		{
			this.dictionaryName = dictionaryName;
		}
		
		public void got(CodeDictionary dictionary) {
			
			dictionaries.put(dictionaryName, dictionary);
			
		}			
	}		
	
	
	/**
	 * Returns a TaxGroupDictionary which contains the description
	 * of the tax group and the corresponding tax rate code and tax name.
	 * 
	 * @return TaxGroupDictionary
	 */
	public static TaxGroupDictionary getTaxGroupDictionary()
	{
		return taxGroupDictionary; 
	}
	
	/**
	 * Returns a TaxGroupDictionary which contains the description
	 * of the tax group and the corresponding tax rate code and tax name.
	 * 
	 * @return TaxGroupDictionary
	 */
	public static StockLocationDictionary getStockLocationDictionary()
	{
		return stockLocationDictionary; 
	}	

	/**
	 * Callback class to cache the tax group and code.
	 * 
	 *
	 */
	protected class RetrieveTaxGroupDictionaryCallback extends GotTaxGroupDictionary
	{
		public void got(TaxGroupDictionary dictionary)
		{
			taxGroupDictionary = dictionary;
			dictionaries.put(dictionary.getDictionaryName(), dictionary);
		}
	}
	
	/**
	 * Callback class to cache the tax group and code.
	 * 
	 *
	 */
	protected class RetrieveStockLocationDictionaryCallback extends GotStockLocationDictionary
	{
		public void got(StockLocationDictionary dictionary)
		{
			stockLocationDictionary = dictionary;
			dictionaries.put(dictionary.getDictionaryName(), dictionary);
		}
	}	
}

