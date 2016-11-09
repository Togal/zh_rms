package com.bms.data.type.conversion.date.vo;

/**
 * 
 * Title:DATEFORMAT
 * Description:日期格式枚举
 * @author    zwb
 * @date      2016年7月18日 下午6:03:58
 *
 */
public enum DATEFORMAT {
	YYYY_MM_DD("yyyy-MM-dd"),YYYY_MM_DD_HH_MM("yyyy-MM-dd HH:mm"),YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss");
	
	private final String value;
	
	private DATEFORMAT(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
