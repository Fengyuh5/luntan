package com.xiansi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.xiansi.dto.CommentsDTO;
import com.xiansi.dto.QuestionDTO;
import com.xiansi.enums.CommentTypeEnum;
import com.xiansi.service.CommentService;
import com.xiansi.service.QuestionService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private CommentService commentService;
	@GetMapping("/question/{id}")
	public String question(@PathVariable(name = "id") Integer id,Model model) {
		QuestionDTO questionDTO = questionService.getById(id);
		List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
		List<CommentsDTO> comments = commentService.listByTargetId(id,CommentTypeEnum.QUESTION);
		//累加阅读数
		questionService.incView(id);
		model.addAttribute("question", questionDTO);
		model.addAttribute("comments", comments);
		model.addAttribute("relatedQuestions", relatedQuestions);
		return "question";
	}
}
