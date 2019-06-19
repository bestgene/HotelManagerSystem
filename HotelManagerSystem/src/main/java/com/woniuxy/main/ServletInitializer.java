package com.woniuxy.main;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
	"com.woniuxy.controller",
	"com.woniuxy.service.impl",
	"com.woniuxy.configuration"
	})
// 常常扫描Controller DAO Service
//@ComponentScan("com.woniuxy.filter")
//@ServletComponentScan
//常常扫描Servlet、Filter、Listener
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}

}
