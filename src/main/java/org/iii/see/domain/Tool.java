package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TOOL")
public class Tool implements Serializable {

	private static final long serialVersionUID = 8809695642701886703L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;
	
	@Column(name = "NAME", length = 64)
	private String name;
	
	@Column(name = "DESCRIPTION", length = 512)
	private String description;
	
	@Column(name = "URL", length = 128)
	private String url;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
		
}
