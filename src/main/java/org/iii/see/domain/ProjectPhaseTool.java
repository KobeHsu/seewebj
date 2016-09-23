package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "PROJECT_PHASE_TOOL")
public class ProjectPhaseTool implements Serializable {

	private static final long serialVersionUID = 1693905783243027571L;

	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "projectPhaseUuid", column = @Column(name = "PROJECT_PHASE_UUID", length = 36)),
        @AttributeOverride(name = "toolUuid", column = @Column(name = "TOOL_UUID", length = 36)) })
	private ProjectPhaseToolPK id;

	@Column(name = "SERIAL_NO")
	private Short serialNo;

	@Column(name = "STATUS", length = 8)
	private String status;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROJECT_PHASE_UUID")
	private ProjectPhase projectPhase;			
	
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="TOOL_UUID")
    private Tool tool;
	private Timestamp createTime;
	private String uuid;
	private String phaseUuid;
	private String projectUuid;
	private String toolUuid;

	public ProjectPhaseToolPK getId() {
		return id;
	}

	public void setId(ProjectPhaseToolPK id) {
		this.id = id;
	}

	public Short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Short serialNo) {
		this.serialNo = serialNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ProjectPhase getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(ProjectPhase projectPhase) {
		this.projectPhase = projectPhase;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public void setPhaseUuid(String phaseUuid) {
		this.phaseUuid = phaseUuid;
	}

	public void setToolUuid(String toolUuid) {
		this.toolUuid = toolUuid;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}
