package com.example.mybatis_1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.mybatis_1.interceptor.UsersInterceptor;

@Configuration
public class UsersInterceptorAppConfig implements  WebMvcConfigurer {
	 @Autowired
	   UsersInterceptor userInterceptor;
		@Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(userInterceptor);
	    }
}
