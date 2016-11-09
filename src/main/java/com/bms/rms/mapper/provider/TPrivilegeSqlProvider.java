package com.bms.rms.mapper.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.boboface.base.data.type.conversion.StringTypeConversion;

/**
 * 
 * Title:TPrivilegeSqlProvider
 * Description:TPrivilege sql构造类
 * @author    zwb
 * @date      2016年11月3日 下午4:52:08
 *
 */
public class TPrivilegeSqlProvider {
	
	/**
	 * 根据权限id删除关联表
	 * @param map
	 * @return
	 */
	public String deletePrivilegeRoleByPrivilegeIdsSql(Map<String, Object> map){
		final List<Integer> privilegeIds = (List<Integer>) map.get("privilegeIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (privilegeIds != null && privilegeIds.size() > 0) {	
				whereStr = "privilegeId in (" + StringTypeConversion.listStrToString(privilegeIds, ",") + ")";
			}
			DELETE_FROM("t_role_privilege");
			WHERE(whereStr);
		}}.toString();
	}
	
	/**
	 * 权限根据按钮id去除绑定的按钮
	 * @param map
	 * @return
	 */
	public String updateClearMenuByMenuIdsSql(Map<String, Object> map){
		final List<Integer> menuIds =   (List<Integer>) map.get("menuIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (menuIds != null && menuIds.size() > 0) {	
				whereStr = "menuId in (" + StringTypeConversion.listStrToString(menuIds, ",") + ")";
			}
			UPDATE("t_privilege");
			SET("menuId = NULL");
			WHERE(whereStr);
		}}.toString();
	}
}
