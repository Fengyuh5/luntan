package com.xiansi.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.dto.PaginationDTO;
import com.xiansi.dto.QuestionDTO;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;
import com.xiansi.mapper.QuestionExtMapper;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.mapper.UserMapper;
import com.xiansi.model.Question;
import com.xiansi.model.QuestionExample;
import com.xiansi.model.User;
//组装
@Service
public class QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private QuestionExtMapper questionExtMapper;
	public PaginationDTO list(Integer page, Integer size) {
		PaginationDTO paginationDTO = new PaginationDTO();
		Integer totalPage;//总页数
		
		Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
		if (totalCount % size == 0) {
			totalPage = totalCount / size;
		} else {
			totalPage = totalCount / size + 1;
		}
		if (page < 1) {
			page = 1;
		}
		if (page > totalPage) {
			page = totalPage;
		}
		paginationDTO.setPagination(totalPage,page);
		// size*(page-1)
		Integer offset = size * (page -1);
		List<Question> questions = questionMapper.selectByExampleWithRowbounds
				(new QuestionExample(), new RowBounds(offset,size));
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		
		for (Question question : questions) {
			User user =	userMapper.selectByPrimaryKey(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);
		
		return paginationDTO;
	}
	public PaginationDTO listByUserId(Integer userId, Integer page, Integer size) {
		PaginationDTO paginationDTO = new PaginationDTO();
		Integer totalPage;
		QuestionExample questionExample = new QuestionExample();
		questionExample.createCriteria().andCreatorEqualTo(userId);
		Integer totalCount =(int) questionMapper.countByExample(questionExample);
		//Integer totalCount = questionMapper.countByUserId(accound_id);
		if (totalCount % size == 0) {
			totalPage = totalCount / size;
		} else {
			totalPage = totalCount / size + 1;
		}

		if (page < 1) {
			page = 1;
		}
		if (page > totalPage) {
			page = totalPage;
		}
		paginationDTO.setPagination(totalPage,page);
		// size*(page-1)
		Integer offset = size * (page -1);
		QuestionExample example = new QuestionExample();
		example.createCriteria().andCreatorEqualTo(userId);
		List<Question> questions = questionMapper.selectByExampleWithRowbounds
				(example, new RowBounds(offset,size));
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		for (Question question : questions) {
			User user =	userMapper.selectByPrimaryKey(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setQuestions(questionDTOList);
		
		return paginationDTO;
		
	}
	public QuestionDTO getById(Integer id) {
		Question question = questionMapper.selectByPrimaryKey(id);
		if (question == null) {
			throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
		}
		QuestionDTO questionDTO = new QuestionDTO();
		BeanUtils.copyProperties(question, questionDTO);
		User user =	userMapper.selectByPrimaryKey(question.getCreator());
		questionDTO.setUser(user);
		return questionDTO;

	}
	public void createOrUpdate(Question question) {
		if (question.getId() == null) {
			question.setGmt_create(System.currentTimeMillis());
			question.setGmt_modified(question.getGmt_create());
			question.setView_count(0);
			question.setComment_count(0);
			question.setLike_count(0);
			questionMapper.insert(question);
		} else {
			Question updateQuestion = new Question();
			updateQuestion.setGmt_modified(System.currentTimeMillis());
			updateQuestion.setTitle(question.getTitle());
			updateQuestion.setDescription(question.getDescription());
			updateQuestion.setTag(question.getTag());
			//updateQuestion.setComment_count(question.getComment_count());
			//updateQuestion.setView_count(question.getView_count());
			//updateQuestion.setLike_count(question.getLike_count());
			QuestionExample example = new QuestionExample();
			example.createCriteria().andIdEqualTo(question.getId());
			int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
			if (updated != 1) {
				throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
			}
		}
		
	}
	public void incView(Integer id) {
		Question question = new Question();
		question.setId(id);
		question.setView_count(1);
		questionExtMapper.incView(question);
		
	}


}