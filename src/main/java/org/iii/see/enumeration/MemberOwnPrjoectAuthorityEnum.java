package org.iii.see.enumeration;

public enum MemberOwnPrjoectAuthorityEnum {

	N("N", "否"),
	Y("Y", "是")
	;

	private String code;
	private String desc;
	
	MemberOwnPrjoectAuthorityEnum(String code, String desc) {
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
