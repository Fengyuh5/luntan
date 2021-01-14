package com.xiansi.exception;
//定义枚举 重写getMessage(),返回异常
public enum CustomizeErrorCode implements ICustomizeErrorCode{
	QUESTION_NOT_FOUND(2001,"您找到的问题不见了，要不换个问题试试？"),
	TARGET_PARAM_NOT_FOUND(2002, "未选中任何问题或评论进行回复"),
    NO_LOGIN(2003, "当前操作需要登录，请登陆后重试"),
    SYS_ERROR(2004, "服务冒烟了，要不然你稍后再试试！！！"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在"),
    COMMENT_NOT_FOUND(2006, "回复的评论不存在了，要不要换个试试？"),
    CONTENT_IS_EMPTY(2007, "输入内容不能为空"), 
    READ_NOTIFICATION_FAIL(2008,"请勿非法操作！"), 
    NOTIFICATION_NOT_FOUND(2009,"error，消息不翼而飞了..."),
    ;
	@Override
	public String getMessage() {
		return message;
	}
	@Override
	public Integer getCode() {
		// TODO Auto-generated method stub
		return code;
	}
	private Integer code;
	private String message;

	CustomizeErrorCode(Integer code,String message ) {
		this.message = message;
		this.code = code;
	}	
}
