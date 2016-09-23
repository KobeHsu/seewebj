package org.iii.see.enumeration;

public enum FileFolderEnum {

	Case("case", "個案管理"),
	OnlineIssue("onlineIssue", "線上問題反應"),
	Persona("persona", "人物角色原型管理")
	;

	private String code;
	private String desc;
	
	FileFolderEnum(String code, String desc) {
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
