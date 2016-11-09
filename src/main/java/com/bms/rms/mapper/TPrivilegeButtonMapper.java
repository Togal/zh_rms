package com.bms.rms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import tk.mybatis.mapper.common.Mapper;

import com.bms.rms.mapper.provider.TPrivilegeButtonSqlProvider;
import com.bms.rms.model.po.TPrivilegeButton;
import com.bms.rms.model.po.TPrivilegeButtonCustom;

/**
 * 
 * Title:TPrivilegeButtonMapper
 * Description:按钮级别权限控制mapper接口
 * @author    zwb
 * @date      2016年11月1日 下午6:28:12
 *
 */
public interface TPrivilegeButtonMapper extends Mapper<TPrivilegeButton> {
	
	/**
	 * 查询全部按钮
	 * @return
	 */
	@Select("SELECT * FROM t_privilege_button")
	List<TPrivilegeButtonCustom> findAllPrivilegeButton();
	
	/**
	 * 根据权限id查询按钮
	 * @param privilegeId
	 * @return
	 */
	@Select("SELECT * FROM t_privilege_button WHERE privilegeId = #{privilegeId}")
	TPrivilegeButton findPrivilegeButtonByPrivilegeId(Integer privilegeId);
	
	/**
	 * 根据权限id清除按钮绑定的权限
	 * @param privilegeIds
	 */
	@UpdateProvider(type = TPrivilegeButtonSqlProvider.class, method = "updateClearPrivilegeButtonByPrivilegeIdsSql")
	void updateClearPrivilegeButtonByPrivilegeIds(@Param("privilegeIds")List<Integer> privilegeIds);
}
