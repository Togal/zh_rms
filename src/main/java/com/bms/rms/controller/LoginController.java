package com.bms.rms.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bms.base.controller.BaseController;
import com.bms.rms.model.po.TUserCustom;
import com.boboface.base.util.WebUtil;

/**
 * 
 * Title:LoginController
 * Description:登陆controller
 * @author    zwb
 * @date      2016年10月21日 下午4:13:07
 *
 */
@Controller
public class LoginController extends BaseController {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	/**
	 * 登陆页
	 * @return
	 */
	@GetMapping("/login")
	public String login(){
		redisClient.set("name","zowbman");
		log.error(redisClient.get("name"));
		return "login";
	}	
	
	/**
	 * 登陆
	 * @return
	 */
	@PostMapping("/loginSubmit")
	public String loginSubmit(Model model, HttpSession httpSession, 
			@CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId,
			HttpServletResponse resp, String useraccount, String userpassword){
		//校验参数
		TUserCustom user = iUserService.findUserByUserAccountAndUserPassword(useraccount,userpassword);
		
		if(user == null){
			model.addAttribute("error", "*您输入的帐号或密码有误");
			return "login";
		}
		
		//存session
		httpSession.setAttribute("currentUser", user);
		WebUtil.addCookie(resp, "currentUserId", sessionId, 30 * 60);
		return "redirect:";
	}
	
	/**
	 * 注销
	 * @return
	 */
	@GetMapping("/logout")
	public String loginOut(HttpSession httpSession){
		httpSession.removeAttribute("currentUser");
		return "result";
	}
}
