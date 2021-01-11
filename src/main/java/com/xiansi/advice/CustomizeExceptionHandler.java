package com.xiansi.advice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiansi.dto.ResultDTO;
import com.xiansi.exception.CustomizeErrorCode;
import com.xiansi.exception.CustomizeException;
//使用@ControlerAdvice和ExceptionHandler做通用处理异常
@ControllerAdvice
@ResponseBody
public class CustomizeExceptionHandler {
	@ExceptionHandler(Exception.class)
	Object handle(HttpServletRequest request, Throwable e, Model model) {
		String contentType = request.getContentType();
		if ("application/json".equals(contentType)) {
			if (e instanceof CustomizeException) {
				return ResultDTO.errorOf((CustomizeException) e);
			} else {
				return ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
			}
		} else {
			if (e instanceof CustomizeException) {
				model.addAttribute("message", e.getMessage());
			} else {
				model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
			}
			return new ModelAndView("error");
		}
	}
}
