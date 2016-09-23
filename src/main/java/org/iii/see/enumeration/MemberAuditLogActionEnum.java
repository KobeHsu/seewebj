package org.iii.see.enumeration;

public enum MemberAuditLogActionEnum {

	LOGIN("LOGIN", "登入"),
	LOGOUT("LOGOUT", "登出"),
	CHGPWD("CHGPWD", "修改密碼"),
	CHGINFO("CHGINFO", "修改個人資料"),
	SGNAGMT("SGNAGMT", "簽署個資同意書"),
	;

	private String code;
	private String desc;
	
	MemberAuditLogActionEnum(String code, String desc) {
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
