package org.iii.see.enumeration;

public enum DaoActionEnum {

	CREATE("CREATE", "新增"),
	UPDATE("UPDATE", "修改"),
	DELETE("DELETE", "刪除"),
	AGREE("AGREE", "簽署個資同意書"),
	;

	private String code;
	private String desc;
	
	DaoActionEnum(String code, String desc) {
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
