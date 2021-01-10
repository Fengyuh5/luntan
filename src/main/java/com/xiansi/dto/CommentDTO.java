package com.xiansi.dto;

import lombok.Data;

//把前端的数据反序列化
@Data
public class CommentDTO {
	private Long parent_id;
	private String content;
	private Integer type;
}
