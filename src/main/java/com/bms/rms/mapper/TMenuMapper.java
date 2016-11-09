package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.mapper.provider.TMenuSqlProvider;
import com.bms.rms.model.po.TMenu;
import com.bms.rms.model.po.TMenuCustom;
import com.bms.rms.model.po.TPrivilege;

/**
 * 
 * Title:TMenuMapper
 * Description:菜单mapper接口
 * @author    zwb
 * @date      2016年9月29日 下午12:01:12
 *
 */
public interface TMenuMapper extends Mapper<TMenu>  {
	
	/**
	 * 查询顶级从菜单（关联查询从菜单的子菜单和对应权限）
	 * @return
	 */
	@Select("SELECT * FROM t_menu WHERE status = 0 AND menuType = 0 AND parentId is Null")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "slaveChildrenMenus", 
				column = "id",
				many = @Many(select = "findTopSlaveMenusByMasterMenuId"))
	})
	public List<TMenuCustom> findTopSlaveMenusAndPrivilege();
	
	/**
	 * 根据masterMenuId查询菜单
	 * @param masterMenuId
	 * @return
	 */
	@Select("SELECT * FROM t_menu WHERE status = 0 AND masterMenuId = #{masterMenuId} AND parentId is Null")
	@Results({
		@Result(property ="id",column="id"),
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusAndPrivilegeByParentId"))
	})
	public List<TMenuCustom> findTopSlaveMenusByMasterMenuId(Integer masterMenuId);
	
	/**
	 * 根据父菜单id查询菜单（含权限数据）
	 * @param parentId 父菜单id
	 * @return List<TMenuCustom>
	 */
	@Select("SELECT * FROM t_menu WHERE status = 0 AND parentId = #{parentId}")
	@Results({
		@Result(property = "id",column="id"),
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusAndPrivilegeByParentId")),
		@Result(property = "privilege",
				column = "id",
				one =@One(select ="findPrivilegeByMenuId"))
	})
	public List<TMenuCustom> findTopSlaveMenusAndPrivilegeByParentId(Integer parentId);
	
	/**
	 * 根据菜单id查询权限
	 * @param menuId 菜单id
	 * @return TPrivilege
	 */
	@Select("SELECT * FROM t_privilege WHERE menuId = #{menuId}")
	public TPrivilege findPrivilegeByMenuId(Integer menuId);
	
	/**
	 * 查询最大排序号
	 * @return
	 */
	@Select("SELECT MAX(sort) FROM t_menu")
	public Byte findMenuMaxSort();
	
	/**
	 * 级联查询
	 * @return
	 */
	@Select("SELECT * FROM t_menu WHERE menuType = 1 AND parentId is NULL")
	@Results({
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusByParentId"))
	})
	public List<TMenuCustom> findAllForCascade();
	
	/**
	 * 根据父菜单id查询菜单
	 * @param parentId 父菜单id
	 * @return List<TMenuCustom>
	 */
	@Select("SELECT * FROM t_menu WHERE status = 0 AND parentId = #{parentId}")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusAndPrivilegeByParentId")),
	})
	public List<TMenuCustom> findTopSlaveMenusByParentId(Integer parentId);
	
	/**
	 * 根据主master菜单id级联查询
	 * @return
	 */
	@Select("SELECT * FROM t_menu WHERE masterMenuId = #{masterMenuId} AND parentId is NULL")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusByParentId"))
	})
	public List<TMenuCustom> findMenusByMasterIdForCascade(Integer masterMenuId);
	
	/**
	 * 级联查询
	 * @return
	 */
	@Select("SELECT * FROM t_menu WHERE parentId is NULL")
	@Results({
		@Result(property = "id", column="id"),
		@Result(property = "slaveChildrenMenus",
				column = "id",
				many = @Many(select = "findTopSlaveMenusByParentId"))
	})
	public List<TMenuCustom> findMenusForCascade();
	
	/**
	 * 清除主菜单绑定
	 * @param masterMenuId
	 */
	@UpdateProvider(type = TMenuSqlProvider.class, method = "updateClearMasterMenuByMasterMenuIdsSql")
	public void updateClearMasterMenuByMasterMenuIds(@Param("masterMenuIds")List<Integer> masterMenuIds);
}