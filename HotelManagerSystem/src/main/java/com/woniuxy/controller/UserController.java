package com.woniuxy.controller;

import java.awt.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.Vip;
import com.woniuxy.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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
	@RequestMapping("/getinfo")
	public String getInfo(ModelMap map){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Integer user_id=(Integer) session.getAttribute("user_id");
		UserInfo userInfo=userService.getInfoByUid(user_id);
		System.out.println(userInfo);
		map.addAttribute("userInfo",userInfo);
		return "info.html";
	}
	
	@RequestMapping("/update")
	public String updateInfo(UserInfo userInfo){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Integer user_id=(Integer) session.getAttribute("user_id");
		userInfo.setUser_id(user_id);
		userService.updateInfo(userInfo);
		return "info.html";
	}
	
	@RequiresPermissions(value={"user:delete"})
	@RequestMapping("/delete")
	public String deleteUser(Integer user_id) throws Exception{
		userService.deleteUserByUid(user_id);
		return "test.html";
	}
	
	//根据用户账号user_acc去查询vip的等级
		@GetMapping("/FindlevelByUseracc")
		public void FindlevelByUseracc(User user){
			
			//通过用户的姓名去除查询user_id
			User user2= userService.findUserIdByacc(user);
			
			//通过user_id去查询会员等级
			Vip vip=userService.findLevelIdByuserId(user2);
			//通过会员等级去查询会员的折扣力度
			Level level=userService.findQuatoBylevelId(vip);
			System.out.println(level);
			
		}
		}
		
		
		//进行用户的登录操作
		/*@GetMapping("/login")
		@ResponseBody
		public String login(User user){
			System.out.println("进入登录。。。");
			ModelAndView view=new ModelAndView();
			String result="";
			
			//根据用户名去查询是否存在此用户
		if (userService.findUserByUid(user)==null) {
			result="不存在此账号";
		}	
			else {
			if (userService.findUserByUid(user).getUser_pwd().equals(user.getUser_pwd())) {
				result="html/index.html";
			}	
			else {
				result="密码不正确";
			}
				
			}
			
		
			return result;
		}
		*/
