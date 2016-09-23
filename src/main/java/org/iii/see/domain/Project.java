package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECT")
public class Project implements Serializable {

	private static final long serialVersionUID = 1415639999286826598L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "NAME", length = 64)
	private String name;

	@Column(name = "PROJECT_TEMPLATE_UUID", length = 36)
	private String projectTemplateUuid;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
	@Column(name = "STATUS", length = 8)
	private String status;
	
	@Column(name = "BEGIN_DATE")
	private Date beginDate; 

	@Column(name = "END_DATE")
	private Date endDate; 
		
	@Column(name = "CREATOR", length = 64)
	private String creator;

	@Column(name = "UPDATER", length = 64)
	private String updater;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getProjectTemplateUuid() {
		return projectTemplateUuid;
	}

	public void setProjectTemplateUuid(String projectTemplateUuid) {
		this.projectTemplateUuid = projectTemplateUuid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUpdater() {
		return updater;
	}

	public void setUpdater(String updater) {
		this.updater = updater;
	}
	
}
