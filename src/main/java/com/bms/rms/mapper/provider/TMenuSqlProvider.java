package com.bms.rms.mapper.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.boboface.base.data.type.conversion.StringTypeConversion;

/**
 * 
 * Title:TMenuSqlProvider
 * Description:
 * @author    zwb
 * @date      2016年11月7日 下午3:03:47
 *
 */
public class TMenuSqlProvider {
	
	/**
	 * 清除主菜单绑定
	 * @param map
	 */
	public String updateClearMasterMenuByMasterMenuIdsSql(Map<String, Object> map){
		final List<Integer> masterMenuIds =   (List<Integer>) map.get("masterMenuIds");
		return new SQL(){{
			String whereStr = "1 = 0";
			if (masterMenuIds != null && masterMenuIds.size() > 0) {	
				whereStr = "masterMenuId in (" + StringTypeConversion.listStrToString(masterMenuIds, ",") + ")";
			}
			UPDATE("t_menu");
			SET("masterMenuId = NULL");
			WHERE(whereStr);
		}}.toString();
	}
}
