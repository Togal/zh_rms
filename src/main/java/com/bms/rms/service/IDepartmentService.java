package com.bms.rms.service;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TDepartment;

/**
 * 
 * Title:IDepartmentService
 * Description:部门service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:20
 *
 */
public interface IDepartmentService extends IBaseService<TDepartment> {
	
	/**
	 * 根据departmentid删除部门
	 * @param departmentId
	 */
	void deleteDepartment(Integer departmentId);
	
	/**
	 * 根据departmentid批量删除部门
	 * @param departmentIds
	 */
	void deleteDepartment(Integer[] departmentIds);
}
