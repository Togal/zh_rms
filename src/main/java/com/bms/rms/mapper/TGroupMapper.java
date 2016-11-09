package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.model.po.TGroup;

/**
 * 
 * Title:TGroupMapper
 * Description:用户组mapper
 * @author    zwb
 * @date      2016年10月19日 下午3:18:10
 *
 */
public interface TGroupMapper extends Mapper<TGroup>{
	/**
	 * 根据用户组id查询角色ids
	 * @param groupId
	 * @return
	 */
	@Select("SELECT tr.id FROM t_role tr "
			+ "INNER JOIN t_group_role tgr ON tr.id = tgr.roleId "
			+ "INNER JOIN t_group tg ON tgr.groupId = tg.id WHERE tg.id = #{groupId}")
	public List<Integer> findRoleIdsByGroupId(Integer groupId);
	
	/**
	 * 新增用户组角色关联
	 * @param groupId
	 * @param insertRole
	 */
	@Insert("INSERT INTO t_group_role(groupId,RoleId) VALUES(#{0},#{1})")
	public void insertGroupRole(Integer groupId, Integer insertRole);
	
	/**
	 * 删除用户组角色关联
	 * @param groupId
	 * @param deleteRole
	 */
	@Delete("DELETE FROM t_group_role WHERE groupId = #{0} AND roleId = #{1}")
	public void deleteGroupRoleByGroupIdAndRoleId(Integer groupId, Integer roleId);
	
	/**
	 * 根据用户组id查询用户ids
	 * @param groupId
	 * @return
	 */
	@Select("SELECT tu.id FROM t_group tg "
			+ "INNER JOIN t_user_group tug ON tg.id = tug.groupId "
			+ "INNER JOIN t_user tu ON tug.userId = tu.id WHERE tg.id = #{groupId}")
	public List<Integer> findUserIdsByGroupId(Integer groupId);
	
	/**
	 * 新增用户组用户关联
	 * @param groupId
	 * @param insertUser
	 */
	@Insert("INSERT INTO t_user_group(groupId,userId) VALUES(#{0},#{1})")
	public void insertGroupUser(Integer groupId, Integer insertUser);
	
	/**
	 * 删除用户组用户关联
	 * @param groupId
	 * @param deleteUser
	 */
	@Delete("DELETE FROM t_user_group WHERE groupId = #{0} AND userId = #{1}")
	public void deleteGroupUserByGroupIdAndUserId(Integer groupId, Integer userId);
	
	/**
	 * 根据用户组id删除用户组角色关联关系
	 * @param groupId
	 */
	@Delete("DELETE FROM t_group_role WHERE groupId = #{0}")
	public void deleteGroupRoleByGroupId(Integer groupId);
	
	/**
	 * 根据用户组id删除用户组用户关联关系
	 * @param groupId
	 */
	@Delete("DELETE FROM t_user_group WHERE groupId = #{0}")
	public void deleteGroupUserByGroupId(Integer groupId);
}