package com.bms.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.bms.annotation.WriteOnlyConnection;
import com.bms.config.readwriteseparation.datasource.DataSourceContextHolder;

/**
 * 
 * Title:WriteOnlyConnectionInterceptor
 * Description:绑定数据源为master库
 * @author    zwb
 * @date      2016年10月12日 上午10:31:50
 *
 */
@Aspect
@Component
public class WriteOnlyConnectionInterceptor {
	@Around("@annotation(writeOnlyConnection)")
	public Object proceed(ProceedingJoinPoint proceedingJoinPoint, WriteOnlyConnection writeOnlyConnection) throws Throwable {
		DataSourceContextHolder.write();
		Object result = proceedingJoinPoint.proceed();
		return result;
	}
}
