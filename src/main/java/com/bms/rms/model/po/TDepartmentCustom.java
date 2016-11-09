package com.bms.rms.model.po;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TDepartmentCustom
 * Description:部门扩展类
 * @author    zwb
 * @date      2016年10月19日 下午2:33:17
 *
 */
public class TDepartmentCustom extends TDepartment {
	 /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }
}
