package com.bms.rms.service.impl;

import org.springframework.stereotype.Service;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TDepartment;
import com.bms.rms.service.IDepartmentService;

/**
 * 
 * Title:DepartmentServiceImpl
 * Description:部门service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:30:07
 *
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<TDepartment> implements IDepartmentService{

	@Override
	public void deleteDepartment(Integer departmentId) {
		int affectRow = tDepartmentMapper.deleteByPrimaryKey(departmentId);
		if(affectRow > 0){
			tUserMapper.updateClearUserDepartment(departmentId);
		}
	}

	@Override
	public void deleteDepartment(Integer[] departmentIds) {
		for (Integer departmentId : departmentIds) {
			deleteDepartment(departmentId);
		}
	}
}
