package com.bms.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * Title:CustomDateConvert
 * Description:转换器
 * @author    zwb
 * @date      2016年10月1日 上午12:12:02
 *
 */
public class CustomDateConvert implements Converter<String, Date> {
	
	@Override
	public Date convert(String source) {
		//实现将日期串转成日期类型(格式为yyy-mm-dd HH:mm:ss)
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		//实现将日期串转成日期类型(格式为yyy-mm-dd)
		SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyy-MM-dd");
		try {
			return simpleDateFormat1.parse(source);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		
		//实现将日期串转成日期类型(格式为HH:mm:ss)
		SimpleDateFormat simpleDateFormat2=new SimpleDateFormat("HH:mm:ss");
		try {
			return simpleDateFormat2.parse(source);
		} catch (ParseException e) {
			//e.printStackTrace();
		}
		return null;
	}
}