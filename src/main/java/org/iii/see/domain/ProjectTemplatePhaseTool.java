package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TEMPLATE_PHASE_TOOL")
public class ProjectTemplatePhaseTool implements Serializable {

	private static final long serialVersionUID = 1611834830335311136L;

	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "projectTemplatePhaseUuid", column = @Column(name = "PROJECT_TEMPLATE_PHASE_UUID", length = 36)),
        @AttributeOverride(name = "toolUuid", column = @Column(name = "TOOL_UUID", length = 36)) })
	private ProjectTemplatePhaseToolPK id;
	
	@Column(name = "SERIAL_NO")
	private Short serialNo;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROJECT_TEMPLATE_PHASE_UUID")
	private ProjectTemplatePhase projectTemplatePhase;			

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="TOOL_UUID")
    private Tool tool;
	
	public ProjectTemplatePhaseToolPK getId() {
		return id;
	}

	public void setId(ProjectTemplatePhaseToolPK id) {
		this.id = id;
	}

	public Short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public ProjectTemplatePhase getProjectTemplatePhase() {
		return projectTemplatePhase;
	}

	public void setProjectTemplatePhase(ProjectTemplatePhase projectTemplatePhase) {
		this.projectTemplatePhase = projectTemplatePhase;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((serialNo == null) ? 0 : serialNo.hashCode());
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
		ProjectTemplatePhaseTool other = (ProjectTemplatePhaseTool) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (serialNo == null) {
			if (other.serialNo != null)
				return false;
		} else if (!serialNo.equals(other.serialNo))
			return false;
		return true;
	}
	
}
