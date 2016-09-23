package org.iii.see.enumeration;

public enum OnlineIssueStatusEnum {

	OPEN("OPEN", "未處理"),
	PROCESS("PROCESS", "處理中"),
	PENDING("PENDING", "擱置"),
	CLOSED("CLOSED", "結案"),
	;

	private String code;
	private String desc;
	
	OnlineIssueStatusEnum(String code, String desc) {
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
