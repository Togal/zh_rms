package com.bms.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.bms.exception.CustomExceptionResolver;
import com.bms.interceptor.CheckPrivilegeInterceptor;

/**
 * 
 * Title:InterceptorConfig
 * Description:配置
 * @author    zwb
 * @date      2016年10月21日 下午6:27:15
 *
 */
@Configuration
public class MyWebAppConfig extends WebMvcConfigurerAdapter {

	/**
	 * 拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckPrivilegeInterceptor()).addPathPatterns("/**");
	}

	/**
	 * 异常处理器
	 */
	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new CustomExceptionResolver());
	}
}
