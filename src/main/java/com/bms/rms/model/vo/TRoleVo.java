package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TRole;

/**
 * 
 * Title:TRoleVo
 * Description:角色包装类
 * @author    zwb
 * @date      2016年10月19日 下午2:16:04
 *
 */
public class TRoleVo {
	
	private TRole role;
	
	private Date addtime;//添加时间

	public TRole getRole() {
		return role;
	}

	public void setRole(TRole role) {
		this.role = role;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}	
}
