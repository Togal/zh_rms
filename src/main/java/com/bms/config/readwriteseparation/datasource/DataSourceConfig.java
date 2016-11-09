package com.bms.config.readwriteseparation.datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.github.pagehelper.PageHelper;

/**
 * 
 * Title:DataSourceConfig
 * Description:数据源配置
 * @author    zwb
 * @date      2016年10月11日 下午5:48:02
 *
 */
@Configuration
@AutoConfigureAfter({BaseDataSource.class})
public class DataSourceConfig extends MybatisAutoConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(roundRobinDataSouceProxy());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:**/mapper/*.xml"));
        
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        
        return sqlSessionFactoryBean.getObject();
    }
	
	/**
	 * 数据源
	 * @return
	 */
	//不用设置bean使用proxy.afterPropertiesSet进行初始化AbstractRoutingDataSource，否则出现循环依赖
	//@Bean
	public AbstractRoutingDataSource roundRobinDataSouceProxy() {
		
		//读数据源封装
		List<DataSource> readDataSources = new ArrayList<DataSource>();
		
		readDataSources.add((DataSource) applicationContext.getBean("readDataSource"));
		//如多个读数据源则在这里添加
		//readDataSources.add(SpringContextHolder.getBean("xxxx"));
		//...
		
		//动态多数据源
		MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(readDataSources.size());
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		DataSource writeDataSource = (DataSource) applicationContext.getBean("writeDataSource");
		// 写
		targetDataSources.put(DataSourceType.write.getType(), writeDataSource);
		//读
		int index = 0;
		for (DataSource dataSource : readDataSources) {
			targetDataSources.put(index, dataSource);
			index++;
		}
		
		proxy.setDefaultTargetDataSource(writeDataSource);//当
		proxy.setTargetDataSources(targetDataSources);
		proxy.afterPropertiesSet();
		return proxy;
	}
	
}
