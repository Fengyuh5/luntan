package com.xiansi.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiansi.dto.PaginationDTO;
import com.xiansi.dto.QuestionDTO;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.mapper.UserMapper;
import com.xiansi.model.Question;
import com.xiansi.model.User;
import com.xiansi.service.QuestionService;


@Controller
public class IndexController {
@Autowired
private UserMapper userMapper;
@Autowired
private QuestionService questionService;
@GetMapping("/")
public String index(HttpServletRequest request,
					Model model,
					@RequestParam(name = "page",defaultValue = "1") Integer page,
					@RequestParam(name = "size",defaultValue = "8") Integer size) {
	Cookie[] cookies = request.getCookies(); //通过request获取Cookies键值对
	if (cookies != null) {
		for (Cookie cookie : cookies) {
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
	PaginationDTO pagination = questionService.list(page, size);
	//System.out.println(questionList);//能拿到数据库的数据，但是无法从前端显示，具体原因未知，index页面从56行开始获取值，但无法获取
	//bug已经修复
	model.addAttribute("pagination", pagination);
	return "index";
	}
}

