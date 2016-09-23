package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_HISTORY")
public class MemberHistory implements Serializable {

	private static final long serialVersionUID = 6391156957834426295L;

	@EmbeddedId
	private MemberHistoryPK id;
	
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

	public MemberHistoryPK getId() {
		return id;
	}

	public void setId(MemberHistoryPK id) {
		this.id = id;
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

	public String getAgreementRequired() {
		return agreementRequired;
	}

	public void setAgreementRequired(String agreementRequired) {
		this.agreementRequired = agreementRequired;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberHistory other = (MemberHistory) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
