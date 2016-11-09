package com.bms.redis.cluster;

import redis.clients.jedis.BasicCommands;
import redis.clients.jedis.BinaryJedisClusterCommands;
import redis.clients.jedis.JedisCommands;

/**
 * 
 * Title:RedisClusterInterface
 * Description:redis集群接口
 * @author    zwb
 * @date      2016年11月4日 下午2:33:34
 *
 */
public interface RedisClusterInterface extends JedisCommands,BasicCommands,BinaryJedisClusterCommands {

}
