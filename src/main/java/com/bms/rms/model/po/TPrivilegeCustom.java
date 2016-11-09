package com.bms.rms.model.po;

import java.util.List;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TPrivilegeCustom
 * Description:权限扩展类
 * @author    zwb
 * @date      2016年10月19日 上午10:44:24
 *
 */
public class TPrivilegeCustom extends TPrivilege {
	
	private List<TPrivilegeCustom> childrenPrivileges;//子权限
	
	private TPrivilegeButton privilegeButton;//按钮权限绑定
	
    /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }

	public List<TPrivilegeCustom> getChildrenPrivileges() {
		return childrenPrivileges;
	}

	public void setChildrenPrivileges(List<TPrivilegeCustom> childrenPrivileges) {
		this.childrenPrivileges = childrenPrivileges;
	}

	public TPrivilegeButton getPrivilegeButton() {
		return privilegeButton;
	}

	public void setPrivilegeButton(TPrivilegeButton privilegeButton) {
		this.privilegeButton = privilegeButton;
	}
}
