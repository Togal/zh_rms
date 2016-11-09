package com.bms.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.bms.config.readwriteseparation.datasource.DataSourceContextHolder;

/**
 * 
 * Title:DataSourceAop
 * Description:Spring AOP切面 读写分离
 * @author    zwb
 * @date      2016年10月11日 下午6:41:07
 *
 */
@Aspect
@Component
public class DataSourceAop {
	
    @Before("execution(* com..*.mapper.*.save*(..))" + " or " +
    		"execution(* com..*.mapper.*.insert*(..))" + " or " +
    		"execution(* com..*.mapper.*.update*(..))" + " or " +
    		"execution(* com..*.mapper.*.delete*(..))")
    public void setWriteDataSourceType() {
        DataSourceContextHolder.write();
    }
    
    @Before("execution(* com..*.mapper.*.find*(..))" + " or " +
    		"execution(* com..*.mapper.*.get*(..))" + " or " +
    		"execution(* com..*.mapper.*.select*(..))")
    public void setReadDataSourceType() {
        DataSourceContextHolder.read();
    }
}