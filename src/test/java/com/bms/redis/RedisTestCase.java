package com.bms.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * Title:RedisTestCase
 * Description:
 * @author    zwb
 * @date      2016年10月27日 下午6:08:04
 *
 */
public class RedisTestCase {
	
	/**
	 * redis 入门
	 */
	@Test
	public void redisConTest(){
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value =jedis.get("foo");
		System.out.println(value);
	}
	
	private static JedisPool POOL;
	
	/**
	 * jedis连接池
	 * @return
	 */
	public static JedisPool getPool(){
		if(POOL == null){
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxIdle(5);
			config.setMaxWaitMillis(1000 * 100);
			config.setTestOnBorrow(true);
			POOL = new JedisPool(config, "localhost",6379);
			
		}
		return POOL;
	}
	
	/**
	 * 返还到连接池  
	 * @param pool
	 * @param jedis
	 */
	public static void returnResource(JedisPool pool, Jedis jedis){
		if(jedis!=null){
			pool.close();
		}
	}
	
	@Test
	public void redisPoolTest(){
		JedisPool pool = null;  
        Jedis jedis = null; 
        try {  
            pool = getPool();  
            jedis = pool.getResource();  
            System.out.println(jedis.get("foo"));  
        } catch (Exception e) {  
            //释放redis对象  
            pool.close(); 
            e.printStackTrace();  
        } finally {  
            //返还到连接池  
            returnResource(pool, jedis);  
        }  
	}
}
