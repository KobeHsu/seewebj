package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORUM")
public class Forum implements Serializable {
	
	private static final long serialVersionUID = -8299100823341572688L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;	
	
	@Column(name = "TITLE", length = 32)
	private String title;
	
	@Column(name = "SUMMARY", length = 256)
	private String summary;	

	@Column(name = "STATUS", length = 8)
	private String status;
	
	@Column(name = "SERIAL_NO")
	private short serialNo;
	
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

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(short serialNo) {
		this.serialNo = serialNo;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
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
	
}
