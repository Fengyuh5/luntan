package com.xiansi.schedule;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xiansi.cache.HotTagCache;
import com.xiansi.mapper.QuestionMapper;
import com.xiansi.model.Question;
import com.xiansi.model.QuestionExample;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class HotTagTasks {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private HotTagCache hotTagCache;
	@Scheduled(fixedRate = 20000)
	
	public void hotTagSchedule() {
		int offset = 0;
		int limit = 20;
		log.info("hotTagSchedule start{}", new Date());
		List<Question> list = new ArrayList<>();
		Map<String, Integer> priorities = new HashMap<String, Integer>();
		
		while (offset == 0 || list.size() == limit) {
			list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset,limit));
			for (Question question : list) {
				String[] tags = StringUtils.split(question.getTag(),"，");
				for (String tag : tags) {
					Integer priority = priorities.get(tag);
					if (priority != null) {
						priorities.put(tag, priority + 5 + (question.getComment_count() *3) 
								+ (question.getView_count() /5) );
					} else {
						priorities.put(tag, 5 + (question.getComment_count() *3) 
								+ (question.getView_count() /5));
					}
				}
				
				
			}
			offset += limit;
		}
		
//		priorities.forEach((k,v)->{
//			System.out.print(k);
//			System.out.print(":");
//			System.out.print(v);
//			System.out.println();
//		});
		hotTagCache.updateTags(priorities); 
		log.info("hotTagSchedule stop{}", new Date());
	}
}
