package com.xiansi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.xiansi.dto.CommentsDTO;
import com.xiansi.enums.CommentTypeEnum;
import com.xiansi.enums.NotificationStatusEnum;
import com.xiansi.enums.NotificationTypeEnum;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;
import com.xiansi.mapper.CommentExtMapper;
import com.xiansi.mapper.CommentMapper;
import com.xiansi.mapper.NotificationMapper;
import com.xiansi.mapper.QuestionExtMapper;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.mapper.UserMapper;
import com.xiansi.model.Comment;
import com.xiansi.model.CommentExample;
import com.xiansi.model.Notification;
import com.xiansi.model.Question;
import com.xiansi.model.User;
import com.xiansi.model.UserExample;

@Service
public class CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private QuestionExtMapper questionExtMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CommentExtMapper commentExtMapper;
	@Autowired
	private NotificationMapper notificationMapper;
	@Transactional
	public void insert(Comment comment, User commentator) {
		if (comment.getParent_id() == null || comment.getParent_id() == 0) {
			throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
		}

		if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
			throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
		}

		if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
			// 回复评论
			Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParent_id());
			if (dbComment == null) {
				throw new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
			}
			Question question = questionMapper.selectByPrimaryKey(dbComment.getParent_id());
			if (question == null) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			commentMapper.insert(comment);
			//增加评论数
			Comment parentComment = new Comment();
			parentComment.setId(comment.getParent_id());
			parentComment.setComment_count(1);
			commentExtMapper.incCommentCount(parentComment);
			//创建通知
			createNotify(comment,dbComment.getCommentator(),commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_COMMENT,question.getId());
		} else {
			// 回复问题
			Question question = questionMapper.selectByPrimaryKey(comment.getParent_id());
			if (question == null) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
			commentMapper.insert(comment);
			question.setComment_count(1);
			questionExtMapper.incCommentCount(question);
			createNotify(comment, question.getCreator(),commentator.getName(),question.getTitle(),NotificationTypeEnum.REPLY_QUESTION, question.getId());
		}
	}
	private void createNotify(Comment comment, Integer receiver, String notifierName, String outerTitle, NotificationTypeEnum notificationType, Integer outer_id) {
		Notification notification = new Notification();
		notification.setGmt_create(System.currentTimeMillis());
		notification.setType(notificationType.getType());
		//Integer parent_id = comment.getParent_id();
		notification.setOuter_id(outer_id);
		notification.setNotifier(comment.getCommentator());
		notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
		notification.setReceiver(receiver);
		notification.setNotifier_name(notifierName);
		notification.setOuter_title(outerTitle);
		notificationMapper.insert(notification);
	}
	public List<CommentsDTO> listByTargetId(Integer id, CommentTypeEnum type) {
		CommentExample commentExample = new CommentExample();
		commentExample.createCriteria().andParent_idEqualTo(id).andTypeEqualTo(type.getType());
		commentExample.setOrderByClause("gmt_create desc"); // 评论内容按时间倒序
		List<Comment> comments = commentMapper.selectByExample(commentExample);
		if (comments.size() == 0) {
			return new ArrayList<>();
		}
		// 获取去重的评论人
		Set<Integer> commentators = comments.stream().map(comment -> comment.getCommentator())
				.collect(Collectors.toSet());
		List<Integer> userIds = new ArrayList<Integer>();
		userIds.addAll(commentators);
		// 获取评论人并转换为Map
		UserExample userExample = new UserExample();
		userExample.createCriteria().andIdIn(userIds);
		List<User> users = userMapper.selectByExample(userExample);
		Map<Integer, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
		// 转换comment为commentsDTO
		List<CommentsDTO> commentsDTOs = comments.stream().map(comment -> {
			CommentsDTO commentsDTO = new CommentsDTO();
			BeanUtils.copyProperties(comment, commentsDTO);
			commentsDTO.setUser(userMap.get(comment.getCommentator()));
			return commentsDTO;
		}).collect(Collectors.toList());
		return commentsDTOs;
	}

}
