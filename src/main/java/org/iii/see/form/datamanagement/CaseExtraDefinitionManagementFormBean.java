package org.iii.see.form.datamanagement;

import org.iii.see.form.BaseFormBean;


public class CaseExtraDefinitionManagementFormBean extends BaseFormBean {
	
	private static final long serialVersionUID = -5746190989323901400L;

	private String uuid;
	
	private String projectUuid;
	
	private String name;
	
	private short serialNo;
	
	private String valueType;
	
	private short valueLength;
	
	private String valueMeasure;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getProjectUuid() {
		return projectUuid;
	}
	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public short getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(short serialNo) {
		this.serialNo = serialNo;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public short getValueLength() {
		return valueLength;
	}
	public void setValueLength(short valueLength) {
		this.valueLength = valueLength;
	}
	public String getValueMeasure() {
		return valueMeasure;
	}
	public void setValueMeasure(String valueMeasure) {
		this.valueMeasure = valueMeasure;
	}	
}
