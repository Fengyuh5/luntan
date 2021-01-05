package com.xiansi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.dto.QuestionDTO;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.mapper.UserMapper;
import com.xiansi.model.Question;
import com.xiansi.model.User;
//组装
@Service
public class QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper;
	public List<QuestionDTO> list() {
		List<Question> questions = questionMapper.list();
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		for (Question question : questions) {
		User user =	userMapper.findById(question.getCreator());
		QuestionDTO questionDTO = new QuestionDTO();
		BeanUtils.copyProperties(question, questionDTO);
		questionDTO.setUser(user);
		questionDTOList.add(questionDTO);
		//System.out.println(questionDTOList);
		}
		return questionDTOList;
	}

}
