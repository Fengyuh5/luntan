package com.xiansi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.xiansi.model.Question;

@Mapper
public interface QuestionMapper {
	@Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
	void create(Question question);
}
