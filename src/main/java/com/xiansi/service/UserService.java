 package com.xiansi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.mapper.UserMapper;
import com.xiansi.model.User;
import com.xiansi.model.UserExample;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	//解决一个用户可以注册多次Bug。
	public void createOrUpdate(User user) {
		UserExample userExample = new UserExample();
		userExample.createCriteria().andAccount_idEqualTo(user.getAccount_id());
		List<User> users = userMapper.selectByExample(userExample);
		if (users.size() == 0) {
			user.setGmt_create(System.currentTimeMillis());
			user.setGmt_modified(user.getGmt_create());
			userMapper.insert(user);
		} else {
			User dbUser = users.get(0);
			User updateUser = new User();
			updateUser.setGmt_modified(System.currentTimeMillis());
			updateUser.setAvatar_url(user.getAvatar_url());
			updateUser.setName(user.getName());
			updateUser.setToken(user.getToken());
			UserExample example = new UserExample();
			example.createCriteria().andIdEqualTo(dbUser.getId());
			userMapper.updateByExampleSelective(updateUser, example);
		}
	}
}
