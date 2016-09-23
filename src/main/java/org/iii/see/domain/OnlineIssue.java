package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ONLINE_ISSUE")
public class OnlineIssue implements Serializable {

	private static final long serialVersionUID = 2241751410203934194L;
	
	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;	
	
	@Column(name = "TITLE", length = 32)
	private String title;
	
	@Column(name = "CONTENT", length = 2147483647)
	private String content;
	
	@Column(name = "REPLY_CONTENT", length = 2147483647)
	private String replyContent;

	@Column(name = "STATUS", length = 8)
	private String status;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	

	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name = "SUBMITTER", length = 36)
	private String submitter;

	@Column(name = "PROCESSOR", length = 36)
	private String processor;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}
	
}
