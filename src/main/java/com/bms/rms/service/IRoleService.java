package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TRole;

/**
 * 
 * Title:IRoleService
 * Description:角色接口
 * @author    zwb
 * @date      2016年10月19日 下午12:59:00
 *
 */
public interface IRoleService extends IBaseService<TRole> {
	
	/**
	 * 根据角色查询权限ids
	 * @param roleId
	 * @return
	 */
	List<Integer> findPrivilegeIdsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id更新角色权限
	 * @param roleId
	 * @param newPrivilegeIds
	 */
	void updateRolePrivilegeByRoleId(Integer roleId, List<Integer> newPrivilegeIds);
	
	/**
	 * 根据角色查询用户ids
	 * @param roleId
	 * @return
	 */
	List<Integer> findUserIdsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id更新角色用户
	 * @param roleId
	 * @param newUserIds
	 */
	void updateRoleUserByRoleId(Integer roleId, List<Integer> newUserIds);
	
	/**
	 * 根据角色查询用户组ids
	 * @param roleId
	 * @return
	 */
	List<Integer> findGroupIdsByRoleId(Integer roleId);
	
	/**
	 * 根据角色id更新角色用户组
	 * @param roleId
	 * @param newGroupIds
	 */
	void updateRoleGroupByRoleId(Integer roleId, List<Integer> newGroupIds);
	
	/**
	 * 删除角色
	 * @param roleId
	 */
	void deleteRole(Integer roleId);
	
	/**
	 * 批量删除角色
	 * @param roleIds
	 */
	void delteRole(Integer[] roleIds);
}
