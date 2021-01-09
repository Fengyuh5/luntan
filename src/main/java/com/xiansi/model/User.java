package com.xiansi.model;

import lombok.Data;

@Data
public class User {
	private Integer id;
	private String account_id;
	private String name;
	private String token;
	private Long gmt_create;
	private Long gmt_modified;
	private String bio;
	private String avatar_url;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccount_id() {
		return account_id;
	}
	public void setAccount_id(String account_id) {
		this.account_id = account_id == null ? null : account_id.trim();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token == null ? null : token.trim();
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
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio == null ? null : bio.trim();
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url == null ? null : avatar_url.trim();
	}

	
}
