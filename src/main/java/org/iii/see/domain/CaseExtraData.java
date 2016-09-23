package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CASE_EXTRA_DATA")
public class CaseExtraData implements Serializable {

	private static final long serialVersionUID = -4427687670364302970L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "DATA_UUID", length = 36)
	private String dataUuid;

	@Column(name = "DEFINITION_UUID", length = 36)
	private String definitionUuid;
	
	@Column(name = "CONTENT", length = 2147483647)
	private String content;

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

	public String getDataUuid() {
		return dataUuid;
	}

	public void setDataUuid(String dataUuid) {
		this.dataUuid = dataUuid;
	}

	public String getDefinitionUuid() {
		return definitionUuid;
	}

	public void setDefinitionUuid(String definitionUuid) {
		this.definitionUuid = definitionUuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
