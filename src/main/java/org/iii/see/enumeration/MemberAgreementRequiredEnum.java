package org.iii.see.enumeration;

public enum MemberAgreementRequiredEnum {

	Y("Y", "是"),
	N("N", "否")
	;

	private String code;
	private String desc;
	
	MemberAgreementRequiredEnum(String code, String desc) {
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
