package com.xiansi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiansi.dto.CommentDTO;
import com.xiansi.mapper.CommentMapper;
import com.xiansi.model.Comment;

//实现回复功能，学习完json API后在开始做。
//思路，在前端请求的时候拿到一个json，然后服务端拿到这个json之后，反序列化成自己的对象，然后操作。返回给前端时，也是object对象
//让spring去把object转换为json
//使用Postman工具处理
@Controller
public class CommentController {
	@Autowired
	private CommentMapper commentMapper;
	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public Object post(@RequestBody CommentDTO commentDTO) {
		Comment comment = new Comment();
		comment.setParent_id(commentDTO.getParent_id());
		comment.setContent(commentDTO.getContent());
		comment.setType(commentDTO.getType());
		comment.setGmt_create(System.currentTimeMillis());
		comment.setGmt_modified(System.currentTimeMillis());
		comment.setCommentator(1);
		commentMapper.insert(comment);
		return null;
	}
}
