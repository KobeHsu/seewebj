package org.iii.see.enumeration;

public enum ProjectMemberRoleEnum {

	OWNER("OWNER", "專案擁有者"),
	MANAGER("MANAGER", "專案經理"),
	MEMBER("MEMBER", "專案成員"),
	;

	private String code;
	private String desc;
	
	ProjectMemberRoleEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}			
	
}
