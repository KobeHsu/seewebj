package org.iii.see.excecption;

import org.iii.see.enumeration.ErrorMessageEnum;

public class DuplicatedDataException extends Exception {

	private static final long serialVersionUID = -4985607149965432486L;

	public DuplicatedDataException() {
	}
	
	public String toString() {
		return ErrorMessageEnum.COMMON_DUPLICATED_DATA.getCode() + "-" + ErrorMessageEnum.COMMON_DUPLICATED_DATA.getDesc();
	}

	public String getMessage() {
		return ErrorMessageEnum.COMMON_DUPLICATED_DATA.getDesc();
	}
	
}
