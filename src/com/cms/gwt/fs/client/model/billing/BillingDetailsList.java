package com.cms.gwt.fs.client.model.billing;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

public class BillingDetailsList extends FSModel
{
    private List<BillingDetailsListEntry> billingDetails = new ArrayList<BillingDetailsListEntry>();
    
    public List<BillingDetailsListEntry> getEntries()
    {
    	return billingDetails;
    }
    
    public void addEntry(BillingDetailsListEntry entry)
    {
    	billingDetails.add(entry);
    }        
}
