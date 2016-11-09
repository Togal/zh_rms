package com.bms.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TRole;
import com.bms.rms.service.IRoleService;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:RoleServiceImpl
 * Description:角色service实现类
 * @author    zwb
 * @date      2016年10月19日 下午1:00:39
 *
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<TRole> implements IRoleService {

	@Override
	public List<Integer> findPrivilegeIdsByRoleId(Integer roleId) {
		return tRoleMapper.findPrivilegeIdsByRoleId(roleId);
	}

	@Override
	public void updateRolePrivilegeByRoleId(Integer roleId, List<Integer> newPrivilegeIds) {
		List<Integer> oldPrivilegeIds = findPrivilegeIdsByRoleId(roleId);
		Map<String, Object> map = BaseUtil.compareArry(oldPrivilegeIds, newPrivilegeIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tRoleMapper.insertRolePrivilege(roleId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tRoleMapper.deleteRolePrivilegeByRoleIdAndPrivilegeId(roleId, deleteId);
		}
	}

	@Override
	public List<Integer> findUserIdsByRoleId(Integer roleId) {
		return tRoleMapper.findUserIdsByRoleId(roleId);
	}

	@Override
	public void updateRoleUserByRoleId(Integer roleId, List<Integer> newUserIds) {
		List<Integer> oldUserIds = findUserIdsByRoleId(roleId);
		Map<String, Object> map = BaseUtil.compareArry(oldUserIds, newUserIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tRoleMapper.insertRoleUser(roleId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tRoleMapper.deleteRoleUserByRoleIdAndUserId(roleId, deleteId);
		}
	}

	@Override
	public List<Integer> findGroupIdsByRoleId(Integer roleId) {
		return tRoleMapper.findGroupIdsByRoleId(roleId);
	}
	
	@Override
	public void updateRoleGroupByRoleId(Integer roleId, List<Integer> newGroupIds) {
		List<Integer> oldGroupIds = findGroupIdsByRoleId(roleId);
		Map<String, Object> map = BaseUtil.compareArry(oldGroupIds, newGroupIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tRoleMapper.insertRoleGroup(roleId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tRoleMapper.deleteRoleGroupByRoleIdAndGroupId(roleId, deleteId);
		}
	}

	@Override
	public void deleteRole(Integer roleId) {
		int affectRow = tRoleMapper.deleteByPrimaryKey(roleId);
		if(affectRow > 0){
			//角色权限关联关系表
			tRoleMapper.deleteRolePrivilegeByRoleId(roleId);
			//角色用户关联关系表
			tRoleMapper.deleteRoleUserByRoleId(roleId);
			//角色用户组关联关系表
			tRoleMapper.deleteRoleGroupByRoleId(roleId);
		}
	}

	@Override
	public void delteRole(Integer[] roleIds) {
		for (Integer roleId : roleIds) {
			deleteRole(roleId);
		}
	}
}
