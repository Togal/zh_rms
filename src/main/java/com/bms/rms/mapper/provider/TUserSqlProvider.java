package com.bms.rms.mapper.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.boboface.base.data.type.conversion.StringTypeConversion;

/**
 * 
 * Title:TUserSqlProvider
 * Description:TUser sql构造类
 * @author    zwb
 * @date      2016年10月25日 下午5:08:07
 *
 */
public class TUserSqlProvider {
	
	/**
	 * 根据用户组ids查询角色ids
	 * @param map
	 * @return
	 */
	public String findRoleIdsByGroupIdsSql(Map<String, Object> map){
		final List<Integer> userGroupIds =   (List<Integer>) map.get("userGroupIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (userGroupIds != null && userGroupIds.size() > 0) {	
				whereStr = "groupId in (" + StringTypeConversion.listStrToString(userGroupIds, ",") + ")";
			}
			SELECT("roleId");
			FROM("t_group_role");
			WHERE(whereStr);
		}}.toString();
	}
	
	/**
	 * 根据用户的角色ids和用户的用户组的角色ids查询权限
	 * @param map
	 * @return
	 */
	public String findPrivilegeByUserSql(Map<String, Object> map){
		final List<Integer> userRoleIds =   (List<Integer>) map.get("userRoleIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (userRoleIds != null && userRoleIds.size() > 0) {	
				whereStr = "trp.roleId in (" + StringTypeConversion.listStrToString(userRoleIds, ",") + ")";
			}
			SELECT("tp.*");
			FROM("t_role_privilege trp");
			INNER_JOIN("t_privilege tp on trp.privilegeId = tp.id");
			WHERE(whereStr);
		}}.toString();
	}
}
