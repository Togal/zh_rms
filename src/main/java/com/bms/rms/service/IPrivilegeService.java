package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TPrivilege;
import com.bms.rms.model.po.TPrivilegeCustom;

/**
 * 
 * Title:IPrivilegeService
 * Description:权限service接口
 * @author    zwb
 * @date      2016年9月28日 下午6:28:56
 *
 */
public interface IPrivilegeService extends IBaseService<TPrivilege> {
	/**
	 * 查询子级从权限
	 * @param parentId 父级id
	 * @return
	 */
	List<TPrivilege> findChildrenPrivileges(Integer parentId);
	
	/**
	 * 递归查询权限
	 * @return
	 */
	List<TPrivilegeCustom> findPrivilegesForCascade();
	
	/**
	 * 查询顶级权限
	 * @return
	 */
	List<TPrivilege> findTopPrivileges();
	
	/**
	 * 更新实体
	 * @param tPrivilege 权限
	 * @param privilegeButtonId 按钮级别绑定权限
	 */
	 void updatePrivilege(TPrivilege tPrivilege, Integer privilegeButtonId);
	 
	 /**
	  * 根据id递归删除权限
	  * @param id
	  */
	 void deletePrivilegeByIdForRecursion(Integer id);
	 
	 /**
	  * 根据ids批量删除递归权限
	  * @param ids
	  */
	 void deletePrivilegeByIdsForRecursion(Integer[] ids);
	 
	/**
	 * 根据权限查询角色ids
	 * @param privilegeId
	 * @return
	 */
	List<Integer> findRoleIdsByPrivilegeId(Integer privilegeId);
	
	/**
	 * 根据权限id更新权限角色
	 * @param privilegeId
	 * @param newRoleIds
	 */
	void updatePrivilegeRoleByPrivilegeId(Integer privilegeId, List<Integer> newRoleIds);
	
	/**
	 * 查询全部权限
	 * @return
	 */
	List<String> findAllPrivilege();
	
	/**
	 * 保存实体
	 * @param tPrivilege 权限
	 * @param privilegeButtonId 按钮级别绑定权限
	 */
	 void savePrivilege(TPrivilege tPrivilege, Integer privilegeButtonId);
}
