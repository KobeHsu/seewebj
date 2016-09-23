package org.iii.see.form.administration;

import org.iii.see.form.BaseFormBean;

public class ToolManagementFormBean extends BaseFormBean {

	private static final long serialVersionUID = -2765958936871701735L;
	
	private String queryName;
	
	private String uuid;

	private String name;
	
	private String description;
	
	private String url;

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
