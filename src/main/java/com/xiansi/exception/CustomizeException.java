package com.xiansi.exception;

public class CustomizeException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer code;
	public CustomizeException(ICustomizeErrorCode errorCode) {
		this.code = errorCode.getCode();
		this.message = errorCode.getMessage();
	}

	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
	

}
