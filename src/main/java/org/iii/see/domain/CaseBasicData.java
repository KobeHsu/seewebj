package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_BASIC_DATA")
public class CaseBasicData implements Serializable {

	private static final long serialVersionUID = 6116561611228760914L;
	
	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "CASE_NO", length = 8)
	private String caseNo;
	
	@Column(name = "REALNAME", length = 32)
	private String realname;
	
	@Column(name = "SEX", length = 1)
	private String sex;

	@Column(name = "AGE")
	private short age;

	@Column(name = "HEIGHT")
	private short height;

	@Column(name = "WEIGHT")
	private short weight;

	@Column(name = "OCCUPATION", length = 32)
	private String occupation;

	@Column(name = "INCOME")
	private int income;

	@Column(name = "BUSINESS_PHONE", length = 32)
	private String businessPhone;

	@Column(name = "MOBILE_PHONE", length = 32)
	private String mobilePhone;
	
	@Column(name = "ADDRESS", length = 128)
	private String address;

	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name = "CREATOR", length = 36)
	private String creator;

	@Column(name = "UPDATOR", length = 36)
	private String updator;
	
	@OneToOne(mappedBy="caseBasicData")
	private CasePortraitFile casePortraitFile;
	
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public short getHeight() {
		return height;
	}

	public void setHeight(short height) {
		this.height = height;
	}

	public short getWeight() {
		return weight;
	}

	public void setWeight(short weight) {
		this.weight = weight;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getBusinessPhone() {
		return businessPhone;
	}

	public void setBusinessPhone(String businessPhone) {
		this.businessPhone = businessPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}

	public CasePortraitFile getCasePortraitFile() {
		return casePortraitFile;
	}

	public void setCasePortraitFile(CasePortraitFile casePortraitFile) {
		this.casePortraitFile = casePortraitFile;
	}	
	
}
