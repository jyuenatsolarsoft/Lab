package com.cms.gwt.fs.client.model.details;

import java.util.ArrayList;
import java.util.List;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * The detail list of the service ticket.
 *
 */
public class ServiceDetailList extends FSModel {
	    
    private List<ServiceDetailListEntry> serviceDetailList = new ArrayList<ServiceDetailListEntry>();

	public List<ServiceDetailListEntry> getServiceDetailList() {
		return serviceDetailList;
	}

	public void addServiceDetailList(ServiceDetailListEntry entry) {
		serviceDetailList.add(entry);
	}
    
    

}
