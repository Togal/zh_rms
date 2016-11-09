package com.bms.redis;

import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisCommands;

public interface RedisShardedInterface extends BinaryJedisCommands,JedisCommands {
}
