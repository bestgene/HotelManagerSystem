package com.woniuxy.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描Controller Service
@ComponentScan({
	"com.woniuxy.controller",
	"com.woniuxy.service",
	"com.woniuxy.configuration"
	})
//扫描dao
@MapperScan("com.woniuxy.dao")

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ServletInitializer.class);
	}

}
