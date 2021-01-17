package com.xiansi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xiansi.dto.PaginationDTO;
import com.xiansi.dto.QuestionDTO;
import com.xiansi.dto.QuestionQueryDTO;
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
	public PaginationDTO<QuestionDTO> list(String search, String tag, Integer page, Integer size) {
		if (StringUtils.isNotBlank(search)) {
			String[] tags = StringUtils.split(search," ");
			search = Arrays.stream(tags).collect(Collectors.joining("|"));
		}
		
		PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<QuestionDTO>();
		Integer totalPage;//总页数
		QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
		questionQueryDTO.setSearch(search);
		questionQueryDTO.setTag(tag);
		Integer totalCount = questionExtMapper.countBySearch(questionQueryDTO);
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
		questionQueryDTO.setSize(size);
		questionQueryDTO.setPage(offset);
		List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
		List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
		
		for (Question question : questions) {
			User user =	userMapper.selectByPrimaryKey(question.getCreator());
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(question, questionDTO);
			questionDTO.setUser(user);
			questionDTOList.add(questionDTO);
		}
		paginationDTO.setData(questionDTOList);
		
		return paginationDTO;
	}
	public PaginationDTO<QuestionDTO> listByUserId(Integer userId, Integer page, Integer size) {
		PaginationDTO<QuestionDTO> paginationDTO = new PaginationDTO<QuestionDTO>();
		Integer totalPage;
		QuestionExample questionExample = new QuestionExample();
		questionExample.createCriteria().andCreatorEqualTo(userId);
		Integer totalCount =(int) questionMapper.countByExample(questionExample);
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
		paginationDTO.setData(questionDTOList);
		
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
	public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
		if (StringUtils.isBlank(queryDTO.getTag())) {
			return new ArrayList<>();
		}
		String[] tags = StringUtils.split(queryDTO.getTag(),"，");
		String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
		Question question = new Question();
		question.setId(queryDTO.getId());
		question.setTag(regexpTag);
		List<Question> questions = questionExtMapper.selectRelated(question);
		List<QuestionDTO> questionDTOs = questions.stream().map(q -> {
			QuestionDTO questionDTO = new QuestionDTO();
			BeanUtils.copyProperties(q, questionDTO);
			return questionDTO;
		}).collect(Collectors.toList());
		return questionDTOs;
	}


}