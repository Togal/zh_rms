package com.bms;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

/**
 * 
 * Title:MainServer
 * Description:启动类
 * @author    zwb
 * @date      2016年9月21日 上午11:55:09
 *
 */
@SpringBootApplication
@MapperScan("com.**.mapper")
public class MainServer extends SpringBootServletInitializer  {
	
	
    @Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainServer.class);
	}

	public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }
	
	@Value("${custom.resource-base}")
	private String resourceBase;
	
    
    /**
     * 使用jetty容器启动
     * @return
     */
	@Bean
    public EmbeddedServletContainerFactory embeddedServletContainerFactory(){
    	JettyEmbeddedServletContainerFactory factory = new JettyEmbeddedServletContainerFactory();
    	factory.addServerCustomizers(new JettyServerCustomizer() {
			@Override
			public void customize(Server server) {
				for (Handler handler : server.getHandlers()) {
					if(handler instanceof WebAppContext){
						WebAppContext webAppContext = (WebAppContext) handler;
						webAppContext.setResourceBase(resourceBase);
						server.setHandler(webAppContext);
					}
				}
			}
		});
    	//factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/404"));//非springmvc错误页，此为静态错误页，页面存放classpath:src/main/resource/public/error/下
    	return factory;
    }
}
