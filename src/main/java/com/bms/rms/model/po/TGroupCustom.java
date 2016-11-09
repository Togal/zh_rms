package com.bms.rms.model.po;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TGroupCustom
 * Description:用户组扩展类
 * @author    zwb
 * @date      2016年10月19日 下午3:19:27
 *
 */
public class TGroupCustom extends TGroup {
	 /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }
}
