package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FORUM_THREAD")
public class ForumThread implements Serializable {

	private static final long serialVersionUID = 5818236936813044384L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "FORUM_UUID", length = 36)
	private String forumUuid;
	
	@Column(name = "TITLE", length = 32)
	private String title;
	
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

	@Column(name = "COMMENT_COUNT")
	private int commentCount;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getForumUuid() {
		return forumUuid;
	}

	public void setForumUuid(String forumUuid) {
		this.forumUuid = forumUuid;
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

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}
}
