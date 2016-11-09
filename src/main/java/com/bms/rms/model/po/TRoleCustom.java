package com.bms.rms.model.po;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TRoleCustom
 * Description:角色扩展类
 * @author    zwb
 * @date      2016年10月19日 下午2:05:04
 *
 */
public class TRoleCustom extends TRole {
	 /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }
}
