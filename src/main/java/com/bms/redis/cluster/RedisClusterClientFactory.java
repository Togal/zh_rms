package com.bms.redis.cluster;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class RedisClusterClientFactory implements FactoryBean<RedisClusterInterface>, InitializingBean ,DisposableBean {
    private JedisCluster jedisCluster;

    private String server;

    public void setServer(String server) {
        this.server = server;
    }
    private int maxTotal = 1000;//可用连接实例的最大数目，默认值为1000；如果赋值为-1，则表示不限制；
    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    private int maxWait = 1000;//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出
    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
    	//Jedis Cluster will attempt to discover cluster nodes automatically
    	String[] servers = server.split(";|,");
        for (String str : servers) {
            String[] ap = str.split(":");
            HostAndPort hp = new HostAndPort(ap[0], Integer.valueOf(ap[1]));
            jedisClusterNodes.add(hp);
        }
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(maxTotal); //可用连接实例的最大数目，默认值为8；如果赋值为-1，则表示不限制；
        config.setMaxIdle(100); //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值是8。
        config.setMinIdle(10); //控制一个pool最少有多少个状态为idle(空闲的)的jedis实例，默认值是0。
        config.setMaxWaitMillis(maxWait);//等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
    	jedisCluster = new JedisCluster(jedisClusterNodes,config);
    }
    
    public RedisClusterInterface getJedis(){
    	RedisClusterInterface service = (RedisClusterInterface)creatProxyInstance();
        return service;
    }
    
    @Override
    public void destroy() throws Exception {
    	jedisCluster.close();
    }
    
    @Override
	public RedisClusterInterface getObject() throws Exception {
		return getJedis();
	}

	@Override
	public Class<?> getObjectType() {
		return RedisClusterInterface.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}
    
    public  Object creatProxyInstance() {
        return Proxy.newProxyInstance(RedisClusterInterface.class.getClassLoader(), new Class[]{RedisClusterInterface.class} , new ProxyFactory(jedisCluster));
    }

    class ProxyFactory  implements InvocationHandler {
        private JedisCluster jedisCluster;

        public ProxyFactory(JedisCluster jedisCluster){
            this.jedisCluster = jedisCluster;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args)
                throws Throwable {
            if (method.getName() == "toString"){
                return this.toString();
            }
			Object result = method.invoke(jedisCluster, args);;
            return result;
        }
    }

}