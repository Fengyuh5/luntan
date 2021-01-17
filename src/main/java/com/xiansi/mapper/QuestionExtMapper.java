package com.xiansi.mapper;

import java.util.List;

import com.xiansi.dto.QuestionQueryDTO;
import com.xiansi.model.Question;

public interface QuestionExtMapper {

	int incView(Question record);
	int incCommentCount(Question record);
	List<Question> selectRelated(Question question);
	int countBySearch(QuestionQueryDTO questionQueryDTO);
	List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
