package com.rms.model.vo;

import java.util.Date;

import com.rms.model.po.TPrivilege;

/**
 * 
 * Title:TPrivilegeVo
 * Description:权限VO包装类
 * @author    zwb
 * @date      2016年10月19日 上午11:37:03
 *
 */
public class TPrivilegeVo {

	private TPrivilege privilege;
	
	private Date addtime;//添加时间

	public TPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(TPrivilege privilege) {
		this.privilege = privilege;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	
	
}
