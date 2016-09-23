package org.iii.see.enumeration;

public enum ErrorMessageEnum {

	AUTHENTICATION_ACCOUNT_NOT_EXISTS ("AA-0001", "帳號不存在"),
	AUTHENTICATION_ACCOUNT_DISABLED   ("AA-0002", "帳號已停用"),
	AUTHENTICATION_ACCOUNT_INACTIVE   ("AA-0002", "帳號尚未啟用"),
	AUTHENTICATION_PASSWORD_INCORRECT ("AA-0003", "密碼不正確"),
	AUTHENTICATION_NOT_LOGON          ("AA-0004", "尚未登入系統或已逾時"),

	COMMON_UNKNOWN_FUNCTION           ("CM-0001", "無法辨識功能請求"),
	COMMON_INSERT_FAILED              ("CM-0002", "新增資料時發生錯誤"),
	COMMON_UPDATE_FAILED              ("CM-0003", "更新資料時發生錯誤"),
	COMMON_DELETE_FAILED              ("CM-0004", "刪除資料時發生錯誤"),
	COMMON_NO_DATA_FOUND              ("CM-0005", "資料不存在"),
	COMMON_DATA_INCORRECT             ("CM-0006", "缺必填資料或資料格式不正確"),
	COMMON_DUPLICATED_DATA            ("CM-0007", "名稱相同的資料已經存在"),
	;

	private String code;
	private String desc;
	
	ErrorMessageEnum(String code, String desc) {
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
