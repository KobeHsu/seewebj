package org.iii.see.enumeration;

public enum AccountStatusEnum {

	ACTIVE("ACTIVE", "啟用"),
	INACTIVE("INACTIVE", "未啟用"),
	DISABLED("DISABLED", "停用")
	;

	private String code;
	private String desc;
	
	AccountStatusEnum(String code, String desc) {
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
