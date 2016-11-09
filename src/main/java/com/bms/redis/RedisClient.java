package com.bms.redis;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * Title:RedisClient
 * Description:redisClient
 * @author    zwb
 * @date      2016年11月4日 下午2:12:55
 *
 */
public class RedisClient implements InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
    private Pattern pattern = Pattern.compile("(.+):(\\d+)");
    private JedisPool jedisPool;

    private String server;


    public void setServer(String server) {
        this.server = server;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Matcher matcher = pattern.matcher(server);
        if (!matcher.find()) {
            throw new IllegalArgumentException("server: "+server);
        }

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(32);
        jedisPool = new JedisPool(config, matcher.group(1), Integer.parseInt(matcher.group(2)));

    }

    
    public String get(String key){
    	try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.get(key);
            return result;
        }
    }
    
    public String set(String key, String value) {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.set(key, value);
            return result;
        }
    }

    public Long llen(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long result = jedis.llen(key);
            return result;
        }
    }

    public String lpop(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.lpop(key);
            return result;
        }
    }

    public Long rpush(String key, String... strings) {
        try (Jedis jedis = jedisPool.getResource()) {
            Long result = jedis.rpush(key, strings);
            return result;
        }
    }

    public List<String> lrange(String key, long start, long end) {
        try (Jedis jedis = jedisPool.getResource()) {
            List<String> result = jedis.lrange(key, start, end);
            return result;
        }
    }

    public String ltrim(String key, long start, long end) {
        try (Jedis jedis = jedisPool.getResource()) {
            String result = jedis.ltrim(key, start, end);
            return result;
        }
    }
}
