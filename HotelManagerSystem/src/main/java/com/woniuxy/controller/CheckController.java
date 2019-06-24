package com.woniuxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woniuxy.emailUtil.EmailUtil;
import com.woniuxy.pojo.User;



@Controller
@RequestMapping("/check")
public class CheckController {
	@RequestMapping("/emailcheck")
	public String emailCheck(User user,String email){
		new Thread(new EmailUtil(email,user.getUser_acc(),user.getUser_pwd())).start(); //发送邮箱，进行验证（转入usercontroller进行注册）
		return "test.html";
	}
}
