package org.iii.see.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MemberHistoryPK implements Serializable {

	private static final long serialVersionUID = 8737832171476196763L;

	@Column(name = "ACCOUNT", length = 64)
	private String account;

	@Column(name = "ACTIONER", length = 64)
	private String actioner;
	
	@Column(name = "ACTION", length = 6)
	private String action;	
	
	@Column(name = "ACTION_TIME")
	private Timestamp actionTime;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getActioner() {
		return actioner;
	}

	public void setActioner(String actioner) {
		this.actioner = actioner;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Timestamp getActionTime() {
		return actionTime;
	}

	public void setActionTime(Timestamp actionTime) {
		this.actionTime = actionTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result
				+ ((actionTime == null) ? 0 : actionTime.hashCode());
		result = prime * result
				+ ((actioner == null) ? 0 : actioner.hashCode());
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
		MemberHistoryPK other = (MemberHistoryPK) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (action == null) {
			if (other.action != null)
				return false;
		} else if (!action.equals(other.action))
			return false;
		if (actionTime == null) {
			if (other.actionTime != null)
				return false;
		} else if (!actionTime.equals(other.actionTime))
			return false;
		if (actioner == null) {
			if (other.actioner != null)
				return false;
		} else if (!actioner.equals(other.actioner))
			return false;
		return true;
	}
	
}
