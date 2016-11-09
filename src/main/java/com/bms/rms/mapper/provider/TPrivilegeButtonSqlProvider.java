package com.bms.rms.mapper.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.boboface.base.data.type.conversion.StringTypeConversion;

/**
 * 
 * Title:TPrivilegeButtonSqlProvider
 * Description:TPrivilegeButton 构造sql类
 * @author    zwb
 * @date      2016年11月3日 下午5:03:51
 *
 */
public class TPrivilegeButtonSqlProvider {
	
	/**
	 * 清除指定权限绑定的按钮
	 * @param map
	 * @return
	 */
	public String updateClearPrivilegeButtonByPrivilegeIdsSql(Map<String, Object> map){
		final List<Integer> privilegeIds = (List<Integer>) map.get("privilegeIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (privilegeIds != null && privilegeIds.size() > 0) {	
				whereStr = "privilegeId in (" + StringTypeConversion.listStrToString(privilegeIds, ",") + ")";
			}
			UPDATE("t_privilege_button");
			SET("privilegeId = null");
			WHERE(whereStr);
		}}.toString();
	}
}
