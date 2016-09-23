package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORUM_THREAD_REPLY")
public class ForumThreadReply implements Serializable {

	private static final long serialVersionUID = -8555600101693486157L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "THREAD_UUID", length = 36)
	private String threadUuid;
	
	@Column(name = "CONTENT", length = 2147483647)
	private String content;

	@Column(name = "AUTHOR_UUID", length = 36)
	private String authorUuid;

	@Column(name = "AUTHOR_NICKNAME", length = 32)
	private String authorNickname;
	
	@Column(name = "STATUS", length = 8)
	private String status;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;	

	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name = "LIKE_COUNT")
	private int likeCount;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getThreadUuid() {
		return threadUuid;
	}

	public void setThreadUuid(String threadUuid) {
		this.threadUuid = threadUuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthorUuid() {
		return authorUuid;
	}

	public void setAuthorUuid(String authorUuid) {
		this.authorUuid = authorUuid;
	}

	public String getAuthorNickname() {
		return authorNickname;
	}

	public void setAuthorNickname(String authorNickname) {
		this.authorNickname = authorNickname;
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

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}
}
