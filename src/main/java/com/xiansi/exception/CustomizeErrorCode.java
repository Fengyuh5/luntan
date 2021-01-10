package com.xiansi.exception;
//定义枚举 重写getMessage(),返回异常
public enum CustomizeErrorCode implements ICustomizeErrorCode{
	QUESTION_NOT_FOUND("您找到的问题不见了，要不换个问题试试？");
	
	@Override
	public String getMessage() {
		return message;
	}
	private String message;
	CustomizeErrorCode(String message){
		this.message = message;
	}
	
}
