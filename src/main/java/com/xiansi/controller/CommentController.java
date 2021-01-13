package com.xiansi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiansi.dto.CommentDTO;
import com.xiansi.dto.CommentsDTO;
import com.xiansi.dto.ResultDTO;
import com.xiansi.enums.CommentTypeEnum;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.model.Comment;
import com.xiansi.model.User;
import com.xiansi.service.CommentService;

//思路，在前端请求的时候拿到一个json，然后服务端拿到这个json之后，反序列化成自己的对象，然后操作。返回给前端时，也是object对象
//让spring去把object转换为json
//使用Postman工具处理
@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;

	@ResponseBody
	@RequestMapping(value = "/comment", method = RequestMethod.POST)
	public Object post(@RequestBody CommentDTO commentDTO, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (user == null) {
			return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
		}

		if (commentDTO == null || StringUtils.isBlank(commentDTO.getContent())) {
			return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
		}
		Comment comment = new Comment();
		comment.setParent_id(commentDTO.getParent_id());
		comment.setContent(commentDTO.getContent());
		comment.setType(commentDTO.getType());
		comment.setGmt_create(System.currentTimeMillis());
		comment.setGmt_modified(System.currentTimeMillis());
		comment.setCommentator(user.getId());
		// comment.setCommentator(1);
		commentService.insert(comment);
		return ResultDTO.okOf();
	}

	@ResponseBody
	@RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
	public ResultDTO<List<CommentsDTO>> comments(@PathVariable(name = "id") Integer id) {
		List<CommentsDTO> commentDTOs = commentService.listByTargetId(id, CommentTypeEnum.COMMENT);
		return ResultDTO.okOf(commentDTOs);
	}
}
