package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_EXTRA_DEFINITION")
public class CaseExtraDefinition implements Serializable {

	private static final long serialVersionUID = 6236067319281143189L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;
	
	@Column(name = "NAME", length = 64)
	private String name;
	
	@Column(name = "SERIAL_NO")
	private short serialNo;
	
	@Column(name = "VALUE_TYPE", length = 16)
	private String valueType;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;
	
	@Column(name = "CREATOR", length = 36)
	private String creator;

	@Column(name = "VALUE_LENGTH")
	private short valueLength;

	@Column(name = "VALUE_MEASURE", length = 16)
	private String valueMeasure;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(short serialNo) {
		this.serialNo = serialNo;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
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

	public short getValueLength() {
		return valueLength;
	}

	public void setValueLength(short valueLength) {
		this.valueLength = valueLength;
	}

	public String getValueMeasure() {
		return valueMeasure;
	}

	public void setValueMeasure(String valueMeasure) {
		this.valueMeasure = valueMeasure;
	}
}
