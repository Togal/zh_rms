package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TUser;

/**
 * 
 * Title:TUserVo
 * Description:用户包装类
 * @author    zwb
 * @date      2016年10月19日 下午4:44:05
 *
 */
public class TUserVo {
	
	private TUser user;
	
	private Date addtime;//添加时间
	
	private String userpassword;//密码
	
	private String userpasswordagain;//二次密码

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getUserpasswordagain() {
		return userpasswordagain;
	}

	public void setUserpasswordagain(String userpasswordagain) {
		this.userpasswordagain = userpasswordagain;
	}
}
