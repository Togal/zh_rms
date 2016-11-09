package com.bms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Title:WriteOnlyConnection
 * Description:数据源绑定为master主库
 * @author    zwb
 * @date      2016年10月12日 上午10:31:03
 *
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface WriteOnlyConnection {

}
