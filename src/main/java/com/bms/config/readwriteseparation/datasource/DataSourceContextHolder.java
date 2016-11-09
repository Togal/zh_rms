package com.bms.config.readwriteseparation.datasource;

/**
 * 
 * Title:DataSourceContextHolder
 * Description:线程共享全局变量
 * @author    zwb
 * @date      2016年10月11日 下午5:49:33
 *
 */
public class DataSourceContextHolder {
	private static final ThreadLocal<String> local = new ThreadLocal<String>();

	public static ThreadLocal<String> getLocal() {
		return local;
	}

	/**
	 * 读可能是多个库
	 */
	public static void read() {
		local.set(DataSourceType.read.getType());
	}

	/**
	 * 写只有一个库
	 */
	public static void write() {
		local.set(DataSourceType.write.getType());
	}

	public static String getJdbcType() {
		return local.get();
	}
}