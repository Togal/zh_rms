package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TMenu;
import com.bms.rms.model.po.TMenuCustom;
import com.bms.rms.model.vo.MenuTypeEnum;


/**
 * 
 * Title:IMenuService
 * Description:菜单service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:45
 *
 */
public interface IMenuService extends IBaseService<TMenu>{
	
	/**
	 * 根据status查询主菜单
	 * @return List<Menu>
	 */
	List<TMenu> findMasterMenusByStatus(Byte status);
	
	/**
	 * 根据status查询主菜单（不包含自己）
	 * @param status
	 * @return
	 */
	List<TMenu> findMasterMenusByStatusAndNotMe(Byte status, Integer menuId);
	
	/**
	 * 查询顶级从菜单(关联查询子菜单权限)
	 * @return List<TMenuCustom>
	 */
	List<TMenuCustom> findTopSlaveMenusAndPrivilege();
	
	/**
	 * 查询顶级从菜单
	 * @return
	 */
	List<TMenu> findTopMenus(MenuTypeEnum menuTypeEnum);
	
	/**
	 * 查询子级从菜单
	 * @param parentId 父级id
	 * @return
	 */
	List<TMenu> findChildrenSlaveMenus(Integer parentId);
	
	/**
	 * 查询从菜单
	 * @return
	 */
	List<TMenu> findSlaveMenus();
	
	/**
	 * 根据masterId级联查询从菜单
	 * @return
	 */
	List<TMenuCustom> findMenusForCascade(Integer masterMenuId);
	
	/**
	 * 更新从从菜单到主菜单
	 * @param masterMenuId 主master菜单id
	 * @param parentMenuId 父菜单id
	 */
	void updateSlaveToMasterMenu(Integer masterMenuId, Integer parentMenuId);
	
	/**
	 * 查询最大排序号
	 * @return
	 */
	Byte findMenuMaxSort();
	
	/**
	 * 递归查询菜单和子菜单的ids
	 * @param id 指定餐单
	 * @return
	 */
	 List<Integer> findMenuAndChildrenMenuIdsForRecursion(Integer id,List<Integer> ids);
	 
	 /**
	  * 查询全部底层节点
	  * @return
	  */
	 List<TMenu> findAllBottomMenus();

	 /**
	  * 根据主master菜单id递归查询菜单
	  * @param masterMenuId 主master菜单
	  * @return
	  */
	 List<TMenu> findMenusForRecursion(Integer masterMenuId);
	 
	 /**
	  * 根据主master菜单id递归查询菜单(不包含自己)
	  * @param masterMenuId 主master菜单
	  * @param isNotMenuId 不含自己
	  * @return
	  */
	 List<TMenu> findMenusIsNotMenuForRecursion(Integer masterMenuId, Integer isNotMenuId);
	 
	 /**
	  * 更新实体
	  * @param tMenu
	  */
	 void updateMenuSeletive(TMenu tMenu);
	 
	 
	/**
	 * 级联查询从菜单
	 * @return
	 */
	List<TMenuCustom> findMenusForCascade();
	
	
	/**
	 * 删除菜单
	 * @param ids
	 */
	void deleteMenu(Integer[] ids);
}
