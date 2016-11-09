package com.bms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
/*@AutoConfigureAfter(InitBeans.class)*/
public class MapperConfig {
	
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.**.mapper");
		mapperScannerConfigurer.setMarkerInterface(tk.mybatis.mapper.common.Mapper.class);
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		return mapperScannerConfigurer;
	}
}
