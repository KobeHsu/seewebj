package org.iii.see.form;

public class ProjectWizardFormBean extends BaseFormBean {

	private static final long serialVersionUID = 1874337834457792899L;

	private String uuid;
	
	private String projectUuid;
	
	private String name;
	
	private short serialNo;
	
	private String toolUuids;

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

	public String getToolUuids() {
		return toolUuids;
	}

	public void setToolUuids(String toolUuids) {
		this.toolUuids = toolUuids;
	}
	
}
