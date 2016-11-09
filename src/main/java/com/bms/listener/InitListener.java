package com.bms.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.bms.rms.service.IPrivilegeService;

/**
 * 
 * Title:InitListner
 * Description:初始化监听器
 * @author    zwb
 * @date      2016年9月29日 上午11:54:22
 *
 */
public class InitListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//获取所有权限
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
		IPrivilegeService iPrivilegeService = (IPrivilegeService) ac.getBean("privilegeServiceImpl");
		List<String> allPrivilegeUrl = iPrivilegeService.findAllPrivilege();
		sce.getServletContext().setAttribute("allPrivilegeUrl", allPrivilegeUrl);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}
