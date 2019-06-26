package com.woniuxy.controller;



import java.math.BigDecimal;
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

import com.woniuxy.pojo.Level;
import com.woniuxy.pojo.User;
import com.woniuxy.pojo.UserInfo;
import com.woniuxy.pojo.Vip;
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
	
	@RequestMapping("getvipbyuser")     //根据uid查vip相关信息
	public String getVipLevelByUserId(){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Integer user_id=(Integer) session.getAttribute("user_id");
		Vip vip=userService.getVipByUid(user_id);
		Integer level_id=vip.getLevel_id();
		Level level=userService.getLevelByVipId(level_id);
		BigDecimal discount = level.getLevel_discount();//获取到的折扣
		return null;
	}
	
	@RequestMapping("getvipbyadmin")     //后台查vip相关信息
	public String getVipLevelByAdmin(String user_info_tel,String user_info_idcard){
		if(user_info_tel==null){
			user_info_tel="*";
		}
		if(user_info_idcard==null){
			user_info_idcard="*";
		}
		System.out.println("电话，身份证："+user_info_idcard+user_info_tel);   //管理员查询会员
		UserInfo userInfo = userService.selectUserInfoByTelOrIdcard(user_info_tel, user_info_idcard);
		System.out.println("用户信息:"+userInfo);
		Vip vip=userService.getVipByUid(userInfo.getUser_id());
		System.out.println("vip信息:"+vip);
		Integer level_id=vip.getLevel_id();
		Level level=userService.getLevelByVipId(level_id);
		System.out.println("会员等级"+level);
		BigDecimal discount = level.getLevel_discount();//获取到的折扣
		System.out.println(discount);
		System.out.println("Service:"+userService.getDiscountByTelOrIdcard(user_info_tel, user_info_idcard));
		return "操作成功";
	}
}