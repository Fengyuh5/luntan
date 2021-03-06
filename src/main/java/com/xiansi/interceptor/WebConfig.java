package com.xiansi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//拦截器
@Configuration

public class WebConfig implements WebMvcConfigurer{
	@Autowired
	private SessionInterceptor sessionInterceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//registry.addInterceptor(new LocaleInterceptor());
		registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
		//registry.addInterceptor(new SecurityInterceptor()).addPathPatterens("/secure/*");
	}
}
