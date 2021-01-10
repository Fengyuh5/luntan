package com.xiansi.mapper;

import org.apache.ibatis.annotations.Mapper;



import com.xiansi.model.Question;


@Mapper
public interface QuestionExtMapper {
	
	int incView(Question record);

	
}