package org.iii.see.domain;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT_TEMPLATE_PHASE")
public class ProjectTemplatePhase implements Serializable {

	private static final long serialVersionUID = -7790870356569989559L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_TEMPLATE_UUID", length = 36)
	private String projectTemplateUuid;
	
	@Column(name = "SERIAL_NO")
	private Short serialNo;
	
	@Column(name = "NAME", length = 64)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy="projectTemplatePhase")
    @OrderBy("serialNo ASC")
    private Collection<ProjectTemplatePhaseTool> projectTemplatePhaseTools;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getProjectTemplateUuid() {
		return projectTemplateUuid;
	}

	public void setProjectTemplateUuid(String projectTemplateUuid) {
		this.projectTemplateUuid = projectTemplateUuid;
	}

	public Short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
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

	public Collection<ProjectTemplatePhaseTool> getProjectTemplatePhaseTools() {
		return projectTemplatePhaseTools;
	}

	public void setProjectTemplatePhaseTools(Collection<ProjectTemplatePhaseTool> projectTemplatePhaseTools) {
		this.projectTemplatePhaseTools = projectTemplatePhaseTools;
	}
	
}
