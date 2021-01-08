package com.xiansi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.mapper.UserMapper;
import com.xiansi.model.User;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;

	public void createOrUpdate(User user) {
		User dbUser = userMapper.findByAccountId(user.getAccount_id());
		if (dbUser == null) {
			user.setGmt_create(System.currentTimeMillis());
			user.setGmt_modified(user.getGmt_create());
			userMapper.insert(user);
		} else {
			dbUser.setGmt_modified(System.currentTimeMillis());
			dbUser.setAvatar_url(user.getAvatar_url());
			dbUser.setName(user.getName());
			dbUser.setToken(user.getToken());
			userMapper.update(dbUser);
		}
	}
}
