package com.woniuxy.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.woniuxy.pojo.User;
import com.woniuxy.service.UserService;

@SpringBootApplication
@ComponentScan({
	"com.woniuxy.controller",
	"com.woniuxy.service"
	})
@MapperScan("com.woniuxy.dao")
public class HotelManagerSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagerSystemApplication.class, args);
	}

}
