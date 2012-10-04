package com.cms.gwt.fs.client.model.details;

import com.cms.gwt.fs.client.model.FSModel;

/**
 * Represents the entry on the service ticket detail.
 * 
 * On the backend, it is referred as service ticket task.
 *
 */
public class ServiceDetail extends FSModel {

    private int sequence;
    private String fromProcedure;
    private String workCode;
    private String workCodeDescription;
    private String descriptionLine1;
    private String descriptionLine2;
    private String descriptionLine3;
    private double manpower;
    private double timeEstimate;
    private boolean warrantyTask;
    private String status;
    private String statusDescription;
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getFromProcedure() {
		return fromProcedure;
	}
	public void setFromProcedure(String fromProcedure) {
		this.fromProcedure = fromProcedure;
	}
	public String getWorkCode() {
		return workCode;
	}
	public void setWorkCode(String workCode) {
		this.workCode = workCode;
	}
	public String getDescriptionLine1() {
		return descriptionLine1;
	}
	public void setDescriptionLine1(String descriptionLine1) {
		this.descriptionLine1 = descriptionLine1;
	}
	public String getDescriptionLine2() {
		return descriptionLine2;
	}
	public void setDescriptionLine2(String descriptionLine2) {
		this.descriptionLine2 = descriptionLine2;
	}
	public String getDescriptionLine3() {
		return descriptionLine3;
	}
	public void setDescriptionLine3(String descriptionLine3) {
		this.descriptionLine3 = descriptionLine3;
	}
	public double getManpower() {
		return manpower;
	}
	public void setManpower(double manpower) {
		this.manpower = manpower;
	}
	public double getTimeEstimate() {
		return timeEstimate;
	}
	public void setTimeEstimate(double timeEstimate) {
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
	public String getWorkCodeDescription() {
		return workCodeDescription;
	}
	public void setWorkCodeDescription(String workCodeDescription) {
		this.workCodeDescription = workCodeDescription;
	}
  
	
    
}
