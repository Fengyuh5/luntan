package com.xiansi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.dto.PaginationDTO;
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
	public PaginationDTO list(Integer page, Integer size) {
		PaginationDTO paginationDTO = new PaginationDTO();
		Integer totalCount = questionMapper.count();
		paginationDTO.setPagination(totalCount,page,size);
		
		if (page < 1) {
			page = 1;
		}
		if (page > paginationDTO.getTotalPage()) {
			page = paginationDTO.getTotalPage();
		}
		// size*(page-1)
		Integer offset = size * (page -1);
		List<Question> questions = questionMapper.list(offset, size);
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		
		for (Question question : questions) {
			User user =	userMapper.findById(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
			//System.out.println(questionDTOList);
		}
		paginationDTO.setQuestions(questionDTOList);
		
		return paginationDTO;
	}

}
