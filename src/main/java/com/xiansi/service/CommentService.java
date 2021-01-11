package com.xiansi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiansi.enums.CommentTypeEnum;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;
import com.xiansi.mapper.CommentMapper;
import com.xiansi.mapper.QuestionExtMapper;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.model.Comment;
import com.xiansi.model.Question;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionExtMapper questionExtMapper;
	@Transactional
	public void insert(Comment comment) {
		if (comment.getParent_id() == null || comment.getParent_id() == 0) {
			throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}
		
		if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
			throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
		}
		
		if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
			//回复评论
			Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParent_id());
			if (dbComment == null) {
				throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
			commentMapper.insert(comment);
		} else {
			//回复问题
			Question question = questionMapper.selectByPrimaryKey(comment.getParent_id());
			if (question == null) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
				
			}
			commentMapper.insert(comment);
			question.setComment_count(1);
			questionExtMapper.incCommentCount(question);
		}
	}
	
}
