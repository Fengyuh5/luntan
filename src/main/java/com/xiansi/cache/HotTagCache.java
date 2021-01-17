package com.xiansi.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.springframework.stereotype.Component;

import com.xiansi.dto.HotTagDTO;

import lombok.Data;
@Component
@Data
public class HotTagCache {
	private List<String> hots = new ArrayList<String>();
	public void updateTags(Map<String, Integer> tags) {
		int max = 9;
		PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<HotTagDTO>(max); //创建优先级队列
		
		//使用小顶堆实现
		tags.forEach((name, priority) ->{
			HotTagDTO hotTagDTO = new HotTagDTO();
			hotTagDTO.setName(name);
			hotTagDTO.setPriority(priority);
			if (priorityQueue.size() < max) { //小于max直接往里面放
				priorityQueue.add(hotTagDTO);
			} else { //大于等于max则要拿到最小的元素去比较，比他大在往里放
				HotTagDTO minHot = priorityQueue.peek();
				if (hotTagDTO.compareTo(minHot) > 0) {
					priorityQueue.poll();
					priorityQueue.add(hotTagDTO);
				}
			}
		});
		List<String> sortedTags = new ArrayList<String>();
		HotTagDTO poll = priorityQueue.poll();
		hots.add(poll.getName());
		while (poll != null) {
			sortedTags.add(0, poll.getName());
			poll = priorityQueue.poll();
			
		}
		hots = sortedTags;
		//System.out.println(hots);
	}
}
