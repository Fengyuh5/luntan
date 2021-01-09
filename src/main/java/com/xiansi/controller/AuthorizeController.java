package com.xiansi.controller;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.xiansi.dto.AccessTokenDTO;
import com.xiansi.dto.GithubUser;
import com.xiansi.model.User;
import com.xiansi.provider.GithubProvider;
import com.xiansi.service.UserService;

@Controller
public class AuthorizeController {
	@Autowired
	private GithubProvider githubProvider;
	@Autowired
	private UserService userService;
	@GetMapping("/callback")
	public String callback(@RequestParam(name = "code") String code,
						   @RequestParam(name = "state") String state,
						   HttpServletResponse response) {
		AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
		//添加github链接必须的值
		
		accessTokenDTO.setClient_id("Iv1.34f5e26d12ae33d5");
		accessTokenDTO.setClient_secret("55f754d7b50b54aca2477c674ade62f748cc8779");
		accessTokenDTO.setCode(code);
		accessTokenDTO.setRedirect_uri("http://localhost:8888/callback");
		accessTokenDTO.setState(state);
		String accessToken = githubProvider.getAccessToken(accessTokenDTO);
		GithubUser githubUser = githubProvider.getUser(accessToken);
		//System.out.println(user.getName());//返回的null值已解决。
		if (githubUser != null && githubUser.getId() != null) {
			User user = new User();
			String token = UUID.randomUUID().toString();
			//插入数据库
			user.setToken(token);
			user.setName(githubUser.getName());
			user.setAccount_id(String.valueOf(githubUser.getId()));
			user.setAvatar_url(githubUser.getAvatar_url());
			userService.createOrUpdate(user);
			//userMapper.insert(user);
			//手动模拟cookie去验证访问数据库查询
			response.addCookie(new Cookie("token", token));
			//request.getSession().setAttribute("user", user);
			return "redirect:/";
		} else {
			return "redirect:/";
		}

	}
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,
						HttpServletResponse response) {
		request.getSession().removeAttribute("user");
		Cookie cookie = new Cookie("token",null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return "redirect:/";
	}
}
