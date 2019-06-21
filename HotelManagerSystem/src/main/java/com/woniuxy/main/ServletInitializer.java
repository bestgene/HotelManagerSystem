package com.woniuxy.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocket;


@SpringBootApplication
@ComponentScan({
	"com.woniuxy.controller",
	"com.woniuxy.service.impl",
	"com.woniuxy.configuration"
	})
@EnableWebSocket // 启用websocket
// 常常扫描Controller DAO Service
//@ComponentScan("com.woniuxy.filter")
//@ServletComponentScan
//常常扫描Servlet、Filter、Listener

@MapperScan("com.woniuxy.dao")
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}

}
