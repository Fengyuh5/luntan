package com.xiansi.model;

import lombok.Data;

@Data
public class Question {
	private Integer id;
	private String title;
	private Long gmt_create;
	private Long gmt_modified;
	private Integer creator;
	private Integer comment_count = 0;
	private Integer view_count = 0;
	private Integer like_count = 0;
	private String tag;
	private String account_id;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}
	public Long getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Long gmt_create) {
		this.gmt_create = gmt_create;
	}
	public Long getGmt_modified() {
		return gmt_modified;
	}
	public void setGmt_modified(Long gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	public Integer getCreator() {
		return creator;
	}
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	public Integer getComment_count() {
		return comment_count;
	}
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	public Integer getLike_count() {
		return like_count;
	}
	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag == null ? null : tag.trim();
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id == null ? null : account_id.trim();
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

}
