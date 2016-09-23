package org.iii.see.form.datamanagement;

import java.util.List;
import java.util.Map;

import org.iii.see.form.BaseFormBean;


public class CaseCompositeDataFormBean extends BaseFormBean {

	private static final long serialVersionUID = 5341592903193697612L;

	private String uuid;	
	
	private String projectUuid;	
	
	private String caseNo;
	
	private String realname;	
	
	private String sex;	
	
	private short age;	
	
	private short height;	
	
	private short weight;
	
	private String occupation;
	
	private int income;
	
	private String businessPhone;
	
	private String mobilePhone;
	
	private String address;
	
	private List<Map<String, String>> caseExtraDataList;

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

	public List<Map<String, String>> getCaseExtraDataList() {
		return caseExtraDataList;
	}

	public void setCaseExtraDataList(List<Map<String, String>> caseExtraDataList) {
		this.caseExtraDataList = caseExtraDataList;
	}

}
