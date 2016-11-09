package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TUser;
import com.bms.rms.model.po.TUserCustom;

/**
 * 
 * Title:IUserService
 * Description:用户service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:29:07
 *
 */
public interface IUserService extends IBaseService<TUser> {
	/**
	 * 根据用户查询角色ids
	 * @param userId
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer userId);
	
	/**
	 * 根据用户id更新角色
	 * @param userId
	 * @param newRoleIds
	 */
	void updateUserRoleByUserId(Integer userId, List<Integer> newRoleIds);
	
	/**
	 * 根据用户查询用户组ids
	 * @param userId
	 * @return
	 */
	List<Integer> findGroupIdsByUserId(Integer userId);
	
	/**
	 * 根据用户id更新用户组
	 * @param userId
	 * @param newGroupIds
	 */
	void updateUserGroupByUserId(Integer userId, List<Integer> newGroupIds);
	
	/**
	 * 根据用户名和密码查询用户
	 * @param userAccount 用户名
	 * @param userPassword 密码
	 * @return
	 */
	TUserCustom findUserByUserAccountAndUserPassword(String userAccount,String userPassword);
	
	/**
	 * 删除用户
	 * @param userId
	 */
	void deleteUser(Integer userId);
	
	/**
	 * 批量删除用户
	 * @param userIds
	 */
	void deleteUser(Integer[] userIds);
}
