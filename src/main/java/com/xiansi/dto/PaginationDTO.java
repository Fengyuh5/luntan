package com.xiansi.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
public class PaginationDTO {
	private List<QuestionDTO> questions;
	private boolean showPrevious; //上一页
	private boolean showFirstPage;//第一页
	private boolean showNext;     //下一页
	private boolean showEndPage;//最后一页
	private Integer page;//当前页
	private List<Integer> pages = new ArrayList<Integer>();
	private Integer totalPage;//总页数
	public void setPagination(Integer totalPage, Integer page) {
		
	/*	if (totalCount % size == 0) {
			totalPage = totalCount / size;
		} else {
			totalPage = totalCount / size + 1;
		}
		if (page < 1) {
			page = 1;
		}
		if (page > totalPage) {
			page = totalPage;
		}*/
		this.totalPage = totalPage;
		this.page = page;
		pages.add(page);
		for (int i = 1; i <= 2; i++) {
			if (page - i > 0) {
				pages.add(0, page - i);
			}
			if (page + i <= totalPage) {
				pages.add(page + i);
			}
		}
		
		//是否展示上一页
		if (page == 1) {
			showPrevious = false;
		} else {
			showPrevious = true;
		}
		//是否展示下一页
		if (page == totalPage) {
			showNext = false;
		} else {
			showNext = true;
		}
		
		//是否展示第一页 contains(1)不展示
		if (pages.contains(1)) {
			showFirstPage = false;
		} else {
			showFirstPage = true;
		}
		
		//是否展示最后一页
		if (pages.contains(totalPage)) {
			showEndPage = false;
		} else {
			showEndPage = true;
		}
	}
}