package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectTemplatePhaseToolPK implements Serializable {

	private static final long serialVersionUID = 1220414965170867966L;

	@Column(name = "PROJECT_TEMPLATE_PHASE_UUID", length = 36)
	private String projectTemplatePhaseUuid;

	@Column(name = "TOOL_UUID", length = 36)
	private String toolUuid;
	
	public String getProjectTemplatePhaseUuid() {
		return projectTemplatePhaseUuid;
	}

	public void setProjectTemplatePhaseUuid(String projectTemplatePhaseUuid) {
		this.projectTemplatePhaseUuid = projectTemplatePhaseUuid;
	}

	public String getToolUuid() {
		return toolUuid;
	}

	public void setToolUuid(String toolUuid) {
		this.toolUuid = toolUuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((projectTemplatePhaseUuid == null) ? 0
						: projectTemplatePhaseUuid.hashCode());
		result = prime * result
				+ ((toolUuid == null) ? 0 : toolUuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectTemplatePhaseToolPK other = (ProjectTemplatePhaseToolPK) obj;
		if (projectTemplatePhaseUuid == null) {
			if (other.projectTemplatePhaseUuid != null)
				return false;
		} else if (!projectTemplatePhaseUuid
				.equals(other.projectTemplatePhaseUuid))
			return false;
		if (toolUuid == null) {
			if (other.toolUuid != null)
				return false;
		} else if (!toolUuid.equals(other.toolUuid))
			return false;
		return true;
	}
	
}
