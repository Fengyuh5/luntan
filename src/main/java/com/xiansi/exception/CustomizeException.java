package com.xiansi.exception;

public class CustomizeException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	
	public CustomizeException(ICustomizeErrorCode errorCode) {
		this.message = errorCode.getMessage();
	}

	public CustomizeException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	

}
