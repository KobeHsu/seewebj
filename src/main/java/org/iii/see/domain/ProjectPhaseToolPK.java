package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectPhaseToolPK implements Serializable {

	private static final long serialVersionUID = -8889015405110928353L;

	@Column(name = "PROJECT_PHASE_UUID", length = 36)
	private String projectPhaseUuid;

	@Column(name = "TOOL_UUID", length = 36)
	private String toolUuid;

	public String getProjectPhaseUuid() {
		return projectPhaseUuid;
	}

	public void setProjectPhaseUuid(String projectPhaseUuid) {
		this.projectPhaseUuid = projectPhaseUuid;
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
				+ ((projectPhaseUuid == null) ? 0 : projectPhaseUuid.hashCode());
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
		ProjectPhaseToolPK other = (ProjectPhaseToolPK) obj;
		if (projectPhaseUuid == null) {
			if (other.projectPhaseUuid != null)
				return false;
		} else if (!projectPhaseUuid.equals(other.projectPhaseUuid))
			return false;
		if (toolUuid == null) {
			if (other.toolUuid != null)
				return false;
		} else if (!toolUuid.equals(other.toolUuid))
			return false;
		return true;
	}
}
