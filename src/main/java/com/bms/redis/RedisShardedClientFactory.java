package com.bms.redis;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class RedisShardedClientFactory implements FactoryBean<RedisShardedInterface> ,InitializingBean ,DisposableBean {
	// private Logger log = LoggerFactory.getLogger(getClass());
	private ShardedJedisPool shardedJedisPool;

	private String server;

	public void setServer(String server) {
		this.server = server;
		// System.out.println(server);
	}

	private int maxTotal = 1000; // 可用连接实例的最大数目，默认值为1000；如果赋值为-1，则表示不限制；

	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	private int maxWait = 1000;// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出

	public void setMaxWait(int maxWait) {
		this.maxWait = maxWait;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// System.out.println(server);
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		String[] servers = server.split(";|,");
		for (String str : servers) {
			String[] ap = str.split(":");
			JedisShardInfo si = new JedisShardInfo(ap[0], Integer.valueOf(ap[1]));
			// si.setPassword("123");
			shards.add(si);
		}

		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(maxTotal); // 可用连接实例的最大数目，默认值为8；如果赋值为-1，则表示不限制；
		config.setMaxIdle(100); // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值是8。
		config.setMinIdle(10); // 控制一个pool最少有多少个状态为idle(空闲的)的jedis实例，默认值是0。
		config.setMaxWaitMillis(maxWait);// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出
		config.setTestOnBorrow(true);
		config.setTestOnReturn(true);
		shardedJedisPool = new ShardedJedisPool(config, shards);
	}

	@Override
	public void destroy() {
		shardedJedisPool.destroy();
	}

	public RedisShardedInterface getJedis() {
		RedisShardedInterface service = (RedisShardedInterface) creatProxyInstance();
		// service.setShardedJedisPool(shardedJedisPool);
		return service;
	}

	@Override
	public RedisShardedInterface getObject() throws Exception {
		return getJedis();
	}

	@Override
	public Class<?> getObjectType() {
		return RedisShardedInterface.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Object creatProxyInstance() {
		return Proxy.newProxyInstance(RedisShardedInterface.class.getClassLoader(),
				new Class[] { RedisShardedInterface.class }, new ProxyFactory(shardedJedisPool));
	}
  
	class ProxyFactory implements InvocationHandler {

		private ShardedJedisPool shardedJedisPool;

		public ProxyFactory(ShardedJedisPool shardedJedisPool) {
			this.shardedJedisPool = shardedJedisPool;
		}

		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			if (method.getName() == "toString") {
				return this.toString();
			}
			Object result = null;
			ShardedJedis shardedJedis = shardedJedisPool.getResource();
			try{
				result = method.invoke(shardedJedis, args);
			}finally{
				/*if(shardedJedis != null){
					shardedJedis.close();
				}*/
			}
			return result;
		}

	}
}
