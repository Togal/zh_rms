package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TGroup;

/**
 * 
 * Title:TGroupVo
 * Description:用户组vo类
 * @author    zwb
 * @date      2016年10月19日 下午3:21:38
 *
 */
public class TGroupVo {
	
	private TGroup group;
	
	private Date addtime;//添加时间

	public TGroup getGroup() {
		return group;
	}

	public void setGroup(TGroup group) {
		this.group = group;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
