package org.iii.see.form;

import java.io.Serializable;

public class NewProjectFormBean implements Serializable {

	private static final long serialVersionUID = 2176859481406411319L;

	private String step;
	
	private String name;
	
	private String description;
	
	private String next;
	
	private String projectTemplateUuid;
	
	private String[] projectTemplatePhaseTool;
	
	public String getStep() {
		return step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public String getProjectTemplateUuid() {
		return projectTemplateUuid;
	}

	public void setProjectTemplateUuid(String projectTemplateUuid) {
		this.projectTemplateUuid = projectTemplateUuid;
	}

	public String[] getProjectTemplatePhaseTool() {
		return projectTemplatePhaseTool;
	}

	public void setProjectTemplatePhaseTool(String[] projectTemplatePhaseTool) {
		this.projectTemplatePhaseTool = projectTemplatePhaseTool;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}		
	
}
