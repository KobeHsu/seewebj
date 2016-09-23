package org.iii.see.enumeration;

public enum BuiltInCaseExtraDefinitionTypeEnum {

	STRING("STRING", "文字"),
	NUMERIC("NUMERIC", "數值")
	;

	private String code;
	private String desc;
	
	BuiltInCaseExtraDefinitionTypeEnum(String code, String desc) {
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
