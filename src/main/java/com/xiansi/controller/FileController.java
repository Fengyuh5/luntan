package com.xiansi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiansi.dto.FileDTO;

@Controller
public class FileController {
	@RequestMapping("/file/upload")
	@ResponseBody
	public FileDTO upload() {
		FileDTO fileDTO = new FileDTO();
		fileDTO.setSuccess(1);
		fileDTO.setUrl("/img/weixin.png");//没有买云服务器，直接定义的本地路径
		return fileDTO;
	}
}
