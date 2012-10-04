package com.cms.gwt.fs.client.model.details;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * An entry on the service detail list.
 *
 */
public class ServiceDetailListEntry extends FSModel {

    private String sequence;
    private String descriptionLine1;
    private String manpower;
    private String timeEstimate;
    private boolean warrantyTask;
    private String status;
    private String statusDescription;
    private String fromProcedure;
    
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getDescriptionLine1() {
		return descriptionLine1;
	}
	public void setDescriptionLine1(String descriptionLine1) {
		this.descriptionLine1 = descriptionLine1;
	}
	public String getManpower() {
		return manpower;
	}
	public void setManpower(String manpower) {
		this.manpower = manpower;
	}
	public String getTimeEstimate() {
		return timeEstimate;
	}
	public void setTimeEstimate(String timeEstimate) {
		this.timeEstimate = timeEstimate;
	}
	public boolean isWarrantyTask() {
		return warrantyTask;
	}
	public void setWarrantyTask(boolean warrantyTask) {
		this.warrantyTask = warrantyTask;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public String getFromProcedure() {
		return fromProcedure;
	}
	public void setFromProcedure(String fromProcedure) {
		this.fromProcedure = fromProcedure;
	}
    
    
}
