package com.xiansi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.dto.NotificationDTO;
import com.xiansi.dto.PaginationDTO;
import com.xiansi.enums.NotificationTypeEnum;
import com.xiansi.model.User;
import com.xiansi.service.NotificationService;


@Controller
public class NotificationControler {
	@Autowired
	private NotificationService notificationService;
	@GetMapping("/notification/{id}")
	public String profile(@PathVariable(name = "id") Integer id,
						 HttpServletRequest request) {
	
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return "redirect:/";
		}
		NotificationDTO notificationDTO = notificationService.read(id,user);
		if (NotificationTypeEnum.REPLY_COMMENT.getType() == notificationDTO.getType()
				|| NotificationTypeEnum.REPLY_QUESTION.getType() == notificationDTO.getType()) {
			return "redirect:/question/" + notificationDTO.getOuter_id();
		} else {
			return "redirect:/";
		}
		
	}
}
