package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER")
public class Member implements Serializable {

	private static final long serialVersionUID = 8963265932167986676L;

	@Id
	@Column(name = "ACCOUNT", length = 64)
	private String account;
	
	@Column(name = "PASSWORD", length = 32)
	private String password;
	
	@Column(name = "REALNAME", length = 32)
	private String realname;	
	
	@Column(name = "NICKNAME", length = 64)
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

	@Column(name = "SOURCE", length = 8)
	private String source;

	@Column(name = "OWN_PROJECT_AUTHORITY", length = 1)
	private String ownPrjoectAuthority;
	
	@Column(name = "OWN_PROJECT_MAX_NUMBER")
	private Short ownPrjoectMaxNumber;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "VERIFY_TIME")
	private Timestamp verifyTime;

	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name = "CREATOR", length = 64)
	private String creator;

	@Column(name = "UPDATER", length = 64)
	private String updater;

	@Column(name = "AGREEMENT_VERSION", length = 16)
	private String agreementVersion;

	@Column(name = "AGREE_TIME")
	private Timestamp agreeTime;
	
	@Column(name = "AGREEMENT_REQUIRED", length = 1)
	private String agreementRequired;	
	
	public String getAgreementVersion() {
		return agreementVersion;
	}

	public void setAgreementVersion(String agreementVersion) {
		this.agreementVersion = agreementVersion;
	}

	public Timestamp getAgreeTime() {
		return agreeTime;
	}

	public void setAgreeTime(Timestamp agreeTime) {
		this.agreeTime = agreeTime;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getOwnPrjoectAuthority() {
		return ownPrjoectAuthority;
	}

	public void setOwnPrjoectAuthority(String ownPrjoectAuthority) {
		this.ownPrjoectAuthority = ownPrjoectAuthority;
	}

	public Short getOwnPrjoectMaxNumber() {
		return ownPrjoectMaxNumber;
	}

	public void setOwnPrjoectMaxNumber(Short ownPrjoectMaxNumber) {
		this.ownPrjoectMaxNumber = ownPrjoectMaxNumber;
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
	
	public String getAgreementRequired() {
		return agreementRequired;
	}

	public void setAgreementRequired(String agreementRequired) {
		this.agreementRequired = agreementRequired;
	}
	
}
