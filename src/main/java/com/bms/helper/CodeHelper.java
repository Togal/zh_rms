package com.bms.helper;

import java.util.HashMap;
import java.util.Map;

import com.bms.properties.reader.PropertiesReaderAbstract;
import com.bms.properties.reader.Reader;

/**
 * 
 * Title:CodeHelper
 * Description:错误代码
 * @author    zwb
 * @date      2016年7月25日 上午11:19:56
 *
 */
public class CodeHelper {
	
	/**
	 * reader
	 */
	private static PropertiesReaderAbstract reader = new Reader();
	
	/**
	 * 
	 * Title:CODE
	 * Description:
	 * D100000->请求成功<br>
	 * D200000->系统错误<br>
	 * D200001->参数错误<br>
	 * D200002->非法参数<br>
	 * D300000->未知错误<br>
	 * 
	 * @author    zwb
	 * @date      2016年9月12日 上午11:46:10
	 *
	 */
	public enum CODE{
		D100000,//请求成功
		//base 0~99
		D200000,//系统错误
		D200001,//参数错误
		D200002,//非法参数
		//100~199
		//200~299
		//300~399
		//400~499
		//--
		D300000;//未知错误
	}
	
	/**
	 * 获取信息
	 * @param CODE 错误码
	 * @return 错误信息
	 */
	public static Map<Integer, String> code(CODE CODE){
		String keyStr = String.valueOf(CODE.name());
		keyStr = keyStr.substring(1, keyStr.length());
		Integer key = Integer.valueOf(keyStr);
		
		String value = reader.getValue("Code", keyStr);
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(key, value);
		return map;
	}
}
