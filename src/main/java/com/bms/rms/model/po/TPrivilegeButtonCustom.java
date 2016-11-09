package com.bms.rms.model.po;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TPrivilegeButtonCustom
 * Description:按钮级别权限控制
 * @author    zwb
 * @date      2016年11月2日 上午10:35:32
 *
 */
public class TPrivilegeButtonCustom extends TPrivilegeButton {
	 /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }
    
    /**
     * 处理后的名称
     * @return
     */
    public String getNameStr(){
    	return getName() + "(" + (getSelectdommethod() == (byte) 0 ? "#" : "." ) + getSelectdomname() + ")";
    }
}
