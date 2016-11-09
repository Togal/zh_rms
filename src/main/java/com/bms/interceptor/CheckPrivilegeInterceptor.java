package com.bms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bms.rms.common.CurrentUser;
import com.bms.rms.model.po.TUserCustom;

/**
 * 
 * Title:CheckPrivilegeInterceptor
 * Description:权限拦截器
 * @author    zwb
 * @date      2016年10月21日 下午6:23:05
 *
 */
public class CheckPrivilegeInterceptor implements HandlerInterceptor  {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		if(!(requestURI.indexOf("save") > -1)){
			requestURI = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);
		}
		TUserCustom user = CurrentUser.getUser(request, response);//获取当前用户
		if(requestURI.startsWith("/error"))
			return true;
		
		if(user == null){//未登陆
			if(isAjaxRequest(request)){
				
			}else{//普通请求
				if(requestURI.startsWith("/login")){//如果是去登陆则放行
					return true;
				}else{//如果不是登陆则去登陆
					//request.getRequestDispatcher("/login").forward(request, response);
					response.sendRedirect("/login");
				}
			}
		}else{//已登陆
			if(user.hasPrivilegeByUrl(requestURI, request)){//有权限
				return true;
			}else{
				request.getRequestDispatcher("/error").forward(request, response);
			}
		}
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) throws Exception {
	}
	
	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	private static boolean isAjaxRequest(HttpServletRequest request){
		String requestType = request.getHeader("X-Requested-With");//请求类型（普通请求、ajax请求）
		if(StringUtils.isBlank(requestType) && "XMLHttpRequest".equalsIgnoreCase(requestType)){
			return true;
		}else{
			return false;
		}
	}
}
