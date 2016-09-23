package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_AUDIT_LOG")
public class MemberAuditLog implements Serializable {

	private static final long serialVersionUID = 1808893705637771421L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "ACCOUNT", length = 64)
	private String account;
	
	@Column(name = "ACTION", length = 8)
	private String action;

	@Column(name = "NOTE", length = 128)
	private String note;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;

	@Column(name = "IP_ADDRESS", length = 64)
	private String ipAddress;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
}
