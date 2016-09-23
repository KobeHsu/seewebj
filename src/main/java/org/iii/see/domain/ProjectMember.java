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
@Table(name = "PROJECT_MEMBER")
public class ProjectMember implements Serializable {

	private static final long serialVersionUID = 1458447964518792443L;

	@EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "projectUuid", column = @Column(name = "PROJECT_UUID", length = 36)),
        @AttributeOverride(name = "memberAccount", column = @Column(name = "MEMBER_ACCOUNT", length = 64)) })
	private ProjectMemberPK id;
	
	@Column(name = "ROLE", length = 8)
	private String role;
	
	@Column(name = "CREATOR", length = 64)
	private String creator;

	@Column(name = "UPDATER", length = 64)
	private String updater;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;
	
    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name="PROJECT_UUID")
    private Project project;	

	public ProjectMemberPK getId() {
		return id;
	}

	public void setId(ProjectMemberPK id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
	
}
