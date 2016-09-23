package org.iii.see.form.administration;

import org.iii.see.form.BaseFormBean;

public class OnlineIssueManagementFormBean extends BaseFormBean {

	private static final long serialVersionUID = 3218223820963792840L;

	private String queryBeginDate;
	
	private String queryEndDate;
	
	private String queryProject;
	
	private String queryStatus;
	
	private String uuid;

	private String replyContent; 
	
	private String status;
	
	public String getQueryBeginDate() {
		return queryBeginDate;
	}

	public void setQueryBeginDate(String queryBeginDate) {
		this.queryBeginDate = queryBeginDate;
	}

	public String getQueryEndDate() {
		return queryEndDate;
	}

	public void setQueryEndDate(String queryEndDate) {
		this.queryEndDate = queryEndDate;
	}

	public String getQueryProject() {
		return queryProject;
	}

	public void setQueryProject(String queryProject) {
		this.queryProject = queryProject;
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
