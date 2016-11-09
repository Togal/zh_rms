# zh_bms
后台管理系统

## redis 非集群、集群配置

项目使用jedis 客户端对redis进行操作，分集群以及非集群模式

```
在com.bms.base.controller.BaseController.java 中，集成RedisClientComponent组件类切传入泛类类型，以

RedisShardedInterface 非集群

RedisClusterInterface 集群

作为参数传入。
```

```
在src/main/resources的application.properties中redis.type以

com.bms.redis.RedisShardedClientFactory 非集群

com.bms.redis.cluster.RedisClusterClientFactory 集群

作为参数
```
