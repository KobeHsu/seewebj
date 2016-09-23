package org.iii.see.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProjectMemberPK implements Serializable {

	private static final long serialVersionUID = 9147383259967487241L;

	@Column(name = "PROJECT_UUID", length = 36)
	private String projectUuid;

	@Column(name = "MEMBER_ACCOUNT", length = 64)
	private String memberAccount;

	public String getProjectUuid() {
		return projectUuid;
	}

	public void setProjectUuid(String projectUuid) {
		this.projectUuid = projectUuid;
	}

	public String getMemberAccount() {
		return memberAccount;
	}

	public void setMemberAccount(String memberAccount) {
		this.memberAccount = memberAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((memberAccount == null) ? 0 : memberAccount.hashCode());
		result = prime * result
				+ ((projectUuid == null) ? 0 : projectUuid.hashCode());
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
		ProjectMemberPK other = (ProjectMemberPK) obj;
		if (memberAccount == null) {
			if (other.memberAccount != null)
				return false;
		} else if (!memberAccount.equals(other.memberAccount))
			return false;
		if (projectUuid == null) {
			if (other.projectUuid != null)
				return false;
		} else if (!projectUuid.equals(other.projectUuid))
			return false;
		return true;
	}
}
