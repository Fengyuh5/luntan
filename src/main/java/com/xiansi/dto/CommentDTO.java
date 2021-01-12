package com.xiansi.dto;

import lombok.Data;

//把前端的数据反序列化,这个创建是Create的DTO
@Data
public class CommentDTO {
	private Integer parent_id;
	private String content;
	private Integer type;
}
