package com.bms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bms.rms.service.IMenuService;

/**
 * 
 * Title:SpringBootApplicationTestCase
 * Description:测试springboot启动服务
 * @author    zwb
 * @date      2016年10月27日 上午10:44:48
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTestCase {
	
	private static Logger logger = LoggerFactory.getLogger(SpringBootApplicationTestCase.class);
	
	@Autowired
	private IMenuService iMenuService;
	
	@Test
	public void test(){
		if(iMenuService != null){
			logger.info("Test : 服务启动 ,Result:成功");
		}else{
			logger.error("Test : 服务启动 ,Result:失败");
		}
	}
}
