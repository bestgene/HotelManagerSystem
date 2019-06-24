package com.woniuxy.controller;



import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	UserService userService;
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/register") //注册，由邮箱跳转到该方法
	public String register(User user){
		user.setUser_pwd(new SimpleHash("MD5", user.getUser_pwd(), null,1024).toString());
		userService.addUser(user);
		return "test.html";
	}
	
	@RequestMapping("/login") //登录认证
	public String login(User user,HttpServletRequest request){
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()){
			UsernamePasswordToken token = 
					new UsernamePasswordToken(user.getUser_acc().toString(), user.getUser_pwd());
			try {
				currentUser.login(token);
				user=userService.findUserByAcc(user.getUser_acc());
				Session session = currentUser.getSession();
				session.setAttribute("user_id",user.getUser_id()); //认证成功，将当前用户id存入session
				System.out.println("认证成功");
				return "test.html";
			} catch (Exception e) {
				System.out.println("认证失败");
				return "error.html";
			}
		}
		return "test.html";
	}
	@RequestMapping("/getinfo")   //获取当前用户的个人信息
	public String getInfo(ModelMap map){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Integer user_id=(Integer) session.getAttribute("user_id");
		UserInfo userInfo=userService.getInfoByUid(user_id);
		System.out.println(userInfo);
		map.addAttribute("userInfo",userInfo);
		return "info.html";
	}
	
	@RequestMapping("/update")   //更新个人信息
	public String updateInfo(UserInfo userInfo){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Integer user_id=(Integer) session.getAttribute("user_id");
		userInfo.setUser_id(user_id);
		userService.updateInfo(userInfo);
		return "info.html";
	}
	
	@RequiresPermissions(value={"user:delete"})
	@RequestMapping("/delete")    //管理员删除其他人账号：根据user_id
	public String deleteUser(Integer user_id) throws Exception{
		userService.deleteUserByUid(user_id);
		return "test.html";
	}
	
	@RequiresPermissions(value={"user:showall"})
	@RequestMapping("showall")
	public String showAllUser(){
		List<User> users = userService.allUser();
		return null;
	}
}
