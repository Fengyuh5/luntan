package com.xiansi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.xiansi.model.Question;

@Mapper
public interface QuestionMapper {
	@Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
	void create(Question question);
	
	@Select("select * from question")
	List<Question> list();
}
