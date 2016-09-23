package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONA_BASIC_DATA")
public class PersonaBasicData implements Serializable {

	private static final long serialVersionUID = 8902320703269656523L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "PERSONA_NO", length = 8)
	private String personaNo;
	
	@Column(name = "REALNAME", length = 32)
	private String realname;

	@Column(name = "CATEGORY", length = 2147483647)
	private String category;

	@Column(name = "BACKGROUND", length = 2147483647)
	private String background;

	@Column(name = "BEHAVIOR", length = 2147483647)
	private String behavior;

	@Column(name = "TARGET", length = 2147483647)
	private String target;
	
	@Column(name = "CREATE_TIME")
	private Timestamp createTime;
	
	@Column(name = "UPDATE_TIME")
	private Timestamp updateTime;

	@Column(name = "CREATOR", length = 36)
	private String creator;

	@Column(name = "UPDATOR", length = 36)
	private String updator;
	
	@OneToOne(mappedBy="personaBasicData")
	private PersonaPortraitFile personaPortraitFile;	

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

	public String getPersonaNo() {
		return personaNo;
	}

	public void setPersonaNo(String personaNo) {
		this.personaNo = personaNo;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBehavior() {
		return behavior;
	}

	public void setBehavior(String behavior) {
		this.behavior = behavior;
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

	public PersonaPortraitFile getPersonaPortraitFile() {
		return personaPortraitFile;
	}

	public void setPersonaPortraitFile(PersonaPortraitFile personaPortraitFile) {
		this.personaPortraitFile = personaPortraitFile;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
