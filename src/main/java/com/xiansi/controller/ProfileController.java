package com.xiansi.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.dto.PaginationDTO;
import com.xiansi.mapper.NotificationMapper;
import com.xiansi.model.User;
import com.xiansi.service.NotificationService;
import com.xiansi.service.QuestionService;
/**
 * 
 *
 * 个人发布问题界面 
 * @author 弦思
 * @2021-1-6
 */
@Controller
public class ProfileController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private NotificationService notificationService;
	@GetMapping("/profile/{action}")
	public String profile(@PathVariable(name = "action") String action,
						Model model,
						HttpServletRequest request,
						@RequestParam(name = "page",defaultValue = "1") Integer page,
						@RequestParam(name = "size",defaultValue = "6") Integer size) {
	
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		if ("questions".contains(action)) {
			model.addAttribute("section", "questions");
			model.addAttribute("sectionName", "我的提问");
			PaginationDTO<?> paginationDTO = questionService.listByUserId(user.getId(), page,size);
			model.addAttribute("pagination", paginationDTO);
		} else if("replies".contains(action)){
			PaginationDTO<?> paginationDTO = notificationService.list(user.getId(),page,size);
			
			model.addAttribute("section", "replies");
			model.addAttribute("pagination", paginationDTO);
			model.addAttribute("sectionName", "最新回复");
			
			
		}
		
		return "profile";
	}
}