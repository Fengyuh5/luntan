package com.xiansi.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.mapper.QuestionMapper;
import com.xiansi.mapper.UserMapper;
import com.xiansi.model.Question;
import com.xiansi.model.User;

@Controller
public class PublishController {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper;
	@GetMapping("/publish")
	public String publish() {
		return "publish";
	}
	@PostMapping("/publish")
	public String doPublish(
			@RequestParam("title") String title,
			@RequestParam("description") String description,
			@RequestParam("tag") String tag,
			HttpServletRequest request,
			Model model) {
		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("tag", tag);
		if (title.equals(null) || title.equals("")) {
			model.addAttribute("error","标题不能为空");
			return "publish";
		}
		if (description.equals(null) || description.equals("")) {
			model.addAttribute("error","内容不能为空");
			return "publish";
		}
		if (tag.equals(null) || tag.equals("")) {
			model.addAttribute("error","标签不能为空");
			return "publish";
		}
		
		User user = null;
		Cookie[] cookies = request.getCookies(); //通过request获取Cookies键值对
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) { //获取键
					String token = cookie.getValue();//获取值
					user = userMapper.findByToken(token);
						if (user != null) {
							request.getSession().setAttribute("user", user);
						}
					break;
				}
			}
		}
		user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			model.addAttribute("error","用户未登录");
			return "publish";
		}
		Question question = new Question();
		question.setTitle(title);
		question.setDescription(description);
		question.setTag(tag);
		question.setCreator(user.getId());
		question.setGmt_create(System.currentTimeMillis());
		question.setGmt_modified(question.getGmt_create());
		questionMapper.create(question);
		return "redirect:/";
	}
}
