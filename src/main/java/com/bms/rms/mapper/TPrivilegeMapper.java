package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.mapper.provider.TPrivilegeSqlProvider;
import com.bms.rms.model.po.TPrivilege;
import com.bms.rms.model.po.TPrivilegeCustom;

/**
 * 
 * Title:TPrivilegeMapper
 * Description:权限Mapper接口
 * @author    zwb
 * @date      2016年10月19日 上午10:30:16
 *
 */
public interface TPrivilegeMapper extends Mapper<TPrivilege> {
	
	/**
	 * 递归查询权限
	 * @return List<TPrivilegeCustom>
	 */
	@Select("SELECT * FROM t_privilege WHERE parentId is NULL")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "childrenPrivileges",
				column = "id",
				many = @Many(select = "findPrivilegesByParentId")),
	})
	public List<TPrivilegeCustom> findPrivilegesForCascade();
	
	/**
	 * 根据父id查询权限
	 * @param parentId 父权限id
	 * @return
	 */
	@Select("SELECT * FROM t_privilege WHERE parentId = #{parentId}")
	@Results({
		@Result(property = "id", column = "id"),
		@Result(property = "childrenPrivileges",
				column = "id",
				many = @Many(select = "findPrivilegesByParentId"))
	})
	public List<TPrivilegeCustom> findPrivilegesByParentId(Integer parentId);
	
	
	/**
	 * 根据id递归查询权限
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM t_privilege WHERE id = #{id}")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "childrenPrivileges",
				column = "id",
				many = @Many(select = "findPrivilegesByParentId")),
	})
	public List<TPrivilegeCustom> findPrivilegeByIdForCascade(Integer id);
	
	/**
	 * 根据权限id查询角色ids
	 * @param privilegeId
	 * @return
	 */
	@Select("SELECT tr.id FROM t_role tr "
			+ "INNER JOIN t_role_privilege trp ON tr.id = trp.roleId "
			+ "INNER JOIN t_privilege tp ON trp.privilegeId = tp.id WHERE tp.id = #{privilegeId}")
	public List<Integer> findRoleIdsByPrivilegeId(Integer privilegeId);
	
	/**
	 * 新增权限角色关联
	 * @param privilegeId
	 * @param insertRole
	 */
	@Insert("INSERT INTO t_role_privilege(privilegeId,RoleId) VALUES(#{0},#{1})")
	public void insertPrivilegeRole(Integer privilegeId, Integer insertRole);
	
	/**
	 * 删除权限角色关联
	 * @param privilegeId
	 * @param deleteRole
	 */
	@Delete("DELETE FROM t_role_privilege WHERE privilegeId = #{0} AND roleId = #{1}")
	public void deletePrivilegeRoleByPrivilegeId(Integer privilegeId, Integer deleteRole);
	
	/**
	 * 根据权限ids删除权限角色关联表
	 * @param privilegeIds
	 */
	@DeleteProvider(type = TPrivilegeSqlProvider.class, method = "deletePrivilegeRoleByPrivilegeIdsSql")
	public void deletePrivilegeRoleByPrivilegeIds(@Param("privilegeIds")List<Integer> privilegeIds);
	
	/**
	 * 权限根据按钮id去除绑定的按钮
	 * @param menuIds
	 */
	@UpdateProvider(type = TPrivilegeSqlProvider.class, method = "updateClearMenuByMenuIdsSql")
	public void updateClearMenuByMenuIds(@Param("menuIds")List<Integer> menuIds);
	
}