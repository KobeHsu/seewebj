package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account implements Serializable {
	
	private static final long serialVersionUID = 4503239006623218122L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;
	
	@Column(name = "ACCOUNT", length = 64)
	private String account;
	
	@Column(name = "PASSWORD", length = 32)
	private String password;
	
	@Column(name = "REALNAME", length = 32)
	private String realname;	
	
	@Column(name = "NICKNAME", length = 32)
	private String nickname;
	
	@Column(name = "EMAIL", length = 128)
	private String email;

	@Column(name = "PHONE", length = 32)
	private String phone;
		
	@Column(name = "COMPANY", length = 128)
	private String company;

	@Column(name = "TITLE", length = 64)
	private String title;
	
	@Column(name = "STATUS", length = 8)
	private String status;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "VERIFY_TIME")
	private Timestamp verifyTime;

	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Timestamp verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Account [uuid=" + uuid + ", account=" + account + ", password="
				+ password + ", realname=" + realname + ", nickname="
				+ nickname + ", email=" + email + ", phone=" + phone
				+ ", company=" + company + ", title=" + title + ", status="
				+ status + ", createTime=" + createTime + ", verifyTime="
				+ verifyTime + ", updateTime=" + updateTime + "]";
	}	
}
