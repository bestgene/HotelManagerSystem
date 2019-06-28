package com.woniuxy.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.woniuxy.controller"/*,"com.woniuxy.configuration"*/,"com.woniuxy.service.impl","com.woniuxy.service"})
@MapperScan("com.woniuxy.dao")
public class HotelManagerSystemApplication {

	public static void main(String[] args) {
		//System.out.println("测试1");
		SpringApplication.run(HotelManagerSystemApplication.class, args);
	}

}
