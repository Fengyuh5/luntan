package com.xiansi.enums;

public enum CommentTypeEnum {
	QUESTION(1),//问题
	COMMENT(2);//评论
	private Integer type;
	
	public Integer getType() {
		return type;
	}
	private CommentTypeEnum(Integer type) {
		this.type = type;
	}
	public static boolean isExist(Integer type) {
		for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
			if(commentTypeEnum.getType() == type) {
				return true;
			}
		}
		return false;
	}
	
}
