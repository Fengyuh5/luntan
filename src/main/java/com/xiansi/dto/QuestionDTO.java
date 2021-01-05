package com.xiansi.dto;

import com.xiansi.model.User;

import lombok.Data;
@Data
public class QuestionDTO {
	private int id;
	private String title;
	private String description;
	private Long gmtCreate;
	private Long gmtmodified;
	private Integer creator;
	private Integer commentcount;
	private Integer viewcount;
	private Integer likecount;
	private String tag;
	private User user;
}
