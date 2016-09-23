package org.iii.see.enumeration;

public enum ProjectStatusEnum {
	
	ACTIVE("ACTIVE", "啟用"),
	INACTIVE("INACTIVE", "停用"),
	CLOSED("CLOSED", "結束"),
	;

	private String code;
	private String desc;
	
	ProjectStatusEnum(String code, String desc) {
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
