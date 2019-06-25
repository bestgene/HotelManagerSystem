package com.woniuxy.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.Vip;
import com.woniuxy.service.UserService;

@Controller
@RequestMapping("/User")
public class UserController {

	@Resource
	private UserService userService;

	// 进行用户的登录操作
	@GetMapping("/login")
	/* @ResponseBody */
	public String login(User user) {
		System.out.println("进入登录。。。");
		ModelAndView view = new ModelAndView();
		String result = "";

		// 根据用户名去查询是否存在此用户
		if (userService.findUserByUid(user) == null) {
			result = "不存在此账号";
		} else {
			if (userService.findUserByUid(user).getUser_pwd().equals(user.getUser_pwd())) {
				result = "html/index.html";
			} else {
				result = "密码不正确";
			}

		}

		return result;
	}

	// 根据用户账号user_acc去查询vip的等级
	@GetMapping("/FindlevelByUseracc")
	public void FindlevelByUseracc(User user) {

		// 通过用户的姓名去除查询user_id
		User user2 = userService.findUserIdByacc(user);

		// 通过user_id去查询会员等级
		Vip vip = userService.findLevelIdByuserId(user2);
		// 通过会员等级去查询会员的折扣力度
		Level level = userService.findQuatoBylevelId(vip);
		System.out.println(level);

	}
}

// 进行用户的登录操作
/*
 * @GetMapping("/login")
 * 
 * @ResponseBody public String login(User user){ System.out.println("进入登录。。。");
 * ModelAndView view=new ModelAndView(); String result="";
 * 
 * //根据用户名去查询是否存在此用户 if (userService.findUserByUid(user)==null) {
 * result="不存在此账号"; } else { if
 * (userService.findUserByUid(user).getUser_pwd().equals(user.getUser_pwd())) {
 * result="html/index.html"; } else { result="密码不正确"; }
 * 
 * }
 * 
 * 
 * return result; }
 */
