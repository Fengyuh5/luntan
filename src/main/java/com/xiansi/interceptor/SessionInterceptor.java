package com.xiansi.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xiansi.mapper.UserMapper;
import com.xiansi.model.User;
@Service
//拦截器的内容
public class SessionInterceptor implements HandlerInterceptor {
	@Autowired
	private UserMapper userMapper;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies(); //通过request获取Cookies键值对
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("token")) { //获取键
					String token = cookie.getValue();//获取值
					User user = userMapper.findByToken(token);
						if (user != null) {
							request.getSession().setAttribute("user", user);
						}
					break;
				}
			}
		}
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
}
