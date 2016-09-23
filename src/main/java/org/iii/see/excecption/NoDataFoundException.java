package org.iii.see.excecption;

import org.iii.see.enumeration.ErrorMessageEnum;


public class NoDataFoundException extends Exception {

	private static final long serialVersionUID = 2559973078361327422L;

	public NoDataFoundException() {
	}
	
	public String toString() {
		return ErrorMessageEnum.COMMON_NO_DATA_FOUND.getCode() + "-" + ErrorMessageEnum.COMMON_NO_DATA_FOUND.getDesc();
	}

	public String getMessage() {
		return ErrorMessageEnum.COMMON_NO_DATA_FOUND.getDesc();
	}
	
}
