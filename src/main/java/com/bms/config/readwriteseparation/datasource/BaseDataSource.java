package com.bms.config.readwriteseparation.datasource;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * Title:BaseDataSource
 * Description:数据源
 * @author    zwb
 * @date      2016年10月12日 下午6:00:13
 *
 */
@Configuration
public class BaseDataSource {
	/**
	 * 主
	 * @return
	 */
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource() {
		return new ComboPooledDataSource();
	}
	
	/**
     * 从
     * @return
     */
	@Bean
    @ConfigurationProperties(prefix = "datasource.read")
    public DataSource readDataSource() {
		return new ComboPooledDataSource();
	}
}
