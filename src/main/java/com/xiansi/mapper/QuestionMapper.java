package com.xiansi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xiansi.model.Question;

@Mapper
public interface QuestionMapper {
	//2021.1.7 question 添加查询account_id
	@Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag,account_id)"
			+ "values(#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag},#{account_id})")
	void create(Question question);
	
	//2021.1.6 通过mysql limit分页查询
	@Select("select * from question limit #{offset},#{size}")
	List<Question> list(@Param(value = "offset") Integer offset, @Param(value = "size")Integer size);
	
	//2021.1.6 返回question的行数,补充的知识点count(*)和count(1)在以前的区别是什么
	@Select("select count(1) from question")
	Integer count();
	
	//2021.1.7 通过QuestionDTO中的User对象，查询User对象的account_id值，并获取存入。作为判断是否是同一个用户
	//根据countByUserId接口返回的行数去计算分页数。
	@Select("select * from question where account_id = #{account_id} limit #{offset},#{size}")
	List<Question> listByUserId(@Param(value = "account_id") String account_id, @Param(value = "offset") Integer offset, 
								@Param(value = "size")Integer size);
	//获取当前用户发帖的数量值
	@Select("select count(1) from question where account_id = #{account_id}")
	Integer countByUserId(@Param(value = "account_id")String account_id);

	@Select("select * from question where id = #{id}")
	Question getById(@Param(value = "id")Integer id);
}