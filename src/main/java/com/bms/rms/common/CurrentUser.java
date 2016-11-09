package com.bms.rms.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bms.listener.SesstionListener;
import com.bms.rms.model.po.TUserCustom;
import com.boboface.base.util.WebUtil;

/**
 * 
 * Title:CurrentUser
 * Description:当前用户
 * @author    zwb
 * @date      2016年10月25日 上午10:49:49
 *
 */
public class CurrentUser {
	/**
	 * 获取当前用户
	 * @param request
	 * @param response
	 * @return
	 */
	public static TUserCustom getUser(HttpServletRequest request, HttpServletResponse response){
		TUserCustom user = (TUserCustom) request.getSession().getAttribute("currentUser");
		if(user == null){
			String sid = WebUtil.getCookieByName(request, "currentUserId");
			if(sid!=null){
				HttpSession session = SesstionListener.getSession(sid);
				if (session != null) {
					user =  (TUserCustom) session.getAttribute("currentUser");
					if (user != null) {
						SesstionListener.removeSession(sid);
						request.getSession().setAttribute("currentUser", user);
						WebUtil.addCookie(response, "currentUserId", request.getSession().getId(), 30 * 60);
					}
				}
			}
		}
		return user == null ? null : user;
	}
}
