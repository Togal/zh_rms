spring.mvc.view.prefix: @spring.mvc.view.prefix@
spring.mvc.view.suffix: .jsp

spring.datasource.type = com.mchange.v2.c3p0.ComboPooledDataSource
spring.datasource.jdbcUrl = @spring.datasource.jdbcUrl@
spring.datasource.user = @spring.datasource.user@
spring.datasource.password = @spring.datasource.password@
spring.datasource.driverClass = com.mysql.jdbc.Driver
spring.datasource.minPoolSize = 3
spring.datasource.maxPoolSize = 15
spring.datasource.acquireIncrement = 3
spring.datasource.maxStatements = 8
spring.datasource.maxStatementsPerConnection = 5
spring.datasource.maxIdleTime = 1800

#主库
datasource.write.type = com.mchange.v2.c3p0.ComboPooledDataSource
datasource.write.jdbcUrl = @datasource.write.jdbcUrl@
datasource.write.user = @datasource.write.user@
datasource.write.password = @datasource.write.password@
datasource.write.driverClass = com.mysql.jdbc.Driver
datasource.write.minPoolSize = 3
datasource.write.maxPoolSize = 15
datasource.write.acquireIncrement = 3
datasource.write.maxStatements = 8
datasource.write.maxStatementsPerConnection 5
datasource.write.maxIdleTime = 1800

#从库
datasource.read.type = com.mchange.v2.c3p0.ComboPooledDataSource
datasource.read.jdbcUrl = @datasource.read.jdbcUrl@
datasource.read.user = @datasource.read.user@
datasource.read.password = @datasource.read.password@
datasource.read.driverClass = com.mysql.jdbc.Driver
datasource.read.minPoolSize = 3
datasource.read.maxPoolSize = 15
datasource.read.acquireIncrement = 3
datasource.read.maxStatements = 8
datasource.read.maxStatementsPerConnection 5
datasource.read.maxIdleTime = 1800

server.port = @server.port@
server.address = @server.address@

custom.resource-base = @custom.resource-base@

logging.file=work/log/app.log

redis.type=@redis.type@
redis.server=@redis.server@