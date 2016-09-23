package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_PORTRAIT_FILE")
public class CasePortraitFile implements Serializable {

	private static final long serialVersionUID = 2504649101549010236L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;
	
	@Column(name = "DATA_UUID", length = 36)
	private String dataUuid;

	@Column(name = "EXT_NAME", length = 8)
	private String extName;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name = "CREATOR", length = 36)
	private String creator;

	@Column(name = "UPDATOR", length = 36)
	private String updator;

	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="DATA_UUID")
	private CaseBasicData caseBasicData;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getExtName() {
		return extName;
	}

	public void setExtName(String extName) {
		this.extName = extName;
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

	public CaseBasicData getCaseBasicData() {
		return caseBasicData;
	}

	public void setCaseBasicData(CaseBasicData caseBasicData) {
		this.caseBasicData = caseBasicData;
	}

	public String getDataUuid() {
		return dataUuid;
	}

	public void setDataUuid(String dataUuid) {
		this.dataUuid = dataUuid;
	}
	
}
