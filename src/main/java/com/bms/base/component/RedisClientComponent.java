package com.bms.base.component;

import javax.annotation.Resource;

/**
 * 
 * Title:RedisClientComponent
 * Description:redis 客户端组件
 * @author    zwb
 * @date      2016年11月7日 下午5:58:07
 *
 */
public class RedisClientComponent<T> {
	
	@Resource(name="redisClient")
	protected T redisClient;
}
