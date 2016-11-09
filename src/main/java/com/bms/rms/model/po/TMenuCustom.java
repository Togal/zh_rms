package com.bms.rms.model.po;

import java.util.List;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TMenuCustom
 * Description:菜单扩展类
 * @author    zwb
 * @date      2016年9月29日 下午2:12:32
 *
 */
public class TMenuCustom extends TMenu {
	
	private List<TMenuCustom> slaveChildrenMenus;//从菜单的子菜单
	
	private TPrivilege privilege;//权限
	
    /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }

	public List<TMenuCustom> getSlaveChildrenMenus() {
		return slaveChildrenMenus;
	}

	public void setSlaveChildrenMenus(List<TMenuCustom> slaveChildrenMenus) {
		this.slaveChildrenMenus = slaveChildrenMenus;
	}

	public TPrivilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(TPrivilege privilege) {
		this.privilege = privilege;
	}	
}
