package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TPrivilegeButton;

/**
 * 
 * Title:TPrivilegeButtonVo
 * Description:按钮级别权限控制vo类
 * @author    zwb
 * @date      2016年11月2日 上午11:30:31
 *
 */
public class TPrivilegeButtonVo {
	
	private TPrivilegeButton privilegeButton;
	
	private Date addtime;//添加时间

	public TPrivilegeButton getPrivilegeButton() {
		return privilegeButton;
	}

	public void setPrivilegeButton(TPrivilegeButton privilegeButton) {
		this.privilegeButton = privilegeButton;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
