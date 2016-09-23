package org.iii.see.form.datamanagement;

import java.sql.Timestamp;

import org.iii.see.form.BaseFormBean;

public class CaseSimpleDataFormBean extends BaseFormBean {

	private static final long serialVersionUID = 4985738023838872967L;
	
	private String uuid;	
	
	private String projectUuid;	
	
	private String caseNo;
	
	private String realname;	

	private String sex;	

	private short age;	

	private String businessPhone;
	
	private String mobilePhone;
	
	private String address;
	
	private Timestamp createTime;
	
	private String portraitFileUuid;
	
	private String portraitFileName;

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

	public String getCaseNo() {
		return caseNo;
	}

	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
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

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getPortraitFileName() {
		return portraitFileName;
	}

	public void setPortraitFileName(String portraitFileName) {
		this.portraitFileName = portraitFileName;
	}

	public String getPortraitFileUuid() {
		return portraitFileUuid;
	}

	public void setPortraitFileUuid(String portraitFileUuid) {
		this.portraitFileUuid = portraitFileUuid;
	}		
	
}
