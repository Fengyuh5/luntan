package com.xiansi.dto;


import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;

import lombok.Data;

@Data
public class ResultDTO<T> {
	private Integer code;
	private String message;
	private T data;
	public static ResultDTO errorOf(Integer code, String message) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(code);
		resultDTO.setMessage(message);
		return resultDTO;
	}
	
	public static ResultDTO errorOf(CustomizeErrorCode errorCode) {
		// TODO Auto-generated method stub
		return errorOf(errorCode.getCode(),errorCode.getMessage());
	}
	public static ResultDTO errorOf(CustomizeException e) {
		// TODO Auto-generated method stub
		return errorOf(e.getCode(), e.getMessage());
	}
	public static ResultDTO okOf() {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("请求成功");
		return resultDTO;
	}
	public static <T> ResultDTO okOf(T t) {
		ResultDTO resultDTO = new ResultDTO();
		resultDTO.setCode(200);
		resultDTO.setMessage("请求成功");
		resultDTO.setData(t);
		return resultDTO;
	}
}
