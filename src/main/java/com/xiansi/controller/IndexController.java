package com.xiansi.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.dto.PaginationDTO;

import com.xiansi.service.QuestionService;


@Controller
public class IndexController {

@Autowired
private QuestionService questionService;
@GetMapping("/")
public String index(HttpServletRequest request,
					Model model,
					@RequestParam(name = "page",defaultValue = "1") Integer page,
					@RequestParam(name = "size",defaultValue = "6") Integer size) {
	
	PaginationDTO pagination = questionService.list(page, size);
	//System.out.println(questionList);//能拿到数据库的数据，但是无法从前端显示，具体原因未知，index页面从56行开始获取值，但无法获取
	//bug已经修复
	model.addAttribute("pagination", pagination);
	return "index";
	}
}

