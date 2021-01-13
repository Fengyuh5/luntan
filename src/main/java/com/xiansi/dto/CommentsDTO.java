package com.xiansi.dto;

import com.xiansi.model.User;

import lombok.Data;

@Data
//接收Comment对象的DTO
public class CommentsDTO {
	 private Integer id;

	 private Integer parent_id;

	 private Integer type;

	 private Integer commentator;

	 private Long gmt_create;

	 private Long gmt_modified;

	 private Integer like_count;

	 private String content;
	 
	 private User user;
	 
	 private Integer comment_count;

}
