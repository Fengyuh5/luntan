package com.xiansi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiansi.model.Question;

@Mapper
public interface QuestionMapper {
	@Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag)"
			+ "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
	void create(Question question);
	
	@Select("select * from question limit #{offset},#{size}")
	List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);
	
	@Select("select count(1) from question")
	Integer count();
}
