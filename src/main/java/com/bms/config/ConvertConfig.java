package com.bms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bms.converter.CustomDateConvert;

/**
 * 
 * Title:ConvertConfig
 * Description:转换器配置
 * @author    zwb
 * @date      2016年10月11日 下午5:31:32
 *
 */
@Configuration
public class ConvertConfig {
    //注册日期转换器
    @Bean
    public CustomDateConvert customDateConvert(){
    	return new CustomDateConvert();
    }
}
