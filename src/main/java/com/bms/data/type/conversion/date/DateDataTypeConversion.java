package com.bms.data.type.conversion.date;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:DateDataTypeConversion
 * Description:日期转化接口
 * @author    zwb
 * @date      2016年7月26日 下午6:00:00
 *
 */
public class DateDataTypeConversion {
	/**
	 * 时间戳转字符串日期
	 * @param timeMillis 时间戳
	 * @param isTen 是否10位
	 * @param dateFormat 格式
	 * @return
	 */
	public static String timeMillisToDate(long timeMillis, boolean isTen, DATEFORMAT dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat.getValue());
		String sd = null;
		if(isTen){
			sd = sdf.format(new Date(timeMillis * 1000L));
		}else{
			sd = sdf.format(new Date(timeMillis));
		}
		return sd;
	}
}
