package com.xiansi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.mapper.UserMapper;
import com.xiansi.model.User;

@Controller
public class IndexController {
@Autowired
private UserMapper userMapper;
@GetMapping("/")
public String index(HttpServletRequest request) {
	Cookie[] cookies = request.getCookies(); //通过request获取Cookies键值对
	for (Cookie cookie : cookies) {
		if (cookie != null) {
			if (cookie.getName().equals("token")) { //获取键
				String token = cookie.getValue();//获取值
				User user = userMapper.findByToken(token);
					if (user != null) {
						request.getSession().setAttribute("user", user);
					}
				break;
			}
		}
	}
		return "index";
	}
}
