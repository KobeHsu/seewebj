package org.iii.see.form;

public class OnlineIssueDataFormBean extends BaseFormBean {

	private static final long serialVersionUID = -3161164587294683351L;

	private String uuid;

	private String projectUuid;
	
	private String title;
	
	private String content;
	
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
