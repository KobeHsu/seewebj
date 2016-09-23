package org.iii.see.enumeration;

public enum AnnouncementSerialNoEnum {

	STICKY("1", "置頂"),
	MIDDLE("10", "中段"),
	TAIL("100", "後段")
	;

	private String code;
	private String desc;
	
	AnnouncementSerialNoEnum(String code, String desc) {
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
