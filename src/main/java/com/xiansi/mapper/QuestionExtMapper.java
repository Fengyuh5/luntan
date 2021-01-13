package com.xiansi.mapper;

import java.util.List;

import com.xiansi.model.Question;

public interface QuestionExtMapper {

	int incView(Question record);
	int incCommentCount(Question record);
	List<Question> selectRelated(Question question);
}
