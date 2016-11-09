package com.bms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.redis.RedisShardedClientFactory;
import com.bms.redis.cluster.RedisClusterClientFactory;

/**
 * 
 * Title:RedisConfig
 * Description:redis缓存配置
 * @author    zwb
 * @date      2016年11月4日 下午2:27:01
 *
 */
@Configuration
public class RedisConfig {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${redis.type}")
	private String redis_client_type;
	
	@Bean(name = "redisClient")
	@ConfigurationProperties(prefix="redis")
	public Object redisClient(){
		Class clazz = null;
		try {
			clazz = Class.forName(redis_client_type);
		} catch (ClassNotFoundException e) {
			logger.error("ClassNotFoundException catch:", e);
		}
		if(RedisShardedClientFactory.class != clazz && RedisClusterClientFactory.class != clazz){
			logger.error("No in accordance with RedisClient.class or RedisClusterClientFactory.class");
			return null;
		}
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			logger.error("clazz.newInstance() case to object error!", e);
		}
		return null;
	}
}
