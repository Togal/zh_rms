package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TDepartment;

/**
 * 
 * Title:TDepartmentVo
 * Description:部门包装类
 * @author    zwb
 * @date      2016年10月19日 下午2:34:08
 *
 */
public class TDepartmentVo {
	
	private TDepartment department;
	
	private Date addtime;//添加时间

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
}
