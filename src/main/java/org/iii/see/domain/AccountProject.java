package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_PROJECT")
public class AccountProject implements Serializable {

	private static final long serialVersionUID = 6526473749828985569L;

	@Id
	@Column(name = "UUID", length = 36)
	private String uuid;

	@Column(name = "ACCOUNT_UUID", length = 36)
	private String accountUuid;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "PROJECT_ROLE_UUID", length = 36)
	private String projectRoleUuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAccountUuid() {
		return accountUuid;
	}

	public void setAccountUuid(String accountUuid) {
		this.accountUuid = accountUuid;
	}

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getProjectRoleUuid() {
		return projectRoleUuid;
	}

	public void setProjectRoleUuid(String projectRoleUuid) {
		this.projectRoleUuid = projectRoleUuid;
	}
}
