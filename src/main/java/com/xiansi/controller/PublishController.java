package com.xiansi.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.cache.TagCache;
import com.xiansi.dto.QuestionDTO;

import com.xiansi.model.Question;
import com.xiansi.model.User;
import com.xiansi.service.QuestionService;

@Controller
public class PublishController {

	@Autowired
	private QuestionService questionService;
	@GetMapping("/publish/{id}")
	public String edit(@PathVariable(name = "id") Integer id,
					  Model model) {
		QuestionDTO question = questionService.getById(id);
		model.addAttribute("title", question.getTitle());
		model.addAttribute("description", question.getDescription());
		model.addAttribute("tag", question.getTag());
		model.addAttribute("id", question.getId());
		model.addAttribute("tags", TagCache.get());
		return "publish";
	}
	
	@GetMapping("/publish")
	public String publish(Model model) {
		model.addAttribute("tags", TagCache.get());
		return "publish";
	}
	@PostMapping("/publish")
	public String doPublish(
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "description", required = false) String description,
			@RequestParam(value ="tag", required = false) String tag,
			@RequestParam(value ="id", required = false) Integer id,
			HttpServletRequest request,
			Model model) {
		model.addAttribute("title", title);
		model.addAttribute("description", description);
		model.addAttribute("tag", tag);
		model.addAttribute("tags", TagCache.get());
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
		
		String invalid = TagCache.filterInvalid(tag);
		if (StringUtils.isNotBlank(invalid)) {
			model.addAttribute("error", "输入非法标签" + invalid);
			return "publish";
		}
		
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			model.addAttribute("error","用户未登录");
			return "publish";
		}
		Question question = new Question();
		question.setTitle(title);
		question.setDescription(description);
		question.setTag(tag);
		question.setCreator(user.getId());
		//question.setId(user.getId());
		question.setAccount_id(user.getAccount_id());
		//question.setComment_count(0);
		//question.setLike_count(0);
		//question.setView_count(0);
		question.setId(id);
		questionService.createOrUpdate(question);
		//questionMapper.create(question);
		return "redirect:/";
	}
}