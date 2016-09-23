package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "PROJECT_PHASE")
public class ProjectPhase implements Serializable {

	private static final long serialVersionUID = -2024633466042198085L;
	
	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "PROJECT_TEMPLATE_PHASE_UUID", length = 36)
	private String projectTemplatePhaseUuid;
	
	@Column(name = "SERIAL_NO")
	private short serialNo;

	@Column(name = "NAME", length = 64)
	private String name;	
	
	@Column(name = "CREATOR", length = 36)
	private String creator;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy="projectPhase")
    @OrderBy("serialNo ASC")
    private Collection<ProjectPhaseTool> projectPhaseTools;
	private Timestamp updateTime;

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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getProjectTemplatePhaseUuid() {
		return projectTemplatePhaseUuid;
	}

	public void setProjectTemplatePhaseUuid(String projectTemplatePhaseUuid) {
		this.projectTemplatePhaseUuid = projectTemplatePhaseUuid;
	}

	public Collection<ProjectPhaseTool> getProjectPhaseTools() {
		return projectPhaseTools;
	}

	public void setProjectPhaseTools(Collection<ProjectPhaseTool> projectPhaseTools) {
		this.projectPhaseTools = projectPhaseTools;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
}
