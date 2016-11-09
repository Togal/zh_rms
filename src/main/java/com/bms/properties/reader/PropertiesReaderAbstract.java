package com.bms.properties.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Title:PropertiesReaderAbstract
 * Description:properties文件读取工具
 * @author    zwb
 * @date      2016年7月18日 上午11:45:58
 *
 */
public abstract class PropertiesReaderAbstract {
	
	private static Logger logger = LoggerFactory.getLogger(PropertiesReaderAbstract.class);
	
	/**
	 * 获取properties值
	 * @param file 文件
	 * @param key 键
	 * @return
	 */
	public String getValue(String file,String key){
		String value;
		//未取到值，从properties文件中查找
		try{
			ResourceBundle bundle = ResourceBundle.getBundle(file);
			value = bundle.getString(key);
			if(value == null){
				logger.error("没有该属性的值：" + key);
			}else{
				//value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
			}
			return value;
		}catch(Throwable e){
			logger.error("Throwable catch:" , e);
			return null;
		}
	}
	
	/**
	 * 获取properties配置文件
	 * @param fileName 配置文件名称
	 * @return
	 */
	public Map<String,String> getProperties(String fileName){
		Properties props = new Properties();
		Map<String, String> map = new HashMap<String, String>();
		try{
			InputStream in = getClass().getResourceAsStream(fileName);
			if(in != null){			
				props.load(in);
				Enumeration<?> en = props.propertyNames();
				while(en.hasMoreElements()){
					String key = (String) en.nextElement();
					String property = props.getProperty(key);
					map.put(key, property);
				}
			}else{
				logger.error("配置文件读取失败:" + fileName);
			}
		}catch(IOException e){
			logger.error("IOException catch:" , e);
		}
		return map;
	}
}
