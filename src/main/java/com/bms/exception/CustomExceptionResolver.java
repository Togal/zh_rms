package com.bms.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.boboface.base.helper.CodeHelper.CODE;
import com.boboface.base.model.vo.PubRetrunMsg;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * Title:CustomExceptionResolver
 * Description:全局异常处理器
 * 				如果方法返回是返回json信息，则返回json
 * 				如果方法返回页面，则返回页面
 * @author    zwb
 * @date      2016年5月10日 下午11:09:18
 *
 */
public class CustomExceptionResolver extends ExceptionHandlerExceptionResolver implements HandlerExceptionResolver {

	private static Logger logger = LoggerFactory.getLogger(CustomExceptionResolver.class);  
	
	/**
	 * json序列化
	 */
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		ModelAndView modelAndView = new ModelAndView();
		//登陆特殊处理
		if(handler == null 
				&& ex instanceof HttpRequestMethodNotSupportedException 
				&& request.getRequestURI().indexOf("loginSubmit") > -1){
			modelAndView.setViewName("login");
			return modelAndView;
		}
		
		String message = getCustomException(ex).getMessage();//获取异常信息
		HandlerMethod handlerMethod = null;//处理器method
		if(handler instanceof HandlerMethod){
			handlerMethod = (HandlerMethod) handler;
		}else{
			modelAndView.setViewName("result");
			modelAndView.addObject("msg", message);
			return modelAndView;
		}
		ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);//获取返回类型
		
		if(body == null){			
			modelAndView.setViewName("result");//指向错误页面
			modelAndView.addObject("msg", message);
			return modelAndView;
		}
	
		//返回json串
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setCharacterEncoding("UTF-8");
	    response.setHeader("Cache-Control","no-cache, must-revalidate");
	    try {
	    	response.getWriter().write(objectMapper.writeValueAsString(new PubRetrunMsg(CODE.D300000, null)));
		} catch (IOException e) {
			logger.error("IOException catch:" ,e);
		}
	    return modelAndView;
	}
	
	/**
	 * 获取异常信息
	 * @param ex
	 * @return
	 */
	private CustomException getCustomException(Exception ex){
		CustomException customException=null;
		if(ex instanceof CustomException){
			return customException = (CustomException) ex;
		}else if(ex instanceof HttpRequestMethodNotSupportedException){
			return customException = new CustomException(ex.getMessage());
		}else{
			logger.error("全局异常处理捕获异常 catch:", ex);
			return customException = new CustomException("未知错误");
		}
	}
}
